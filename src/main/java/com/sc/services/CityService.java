package com.sc.services;

import com.sc.domain.City;
import com.sc.domain.DTO.CityDTO;
import com.sc.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository repository;

    public List<City> findCitiesByState(Integer state) {
        return repository.findCities(state);
    }
}
