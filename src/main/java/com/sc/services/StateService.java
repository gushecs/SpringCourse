package com.sc.services;

import com.sc.domain.DTO.StateDTO;
import com.sc.domain.State;
import com.sc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository repository;

    public List<State> findAllByOrderByName(){
        return repository.findAllByOrderByName();
    }

}
