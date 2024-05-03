package com.booking.App.Services;

import java.util.List;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Model.Customer;

public interface CustomerService {

	Customer RegisterCustomer(Customer customer) throws CustomerException;
	
	Customer updateCustomer(Customer customer,int id) throws CustomerException;
	
	Customer deleteCustomer(Integer customerId) throws CustomerException;
	
	List<Customer> allCustomers() throws CustomerException;
	
	Customer viewCustomer(Integer customerId) throws CustomerException; 
}
