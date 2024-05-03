package com.booking.App.Services;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import net.bytebuddy.utility.RandomString;
import com.booking.App.Exceptions.AdminException;
import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Admin;
import com.booking.App.Model.CurrentSessionUser;
import com.booking.App.Model.Login;
import com.booking.App.Repositories.AdminRepo;
import com.booking.App.Repositories.CurrentSessionUserRepo;

import jakarta.validation.Valid;

@Service
public class AdminLoginImpl implements AdminLogin {

	@Autowired
	private CurrentSessionUserRepo currentSessionUserRepo;
	
	@Autowired
	private AdminRepo adminRepo;

	@Override
	public String login(Login login) throws LoginException, AdminException, CustomerException {
		Admin existingAdmin = adminRepo.findByUsername(login.getUsername());
		
		if(existingAdmin == null) {
		    throw new LoginException("Admin with this username is not available. Please Enter valid Username");
		}
		
		Optional<CurrentSessionUser> validUser = currentSessionUserRepo.findById(existingAdmin.getAdminId()); 
		if (validUser == null) throw new LoginException("Admin already loggedin");
		
		if(existingAdmin.getPassword().equals(login.getPassword())) {
			String key = RandomString.make(4);
			
			CurrentSessionUser currentSessionUser = new CurrentSessionUser(existingAdmin.getAdminId(),key,LocalDateTime.now(),"Admin");
			
			currentSessionUserRepo.save(currentSessionUser);
			
			return currentSessionUser.toString();
		}  else {
            throw new LoginException("Please Enter valid Password");
        }
	}

	@Override
	public String logout(String key) throws LoginException, AdminException, CustomerException {
		
		 CurrentSessionUser currentUser = currentSessionUserRepo.findByUuid(key);
	        if (currentUser == null) {

	            throw new LoginException("User not Logged in with this UUID");

	        }

	        currentSessionUserRepo.delete(currentUser);

	        return "Logged Out !" +  "Candidate Details  --> "  + currentUser;
	    }
	
	

	}
