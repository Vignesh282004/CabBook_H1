package com.booking.App.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.DriverException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Exceptions.TripException;
import com.booking.App.Model.Cab;
import com.booking.App.Model.Customer;
import com.booking.App.Model.Driver;
import com.booking.App.Model.TripBooking;
import com.booking.App.Repositories.CabRepo;
import com.booking.App.Repositories.CurrentSessionUserRepo;
import com.booking.App.Repositories.CustomerRepo;
import com.booking.App.Repositories.DriverRepo;
import com.booking.App.Repositories.TripbookingRepo;

@Service
public class TripServiceImpl implements TripService{
	@Autowired
	TripbookingRepo tripbookingRepo;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	CabRepo cabRepo;
	@Autowired
	DriverRepo driverRepo;
	@Autowired
	CurrentSessionUserRepo currentSessionUserRepo;
	@Autowired
	DriverServiceImpl driverServiceImpl;
	
	public boolean isLoginAdmin(String key) throws LoginException
	{
		String role = currentSessionUserRepo.findByUuid(key).getRole();
		if(role.equals("Admin")) {
				return true;
		}
		throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}
	
	public boolean isLoginCustomer(String key) throws LoginException
	{
		String role = currentSessionUserRepo.findByUuid(key).getRole();
		if(role.equals("Customer")) {
				return true;
		}
		throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
	}
	
	@Override
	public TripBooking addTrip(TripBooking tripBooking, String key)
	throws CustomerException, LoginException, DriverException {
		Integer customerId = currentSessionUserRepo.findByUuid(key).getUserid();
		
		if(isLoginCustomer(key)) {
			Optional<Customer> cOptional = customerRepo.findById(customerId);
			
			if(cOptional.isPresent()) {
				Customer customer = cOptional.get();
				
				List<Driver> drivers = driverServiceImpl.viewBestDrivers();
				
				if(drivers.size() !=0) {
					Driver driver = drivers.get(0);
					
					Cab cab = driver.getCab();
					cab.setCabAvailable(false);
					
					Double fare = tripBooking.getDistanceInKm() * cab.getPerkmrate() + 60;
					
					tripBooking.setBill(fare);
					tripBooking.setDriver(driver);
					tripBooking.setCustomer(customer);
					
					return tripbookingRepo.save(tripBooking);
				}
				throw new DriverException("No drivers available right now! please try again some time.");
			}
			 throw new CustomerException("No customer found with given trip details! please enter valid customer details!");
		}
		   throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
	}
	@Override
	public TripBooking updateTrip(TripBooking tripBooking, String key)
			throws CustomerException, LoginException, DriverException, TripException {
		
		  /*
         * here we can update the details of trip when ever we change the destination and that also
         * reflects the bill amount
         * */
		
		Integer custId = currentSessionUserRepo.findByUuid(key).getUserid();
		if(isLoginCustomer(key)) {
			Optional<Customer> optional = customerRepo.findById(custId);
			
			if(optional.isPresent()) {
				Customer customer = optional.get();
				
				List<Driver> drivers = driverServiceImpl.viewBestDrivers();
				
				if(drivers.size() != 0) {
					Driver driver = drivers.get(0);
					
					Cab cab = driver.getCab();
					cab.setCabAvailable(false);
					
					Double fare1 = tripBooking.getDistanceInKm() * cab.getPerkmrate() + 60;
					tripBooking.setBill(fare1);
					tripBooking.setDriver(driver);
					tripBooking.setCustomer(customer);
					return tripbookingRepo.save(tripBooking);
				}
				  throw new DriverException("No drivers available right now! please try again some time.");
			}
			 throw new CustomerException("No customer found with given trip details! please enter valid customer details!");
		}
		 throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
	}
	@Override
	public TripBooking deleteTrip(Integer Tid, String key) throws LoginException, TripException {
		Integer delcust = currentSessionUserRepo.findByUuid(key).getUserid();
		if(isLoginAdmin(key))
		{
			Optional<TripBooking> opt = tripbookingRepo.findById(Tid);
			
			if(opt.isPresent()) 
			{
				TripBooking tb = opt.get();
				 
				Driver driver = tb.getDriver();
				Cab cab = driver.getCab();
				cab.setCabAvailable(true);
				
				driverRepo.save(driver);
//				tripbookingRepo.delete(tb);
				return tb;
			}
			 throw new TripException("No trips found with given trip details");
		}
		  throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}
	@Override
	public String endTrip(Integer tripId, String key) throws LoginException, TripException {
		 /* this method ends the trip of a customer by finding the trip by its id and
         * making the cab status available for another booking and also generate the
         * bill amount for distance travelled and set the payment status paid*/

        if (isLoginCustomer(key)) {

            Optional<TripBooking> opt = tripbookingRepo.findById(tripId);

            if (opt.isPresent()) {

                TripBooking tripBooking = opt.get();

                Driver driver = tripBooking.getDriver();
                Cab cab = driver.getCab();
                cab.setCabAvailable(true);

                driverRepo.save(driver);
                tripBooking.setDriver(driver);
                tripBooking.setCustomer(tripBooking.getCustomer());
                tripbookingRepo.save(tripBooking);

                return "Your bill amount for the Trip -> " + tripBooking.getBill() * 1.18 + "\n Thanks for choosing our cab services, " + "we hope to see you again";
            }
            throw new TripException("No trip found with given trip id -> " + tripId);
        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
	}
	@Override
	public String calculateBill(Integer tripId, String key) throws LoginException, TripException, CustomerException {
		  /*
         * this method gives the total bill amount of a particular trip of a customer*/


        if (isLoginCustomer(key)) {

            Optional<TripBooking> opt = tripbookingRepo.findById(tripId);

            if (opt.isPresent()) {

                TripBooking tripBooking = opt.get();

                /* 1.18 is GST */

                Double bill = tripBooking.getBill() * 1.18;

                return "Your bill amount for the Trip -> " + bill;
            }
            throw new TripException("No Trip details found with given id -> " + tripId);
        }
        throw new LoginException("Customer with this Key is not LoggedIn. Please provide valid Key ");
	}
	@Override
	public List<TripBooking> getAllTrips() throws TripException {
		return tripbookingRepo.findAll();
	}
	
}
