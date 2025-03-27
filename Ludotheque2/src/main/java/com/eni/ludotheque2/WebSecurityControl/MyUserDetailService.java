package com.eni.ludotheque2.WebSecurityControl;

import com.eni.ludotheque2.bo.Role;
import com.eni.ludotheque2.bo.Utilisateur;
import com.eni.ludotheque2.dal.IUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IUtilisateurRepository utilRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUse(Utilisateur user) {
        // Encodage du mot de passe
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Enregistrer l'utilisateur avec le mot de passe encodé
        utilRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
//        if (username.equals("bob")) {
//            User.UserBuilder builder = User.builder();
//            builder.username(username);
//            builder.password("{bcrypt}$2a$10$a3IQ2/KSUoxdScfAgBYWCuCzKrfqyhq9W8nHmGJ3hgGypST7/0vQ2").roles("admin"); //il faut le mot de passe chiffré
//            userDetails = builder.build();
//        }else if (username.equals("alice")) {
//            User.UserBuilder builder = User.builder();
//            builder.username(username);
//            builder.password("{bcrypt}$2a$10$rKg1P/T9RLApEuWGnDJ9V.2vS9qrmODPiO41YXv/bnNbzD5DnELAW"); //il faut le mot de passe chiffré
//            userDetails = builder.build();
        Utilisateur utilBase = utilRepo.findByLogin(username);
        //System.err.println(utilBase);
        if(utilBase != null) {
            User.UserBuilder builder = User.builder();
            builder.username(utilBase.getLogin());
            builder.password(utilBase.getPassword());
            if(utilBase.getRoles() != null && !utilBase.getRoles().isEmpty()) {
                if(utilBase.getRoles().contains(Role.ADMIN)) {
                    builder.roles("admin");
                } else if(utilBase.getRoles().contains(Role.EMPLOYE)) {
                    builder.roles("employe");
                }
            }
            userDetails = builder.build();
        } else {
            throw new UsernameNotFoundException(username);
        }
        return userDetails;
    }
}
