package com.sc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sc.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
