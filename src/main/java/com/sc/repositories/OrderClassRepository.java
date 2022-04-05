package com.sc.repositories;

import com.sc.domain.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sc.domain.OrderClass;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderClassRepository extends JpaRepository<OrderClass, Integer>{

    @Transactional(readOnly=true)
    Page<OrderClass> findByClient(Client client, Pageable pageable);
}
