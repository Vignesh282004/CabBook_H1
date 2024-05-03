package com.booking.App.Services;

import java.util.List;

import com.booking.App.Exceptions.CabException;
import com.booking.App.Exceptions.DriverException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Driver;

public interface DriverService {


    public Driver registerDriver(Driver driver, String key) throws DriverException, LoginException;

    public Driver updateDriver(Driver driver, int driverId, String key) throws DriverException, LoginException, CabException;

    public Driver deleteDriver(int driverId, String key) throws DriverException, LoginException;

    public List<Driver> viewBestDrivers() throws DriverException;

    public Driver viewDriver(int driverId, String key) throws DriverException, LoginException;
}
