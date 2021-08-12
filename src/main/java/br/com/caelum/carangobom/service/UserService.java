package br.com.caelum.carangobom.service;

import br.com.caelum.carangobom.form.UserForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public UserForm saveUser(UserForm userForm);

    public List<UserForm> findAllByOrderByNameBrand();

    public UserForm removeUser(Long id);
}
