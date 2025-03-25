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
        if(facture.getId_facture() == 0){
           throw new RuntimeException("La facture n'a pas été crée");
        }else {
            return facture;
        }
    }

    @Override
    public List<Facture> findFactureByIdClient(int idClient) {
        return factureRepository.findFactureByIdClient(idClient);
    }

    private void MajMontantFacture(Location loc) {
        long diffInMillies = loc.getDate_retour().getTime() - loc.getDate_debut().getTime();
        long nbJour = diffInMillies / (1000 * 60 * 60 * 24) + 1;
        Float montantLoc = loc.getTarif_jour() * nbJour;
        montantFacture = montantFacture + montantLoc;
    }
}
