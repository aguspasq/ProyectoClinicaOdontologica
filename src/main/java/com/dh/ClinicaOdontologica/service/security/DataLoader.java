package com.dh.ClinicaOdontologica.service.security;

import com.dh.ClinicaOdontologica.model.AppUser;
import com.dh.ClinicaOdontologica.model.AppUserRole;
import com.dh.ClinicaOdontologica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("OdontoAdmin");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("OdontoUser");

        userRepository.save(new AppUser("Admin","Admin","admin@odonto.com",
                hashedPassword, AppUserRole.ADMIN));
        userRepository.save(new AppUser("User","User","user@odonto.com",
                hashedPassword2, AppUserRole.USER));

    }
}

