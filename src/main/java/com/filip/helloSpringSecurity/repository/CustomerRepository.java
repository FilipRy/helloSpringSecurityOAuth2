package com.filip.helloSpringSecurity.repository;

import com.filip.helloSpringSecurity.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findOneById(Long id);

}
