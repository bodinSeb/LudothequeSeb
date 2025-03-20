package com.eni.ludotheque2.dal;

import com.eni.ludotheque2.bo.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepository extends JpaRepository<Location, Integer> {
}
