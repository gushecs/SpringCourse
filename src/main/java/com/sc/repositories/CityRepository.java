package com.sc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sc.domain.City;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM City obj WHERE obj.state.id = :state ORDER BY obj.name")
    List<City> findCities(@Param("state") Integer state);

}
