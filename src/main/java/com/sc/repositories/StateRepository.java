package com.sc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sc.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
