package com.booking.App.Services;

import java.util.List;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.App.Exceptions.CabException;
import com.booking.App.Exceptions.DriverException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Cab;
import com.booking.App.Model.CabType;
import com.booking.App.Model.Driver;
import com.booking.App.Repositories.CabRepo;
import com.booking.App.Repositories.CurrentSessionUserRepo;
import com.booking.App.Repositories.DriverRepo;

@Service
public class DriverServiceImpl implements DriverService {
	@Autowired
	private DriverRepo driverRepo;
	@Autowired
	private CurrentSessionUserRepo currentSessionUserRepo;
	@Autowired
	private CabRepo cabRepo;

	public boolean isLoginAdmin(String key) throws LoginException {
		String role = currentSessionUserRepo.findByUuid(key).getRole();
		if (role.equals("Admin")) {
			return true;
		}
		throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key....");
	}

	@Override
	public Driver registerDriver(Driver driver, String key) throws DriverException, LoginException {
		if (isLoginAdmin(key)) {
			Cab cab = driver.getCab();
			CabType cabType = cab.getCabType();
			cab.setSittingCapacity(cabType.sittingCapacity());
			cab.setPerkmrate(cabType.providePrice());
			driver.setCab(cab);

			return driverRepo.save(driver);
		}
		throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}

	@Override
	public Driver updateDriver(Driver driver, int driverId, String key) throws DriverException, LoginException, CabException {
			if(isLoginAdmin(key)) {
				Driver driver2=driverRepo.findById(driverId).orElseThrow(() ->  new DriverException("No Driver is Registered with this id :"+ driverId));
				
				Integer cabId = driver.getCab().getCabId();
				
				Cab cab = cabRepo.findById(cabId).orElseThrow(() -> new CabException("No cabs Found"));
				
				CabType cabType = cab.getCabType();
				cab.setSittingCapacity(cabType.sittingCapacity());
				cab.setPerkmrate(cabType.providePrice());
				
				driver2.setCab(cab);
				
				driver2.setLicenceNo(driver.getLicenceNo());
				driver2.setRating(driver.getRating());
				driverRepo.save(driver2);
				return driver2;
			}
			 throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}

	@Override
	public Driver deleteDriver(int driverId, String key) throws DriverException, LoginException {
	     if (isLoginAdmin(key)) {
	            Driver driver = driverRepo.findById(driverId).orElseThrow(() -> new DriverException("Driver doesn't exist with id : " + driverId));
	            driverRepo.delete(driver);
	            return driver;
	        }
	        throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}

	@Override
	public List<Driver> viewBestDrivers() throws DriverException {
		List<Driver> list = driverRepo.findAll();
		return list.stream().filter(s -> s.getRating() > 4.5).toList();
	}

	@Override
	public Driver viewDriver(int driverId, String key) throws DriverException, LoginException {
		if(isLoginAdmin(key)) {
			return driverRepo.findById(driverId).orElseThrow(() -> new DriverException("Driver doesn't exist with this id :" + driverId));
		}
		 throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}

}
