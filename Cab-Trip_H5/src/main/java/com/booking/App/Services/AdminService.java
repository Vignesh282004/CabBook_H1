package com.booking.App.Services;

import com.booking.App.Exceptions.AdminException;
import com.booking.App.Model.Admin;

public interface AdminService {


    public Admin createUser(Admin admin)throws AdminException;
	
    public Admin updateUser(Admin admin,String key)throws AdminException;
}
