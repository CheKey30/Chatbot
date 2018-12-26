package com.octopedia.demo.service;

import com.octopedia.demo.domain.Customer;
import com.octopedia.demo.repository.CustomerRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerService {
    private CustomerRep customerRep;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public CustomerService(CustomerRep customerRep, EntityManager entityManager){
        this.customerRep = customerRep;
        this.em = entityManager;
    }

    public Customer save(Customer customer){
        return this.customerRep.save(customer);
    }
    public Customer update(Customer customer){
        return em.merge(customer);
    }

    public Customer getCustomerByEmail(String email){
        return this.customerRep.findByEmail(email);
    }
}
