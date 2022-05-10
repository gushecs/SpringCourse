package com.sc.repositories;

import com.sc.domain.DTO.StateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sc.domain.State;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

    @Transactional(readOnly = true)
    List<State> findAllByOrderByName();

}
