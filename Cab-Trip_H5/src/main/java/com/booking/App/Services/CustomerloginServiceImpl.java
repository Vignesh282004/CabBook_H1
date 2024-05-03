package com.booking.App.Services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.CurrentSessionUser;
import com.booking.App.Model.Customer;
import com.booking.App.Model.Login;
import com.booking.App.Repositories.CurrentSessionUserRepo;
import com.booking.App.Repositories.CustomerRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerloginServiceImpl implements CustomerloginService{

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentSessionUserRepo currentSessionUserRepo;
	@Override
	public String login(Login login) throws CustomerException, LoginException {
		Customer exCustomer = customerRepo.findByUsername(login.getUsername());
		if(exCustomer == null) {
			 throw new LoginException("Customer with this username is not available. Please Enter valid Username");
		}
		   Optional<CurrentSessionUser> validUser = currentSessionUserRepo.findById(exCustomer.getCustomerId());

	        if (validUser == null) throw new LoginException("Customer already logged in");
	        
	        if(exCustomer.getPassword().equals(login.getPassword()))
	        {
	        	String key = RandomString.make(4);
	        	CurrentSessionUser currentSessionUser = new CurrentSessionUser(exCustomer.getCustomerId(),key,LocalDateTime.now(),"Customer");
	        	currentSessionUserRepo.save(currentSessionUser);
	        	return currentSessionUser.toString();
	        }
	        else {
	        	throw new LoginException("Please Enter Valid Password");
	        }
	}

	@Override
	public String logout(String key) throws LoginException {
		CurrentSessionUser currentSessionUser = currentSessionUserRepo.findByUuid(key);
		
		if(currentSessionUser == null) {
			 throw new LoginException("User not Logged in with this UUID");
        }
        currentSessionUserRepo.delete(currentSessionUser);
        return "Logged Out !";
	}
	
	
}
