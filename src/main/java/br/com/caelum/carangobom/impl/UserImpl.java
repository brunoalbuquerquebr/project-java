package br.com.caelum.carangobom.impl;

import br.com.caelum.carangobom.domain.User;
import br.com.caelum.carangobom.form.UserForm;
import br.com.caelum.carangobom.repository.UserRepository;
import br.com.caelum.carangobom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserForm userForm;

    @Override
    public UserForm saveUser(UserForm userType) {
        userType.setSenha(new BCryptPasswordEncoder().encode(userType.getSenha()));
        User userSave = userRepository.save(userForm.convertDtoToDomain(userType));
        return userForm.convertDomainToDto(userSave);
    }

    @Override
    public List<UserForm> findAllByOrderByNameBrand() {
        List<User> listUser = userRepository.findAllByOrderByNameAsc();
        return userForm.convertListDomainToDto(listUser);
    }

    @Override
    public UserForm removeUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User user2 = user.get();
            userRepository.delete(user2);
            return userForm.convertDomainToDto(user2);
        } else {
            return null;
        }
    }
}
