package br.com.caelum.carangobom.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TokenForm {
    private String token;
    private String tipo;

    public TokenForm(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }
}
