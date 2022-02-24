package com.sc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sc.domain.Adress;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Integer>{

}
