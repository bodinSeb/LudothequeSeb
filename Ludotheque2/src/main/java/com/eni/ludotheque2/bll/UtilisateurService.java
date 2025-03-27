package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Adresse;
import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Utilisateur;
import com.eni.ludotheque2.dal.IAdresseRepository;
import com.eni.ludotheque2.dal.IClientRepository;
import com.eni.ludotheque2.dal.IUtilisateurRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UtilisateurService implements IUtilisateurService{

    @NonNull
    private IUtilisateurRepository utilRepository;

    @Override
    public void ajouterUtilisateur(Utilisateur util) {
        utilRepository.save(util);
    }

    @Override
    public List<Utilisateur> findAllUtilisateurs() {
        return utilRepository.findAll();
    }

}
