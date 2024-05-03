package com.booking.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Model.Customer;

public interface CustomerRepo  extends JpaRepository<Customer,Integer>{

	public Customer findByUsername(String username) throws CustomerException;
}
