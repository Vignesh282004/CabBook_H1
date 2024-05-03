package com.booking.App.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.App.Exceptions.AdminException;
import com.booking.App.Model.Admin;
import com.booking.App.Model.CurrentSessionUser;
import com.booking.App.Repositories.AdminRepo;
import com.booking.App.Repositories.CurrentSessionUserRepo;

@Service
public class AdminServImpl implements AdminService {
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CurrentSessionUserRepo currentSessionUserRepo;
	
	public Admin createUser(Admin admin) throws AdminException {
		return adminRepo.save(admin);
	}
	
	public Admin updateUser(Admin admin, String key) throws AdminException {
		CurrentSessionUser logged_in_user = currentSessionUserRepo.findByUuid(key);
		if(logged_in_user == null) {
			throw new AdminException("Please provide a valid key to update Admin");
		}
		if(logged_in_user.getUserid() == admin.getAdminId()) {
			return adminRepo.save(admin);
		}
		else {
			throw new AdminException("Invalid Admin Details");
		}
	}
	
	
}
