package com.eni.ludotheque2.WebSecurityControl;

import com.eni.ludotheque2.bll.IUtilisateurService;
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

import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private IUtilisateurService utilisateurService;

    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUse(Utilisateur user) {
        // Encodage du mot de passe
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Enregistrer l'utilisateur avec le mot de passe encodé
        utilisateurRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        Utilisateur utilBase = utilisateurService.findByLogin(username);
        if(utilBase != null) {
            User.UserBuilder builder = User.builder();
            builder.username(utilBase.getLogin());
            builder.password(utilBase.getPassword());
            String[] roles = utilBase.getRoles().stream().map(Role::getLibelle).toArray(String[]::new);
            builder.roles(roles);
            userDetails = builder.build();
        } else {
            throw new UsernameNotFoundException(username);
        }
        return userDetails;
    }
}
