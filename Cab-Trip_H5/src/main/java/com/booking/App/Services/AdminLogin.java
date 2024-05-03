package com.booking.App.Services;

import com.booking.App.Exceptions.AdminException;
import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Login;

public interface AdminLogin {

	public String login(Login login) throws LoginException,AdminException,CustomerException;
	
	public String logout(String key) throws LoginException,AdminException,CustomerException;
}
