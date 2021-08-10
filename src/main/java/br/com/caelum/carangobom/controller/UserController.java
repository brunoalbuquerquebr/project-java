package br.com.caelum.carangobom.controller;

import br.com.caelum.carangobom.form.UserForm;
import br.com.caelum.carangobom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping()
    @ResponseBody
    @Transactional
    public List<UserForm> listUser() {
        return userService.findAllByOrderByNomeBrand();
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<UserForm> createUser(@Valid @RequestBody UserForm user) {
        return new ResponseEntity<>(userService.saveUser(user), null, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Transactional
    public ResponseEntity<UserForm> deletaUser(@PathVariable Long id) {
        UserForm user = userService.removeUser(id);
        if (user != null) {
            return new ResponseEntity<>(user, null, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(user, null, HttpStatus.NOT_FOUND);
        }

    }
}
