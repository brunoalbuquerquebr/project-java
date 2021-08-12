package br.com.caelum.carangobom.controller;

import br.com.caelum.carangobom.form.LoginForm;
import br.com.caelum.carangobom.form.TokenForm;
import br.com.caelum.carangobom.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/auth")
@Controller
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<TokenForm> authentication (@RequestBody @Valid LoginForm form) {
        UsernamePasswordAuthenticationToken dateUser = form.converter();

        try {
            Authentication authentication =  authManager.authenticate(dateUser);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenForm(token, "Bearer"));
        }
        catch(AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
