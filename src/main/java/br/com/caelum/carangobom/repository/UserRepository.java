package br.com.caelum.carangobom.repository;

import br.com.caelum.carangobom.domain.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

        public Optional<User> findByEmail(String email);
        public List<User> findAllByOrderByNameAsc();

}
