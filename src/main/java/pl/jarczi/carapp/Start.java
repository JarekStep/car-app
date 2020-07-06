package pl.jarczi.carapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jarczi.carapp.dao.entity.User;
import pl.jarczi.carapp.repo.UserRepo;

@Configuration
public class Start {

    private UserRepo userRepo;

    public Start(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;

        User userJanusz = new User();
        userJanusz.setUsername("Janusz");
        userJanusz.setPassword(passwordEncoder.encode("Janusz123"));
        userJanusz.setRole("ROLE_ADMIN");

        User userBogdan = new User();
        userBogdan.setUsername("Bogdan");
        userBogdan.setPassword(passwordEncoder.encode("Bogdan123"));
        userBogdan.setRole("ROLE_USER");

        User userMarcin = new User();
        userMarcin.setUsername("Marcin");
        userMarcin.setPassword(passwordEncoder.encode("Marcin123"));
        userMarcin.setRole("ROLE_MODERATOR");


        userRepo.save(userJanusz);
        userRepo.save(userBogdan);
        userRepo.save(userMarcin);
    }
}
