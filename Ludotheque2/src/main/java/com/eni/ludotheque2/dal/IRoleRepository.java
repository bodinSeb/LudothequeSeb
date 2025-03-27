package com.eni.ludotheque2.dal;


import com.eni.ludotheque2.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
}
