package com.eni.ludotheque2.mock;
import com.eni.ludotheque2.bo.*;
import com.eni.ludotheque2.dal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockBD
        //implements CommandLineRunner
{

    @Autowired
    IAdresseRepository repoAd;
    @Autowired
    IClientRepository repoClient;
    @Autowired
    IExemplaireRepository repoExemplaire;
    @Autowired
    IFactureRepository repoFact;
    @Autowired
    IGenreRepository repoGenre;
    @Autowired
    IJeuRepository repoJeu;
    @Autowired
    ILocationRepository repoLoc;
    @Autowired
    IUtilisateurRepository repoUtilisateur;

//    @Override
//    public void run(String... args) throws Exception {
//        alimBD();  // Appel de la méthode alimBD() au démarrage de l'application
//    }

    public void alimBD() {

        //Client
        Client client1 = new Client("nom" + 1 , "prenom" + 1, "emailTest" + 1 + "@eni.fr", "0601010101" );
        client1.setAdresse(new Adresse(1, "rue de" +1, "79300", "Bressuire"));
        Client client2 = new Client("nom" + 2 , "prenom" + 2, "emailTest" + 2 + "@eni.fr", "0601010101" );
        client2.setAdresse(new Adresse(2, "rue de" +2, "79300", "Bressuire"));
        Client client3 = new Client("nom" + 3 , "prenom" + 3, "emailTest" + 3 + "@eni.fr", "0601010101" );
        client3.setAdresse(new Adresse(3, "rue de" +3, "79300", "Bressuire"));
        repoClient.save(client1);
        repoClient.save(client2);
        repoClient.save(client3);

        // Créer plusieurs objets Jeu avec des genres associés
        Genre genre1 = repoGenre.save(new Genre("Aventure"));
        Genre genre2 = repoGenre.save(new Genre("Stratégie"));
        Genre genre3 = repoGenre.save(new Genre("Famille"));
        Genre genre4 = repoGenre.save(new Genre("Action"));

        // Créer un Jeu avec des genres associés
        List<Genre> genresJeu1 = new ArrayList<>();
        genresJeu1.add(genre1);
        genresJeu1.add(genre2);
        List<Genre> genresJeu2 = new ArrayList<>();
        genresJeu2.add(genre2);
        genresJeu2.add(genre3);

        Jeu jeu1 = new Jeu("Stratégie suprême", "REF001", 3.0f);
        jeu1.setAge_min(12);
        jeu1.setDescription("Un jeu de stratégie où vous menez des armées à la guerre.");
        jeu1.setDuree(90);
        jeu1.setGenres(genresJeu1);

        Jeu jeu2 = new Jeu("Skyjo", "REF002", 3.0f);
        jeu2.setAge_min(4);
        jeu2.setDescription("Un jeu de stratégie");
        jeu2.setDuree(60);
        jeu2.setGenres(genresJeu2);
        repoJeu.save(jeu1);
        repoJeu.save(jeu2);

        // Créer les exemplaires
        Exemplaire exemplaire1 = new Exemplaire("CODE11101", true);
        Exemplaire exemplaire2 = new Exemplaire("CODE11102", true);
        Exemplaire exemplaire3 = new Exemplaire("CODE22201", true);
        repoExemplaire.save(exemplaire1);
        repoExemplaire.save(exemplaire2);
        repoExemplaire.save(exemplaire3);

        // Créer deux utilisateurs
        Utilisateur utilisateur1 = new Utilisateur("seb", "seb");
        Utilisateur utilisateur2 = new Utilisateur("admin", "admin");
        utilisateur2.setIsAdmin(true);

        repoUtilisateur.save(utilisateur1);
        repoUtilisateur.save(utilisateur2);

        System.err.println("Mock");

    }
}
