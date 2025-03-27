package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Utilisateur;

import java.util.List;

public interface IUtilisateurService {
    void ajouterUtilisateur(Utilisateur util);
    List<Utilisateur> findAllUtilisateurs();
    Utilisateur findByLogin(String login);
}
