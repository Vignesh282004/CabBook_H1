package com.booking.App.Services;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Customer;
import com.booking.App.Model.Login;

public interface CustomerloginService {
        	
	public String login(Login login) throws CustomerException,LoginException;
	
	public String logout(String key) throws LoginException;
}
