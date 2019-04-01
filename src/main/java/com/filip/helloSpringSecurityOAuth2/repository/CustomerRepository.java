package com.filip.helloSpringSecurityOAuth2.repository;

import com.filip.helloSpringSecurityOAuth2.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findOneById(Long id);

}
