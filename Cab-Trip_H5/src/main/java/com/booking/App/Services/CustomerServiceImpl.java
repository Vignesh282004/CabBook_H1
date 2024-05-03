package com.booking.App.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Model.Customer;
import com.booking.App.Repositories.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer RegisterCustomer(Customer customer) throws CustomerException {
		return customerRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer,int id) throws CustomerException {
		Optional<Customer> cOptional = customerRepo.findById(id);
		
		if(cOptional.isPresent()) {
			return customerRepo.save(customer);
		}
		throw new CustomerException("Cannot Update Details ....");
	}

	@Override
	public Customer deleteCustomer(Integer customerId) throws CustomerException {
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Customer cannot delete with id :" + customerId +  "" + "Cusotmer Not found"));
		customerRepo.delete(customer);
		return customer;
	}

	@Override
	public List<Customer> allCustomers() throws CustomerException {
		List<Customer> list = customerRepo.findAll();
		
		if(list == null) {
			throw new CustomerException("Customers Not Present .....Please Register Some Customers");
		}
		return list;
	}

	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new CustomerException("Cannot View Customer with id :"+customerId + "" + "Customer not found"));
		return customer;
	}
	
	
}
