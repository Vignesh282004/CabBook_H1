package com.booking.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.App.Exceptions.AdminException;
import com.booking.App.Model.Admin;

public interface AdminRepo extends JpaRepository<Admin,Integer>
{
	
	public Admin findByUsername(String username) throws AdminException;
	
}
