package com.sc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sc.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
