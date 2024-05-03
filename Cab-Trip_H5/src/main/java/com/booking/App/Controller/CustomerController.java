package com.booking.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Customer;
import com.booking.App.Model.Login;
import com.booking.App.Repositories.CustomerRepo;
import com.booking.App.Services.CustomerServiceImpl;
import com.booking.App.Services.CustomerloginServiceImpl;

//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
public class CustomerController {
	@Autowired
	private CustomerRepo cRepo;
	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@Autowired
	private CustomerloginServiceImpl customerloginServiceImpl;
	@PostMapping("/Customer")
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		Customer existingCustomer = cRepo.findByUsername(customer.getUsername());

		if (existingCustomer != null) {
			throw new CustomerException("Customer is registered with this username .....");
		}
		return new ResponseEntity<>(customerServiceImpl.RegisterCustomer(customer), HttpStatus.CREATED);
	}

	@PutMapping("/UpdateCustomer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,
			@PathVariable Integer customerId) throws CustomerException {
		return new ResponseEntity<>(customerServiceImpl.updateCustomer(customer, customerId), HttpStatus.OK);
	}
	
	@PostMapping("/CustomerLogin")
	public ResponseEntity<String> loginCustomer(@Valid @RequestBody Login login) throws CustomerException,LoginException
	{
		return new ResponseEntity<>(customerloginServiceImpl.login(login),HttpStatus.ACCEPTED);
	}
	@GetMapping("/CustomerLogout")
	public ResponseEntity<String> logoutCustomer(@RequestParam(required = false)String key) throws CustomerException,LoginException
	{
		return new ResponseEntity<String>(customerloginServiceImpl.logout(key),HttpStatus.OK);
	}
}



