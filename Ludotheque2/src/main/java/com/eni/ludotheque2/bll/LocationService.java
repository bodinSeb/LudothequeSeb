package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Exemplaire;
import com.eni.ludotheque2.bo.Jeu;
import com.eni.ludotheque2.bo.Location;
import com.eni.ludotheque2.dal.IExemplaireRepository;
import com.eni.ludotheque2.dal.IJeuRepository;
import com.eni.ludotheque2.dal.ILocationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService implements ILocationService{

    @NonNull
    private ILocationRepository locRepository;

    @NonNull
    private IExemplaireRepository exempRepository;

    @Override
    public void creationLocation(Client client, Exemplaire exemplaire) {
        List<Location> locsEnCours = locRepository.findLocationByIdClientAndTerminee(client.getId_client());
        if(locsEnCours.size() >= 5){
            throw new IllegalArgumentException("Vous avez déjà 5 locations en cours");
        } else if(!exemplaire.isLouable()) {
            throw new IllegalArgumentException("L'exemplaire n'est pas louable ou est déjà loué");
        } else {
            Location location = new Location();
            location.setClient(client);
            location.setExemplaire(exemplaire);
            location.setTarif_jour(exemplaire.getJeu().getTarif_jour());
            location.setDate_debut(new Date());
            try {
                locRepository.save(location);
                exemplaire.setLouable(false);
                exempRepository.save(exemplaire);

            } catch (Exception e) {
                throw new RuntimeException(e + "La location n'a pas été créée");
            }
        }
    }

    @Override
    public void retourLocation(Location location) {
        location.setDate_retour(new Date());

    }
}
