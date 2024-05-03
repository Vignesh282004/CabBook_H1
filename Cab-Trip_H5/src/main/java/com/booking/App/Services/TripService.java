package com.booking.App.Services;

import java.util.List;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.DriverException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Exceptions.TripException;
import com.booking.App.Model.TripBooking;

public interface TripService {

	public TripBooking addTrip(TripBooking tripBooking,String key) throws CustomerException,LoginException,DriverException;
	
	public TripBooking updateTrip(TripBooking tripBooking,String key) throws CustomerException,LoginException,DriverException,TripException;
	
	public TripBooking deleteTrip(Integer Tid,String key) throws LoginException,TripException;
	
	public String endTrip(Integer tripId,String key) throws LoginException,TripException;
	
	public String calculateBill(Integer tripId,String key) throws LoginException,TripException,CustomerException;
	
	public List<TripBooking> getAllTrips() throws TripException;
}
