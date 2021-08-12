package br.com.caelum.carangobom.form;

import br.com.caelum.carangobom.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class UserForm {
    private Long id;
    private String name;
    private String email;
    private String senha;

    public User convertDtoToDomain(UserForm userDto) {
        User userDomain = new User();
        userDomain.setId(userDto.getId());
        userDomain.setEmail(userDto.getEmail());
        userDomain.setName(userDto.getName());
        userDomain.setSenha(userDto.getSenha());
        return userDomain;
    }

    public UserForm convertDomainToDto (User userDomain) {
        UserForm userDto = new UserForm();
        userDto.setId(userDomain.getId());
        userDto.setEmail(userDomain.getEmail());
        userDto.setName(userDomain.getName());
        userDto.setSenha(userDomain.getPassword());
        return userDto;
    }

    public List<UserForm> convertListDomainToDto (List<User> listDomain){
        List<UserForm> listDto = new ArrayList<>();
        listDomain.forEach(userDomain -> {
            UserForm user = new UserForm();
            user.setId(userDomain.getId())	;
            user.setName(userDomain.getName());
            user.setEmail(userDomain.getEmail());
            listDto.add(user);
        });
        return listDto;
    }

}
