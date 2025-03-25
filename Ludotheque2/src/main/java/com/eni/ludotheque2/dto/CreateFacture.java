package com.eni.ludotheque2.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateFacture {
    private List<Integer> idsLoc;
    private int idClient;
}
