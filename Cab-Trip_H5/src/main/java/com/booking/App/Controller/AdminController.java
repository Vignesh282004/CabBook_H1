package com.booking.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.App.Exceptions.AdminException;
import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Admin;
import com.booking.App.Model.Login;
import com.booking.App.Repositories.AdminRepo;
import com.booking.App.Repositories.CurrentSessionUserRepo;
import com.booking.App.Services.AdminLogin;
import com.booking.App.Services.AdminLoginImpl;
import com.booking.App.Services.AdminServImpl;

import jakarta.validation.Valid;

@RestController
public class AdminController {

	@Autowired
	private AdminLoginImpl adminLoginImpl;
	
	@Autowired
	private AdminServImpl adminServImpl;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private CurrentSessionUserRepo currentSessionUserRepo;
	
	@GetMapping("/check")
	public String check() {
		return "Checked";
	}
	@PostMapping("Admin")
	public ResponseEntity<Admin> createAdmin(@Valid @RequestBody Admin admin) throws AdminException
	{
		Admin newAdmin = adminRepo.findByUsername(admin.getUsername());
		
		if(newAdmin !=null) {
		     throw new AdminException("Please change Username. Admin is registered with this username. ");
        }
        return new ResponseEntity<>(adminServImpl.createUser(admin), HttpStatus.CREATED);
    }
	
	@PutMapping("/Admin")
	public ResponseEntity<Admin> UpdateAdmin(@Valid @RequestBody Admin admin,String key) throws AdminException
	{
		return new ResponseEntity<>(adminServImpl.updateUser(admin,key),HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/loginAdmin")
	public ResponseEntity<String> adminLogin(@Valid @RequestBody Login login) throws LoginException,AdminException,CustomerException
	{
		return new ResponseEntity<>(adminLoginImpl.login(login),HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> adminLogout(@RequestParam(required = false)String key) throws LoginException,AdminException,CustomerException
	{
		return new ResponseEntity<String>(adminLoginImpl.logout(key),HttpStatus.OK);
	}
	
	@GetMapping("/loginAsAdmin/{key}")
	public ResponseEntity<String> loginasAdmin(@PathVariable String key)  throws LoginException
	{
		String role = currentSessionUserRepo.findByUuid(key).getRole();
	
		if(role.equals("Admin")) {
			  return new ResponseEntity<>("Admin found", HttpStatus.ACCEPTED);
		}
		throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key");
	}
	
	
	
	
}
