package com.sc.domain.DTO;

import com.sc.domain.State;

import javax.persistence.Entity;
import java.io.Serializable;

public class StateDTO implements Serializable {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StateDTO() {}

    public StateDTO(State state) {
        this.name = state.getName();
        this.id = state.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
