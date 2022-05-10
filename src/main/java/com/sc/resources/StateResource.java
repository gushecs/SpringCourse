package com.sc.resources;

import com.sc.domain.DTO.CityDTO;
import com.sc.domain.DTO.StateDTO;
import com.sc.services.CityService;
import com.sc.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

    @Autowired
    StateService service;

    @Autowired
    CityService cityService;

    @GetMapping
    public ResponseEntity<List<StateDTO>> findAllStates () {
        return ResponseEntity.ok().body(service.findAllByOrderByName().stream().map(StateDTO::new).collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}/cities")
    public ResponseEntity<List<CityDTO>> findCitiesByState(@PathVariable Integer id) {
        return ResponseEntity.ok().body(cityService.findCitiesByState(id).stream().map(CityDTO::new).collect(Collectors.toList()));
    }

}
