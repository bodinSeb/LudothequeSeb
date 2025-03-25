package com.eni.ludotheque2.bll;

import com.eni.ludotheque2.bo.Client;
import com.eni.ludotheque2.bo.Exemplaire;
import com.eni.ludotheque2.bo.Location;

public interface ILocationService {
    void creationLocation(Client client, Exemplaire exemplaire);
    void retourLocation(Location location);
}
