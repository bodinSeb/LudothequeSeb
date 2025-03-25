package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Facture;
import com.eni.ludotheque2.bo.Location;
import com.eni.ludotheque2.dal.IFactureRepository;
import com.eni.ludotheque2.dal.ILocationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FactureService implements IFactureService {

    @NonNull
    private ILocationRepository locRepository;
    @NonNull
    private IFactureRepository factureRepository;

    private Float montantFacture = 0.0f;

    @Override
    public Facture createFacture(List<Integer> idsLoc, int idClient) {
        Facture facture = new Facture();
        facture.setDate_paiement(new Date());
        List<Location> locations = new ArrayList<>();
        idsLoc.stream().forEach(id -> {
            Location l = locRepository.findById(id).get();
            if(l.getClient().getId_client() == (idClient)){
                locations.add(l);
                MajMontantFacture(l);
            }
        });
        facture.setLocations(locations);
        facture.setMontantFacture(montantFacture);
        factureRepository.save(facture);
        return facture;
    }

    private void MajMontantFacture(Location loc) {
        int nbJour = loc.getDate_retour().getDay() - loc.getDate_debut().getDay() + 1;
        Float montantLoc = loc.getTarif_jour() * nbJour;
        montantFacture = montantFacture + montantLoc;
    }
}
