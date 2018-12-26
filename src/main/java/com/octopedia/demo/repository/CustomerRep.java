package com.octopedia.demo.repository;

import com.octopedia.demo.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRep extends CrudRepository<Customer,Integer> {
    Customer findByEmail(String email);
}
