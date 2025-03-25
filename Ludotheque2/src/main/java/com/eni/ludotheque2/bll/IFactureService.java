package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Facture;

import java.util.List;

public interface IFactureService {
    Facture createFacture(List<Integer> idsLoc, int idClient);
    List<Facture> findFactureByIdClient(int idClient);
}
