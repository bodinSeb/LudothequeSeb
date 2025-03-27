package com.eni.ludotheque2.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name="Utilisateurs")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_utilisateur;

    @Column(nullable = false, length = 50)
    @NonNull
    private String login;

    @Column(nullable = false, length = 100)
    @NonNull
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_roles",
            joinColumns = {@JoinColumn(name = "id_utilisateur")},
            inverseJoinColumns = {@JoinColumn(name = "id_role")}
    )
    private List<Role> roles = new ArrayList<>();
}
