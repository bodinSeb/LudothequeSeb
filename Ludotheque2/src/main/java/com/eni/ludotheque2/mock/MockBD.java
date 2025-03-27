package com.eni.ludotheque2.mock;
import com.eni.ludotheque2.bo.*;
import com.eni.ludotheque2.dal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        List<Genre> genresJeu3 = new ArrayList<>();
        genresJeu3.add(genre3);
        genresJeu3.add(genre4);

        Jeu jeu1 = new Jeu("Stratégie suprême", "REF001", 1.0f);
        jeu1.setAge_min(12);
        jeu1.setDescription("Un jeu de stratégie où vous menez des armées à la guerre.");
        jeu1.setDuree(90);
        jeu1.setGenres(genresJeu1);

        Jeu jeu2 = new Jeu("Skyjo", "REF002", 2.0f);
        jeu2.setAge_min(4);
        jeu2.setDescription("Un jeu de stratégie");
        jeu2.setDuree(60);
        jeu2.setGenres(genresJeu2);

        Jeu jeu3 = new Jeu("Monopoly", "REF003", 3.0f);
        jeu3.setAge_min(4);
        jeu3.setDescription("Un jeu de stratégie");
        jeu3.setDuree(180);
        jeu3.setGenres(genresJeu3);
        repoJeu.save(jeu1);
        repoJeu.save(jeu2);
        repoJeu.save(jeu3);

        // Créer les exemplaires
        Exemplaire exemplaire1 = new Exemplaire("CODEJeu101", true, jeu1);
        Exemplaire exemplaire2 = new Exemplaire("CODEJeu102", true, jeu1);
        Exemplaire exemplaire3 = new Exemplaire("CODEJeu201", true, jeu2);
        Exemplaire exemplaire4 = new Exemplaire("CODEJeu301", true, jeu3);
        Exemplaire exemplaire5 = new Exemplaire("CODEJeu302", true, jeu3);
        exemplaire1.setLouable(false);
        exemplaire2.setLouable(false);
        exemplaire3.setLouable(false);
        exemplaire4.setLouable(false);

        repoExemplaire.save(exemplaire1);
        repoExemplaire.save(exemplaire2);
        repoExemplaire.save(exemplaire3);
        repoExemplaire.save(exemplaire4);
        repoExemplaire.save(exemplaire5);

        // Créer deux utilisateurs
        Utilisateur utilisateur1 = new Utilisateur("seb", "seb");
        Utilisateur utilisateur2 = new Utilisateur("admin", "admin");
        utilisateur2.setIsAdmin(true);

        repoUtilisateur.save(utilisateur1);
        repoUtilisateur.save(utilisateur2);

        //Création de Locations
        Location location1 = new Location();
        Date date1 = stringToDate("2025-02-25");
        System.err.println("DATE1: " + date1);
        location1.setDate_debut(date1);
        location1.setExemplaire(exemplaire1);
        location1.setTarif_jour(exemplaire1.getJeu().getTarif_jour());
        location1.setClient(client1);

        Location location2 = new Location();
        Date date2 = stringToDate("2025-03-01");
        location2.setDate_debut(date1);
        location2.setDate_retour(date2);
        location2.setExemplaire(exemplaire3);
        location2.setTarif_jour(exemplaire3.getJeu().getTarif_jour());
        location2.setClient(client1);

        Location location3 = new Location();
        location3.setDate_debut(date1);
        location3.setExemplaire(exemplaire4);
        location3.setTarif_jour(exemplaire4.getJeu().getTarif_jour());
        location3.setClient(client2);

        Location location4 = new Location();
        location4.setDate_debut(date1);
        location4.setExemplaire(exemplaire2);
        location4.setTarif_jour(exemplaire2.getJeu().getTarif_jour());
        location4.setClient(client2);

        repoLoc.save(location1);
        repoLoc.save(location2);
        repoLoc.save(location3);
        repoLoc.save(location4);

        System.err.println("Mock");

    }

    private static Date stringToDate(String dateString) {
        String format = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Erreur de format de date : " + e.getMessage());
        }
        return date;
    }
}
