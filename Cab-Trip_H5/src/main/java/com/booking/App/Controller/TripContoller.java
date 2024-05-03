package com.booking.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.App.Exceptions.CustomerException;
import com.booking.App.Exceptions.DriverException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Exceptions.TripException;
import com.booking.App.Model.TripBooking;
import com.booking.App.Services.TripServiceImpl;

import jakarta.validation.Valid;

@RestController
public class TripContoller {

	@Autowired
	private TripServiceImpl tripServiceImpl;

	@PostMapping("/addTrip/{key}")
	public ResponseEntity<TripBooking> addTripDetailsHandler(@Valid @RequestBody TripBooking tripBooking,
			@PathVariable String key) throws CustomerException, DriverException, LoginException {

		return new ResponseEntity<>(tripServiceImpl.addTrip(tripBooking, key), HttpStatus.OK);
	}

	@PutMapping("/updateTrip/{key}")
	public ResponseEntity<TripBooking> updateTripDetailsHandler(@Valid @RequestBody TripBooking tripBooking,
			@PathVariable String key) throws TripException, DriverException, CustomerException, LoginException {

		return new ResponseEntity<>(tripServiceImpl.updateTrip(tripBooking, key), HttpStatus.OK);
	}

	@DeleteMapping("/deleteTrip/{tripId}/{key}")
	public ResponseEntity<TripBooking> deleteTripHandler(@PathVariable Integer tripId, @PathVariable String key)
			throws TripException, LoginException {

		return new ResponseEntity<>(tripServiceImpl.deleteTrip(tripId, key), HttpStatus.OK);
	}

	@PutMapping("/endTrip/{tripId}/{key}")
	public ResponseEntity<String> endTripHandler(@PathVariable Integer tripId, @PathVariable String key)
			throws TripException, LoginException {

		return new ResponseEntity<>(tripServiceImpl.endTrip(tripId, key), HttpStatus.OK);
	}

	@GetMapping("/tripBill/{tripId}/{key}")
	public ResponseEntity<String> getTripBillHandler(@PathVariable Integer tripId, @PathVariable String key)
			throws CustomerException, TripException, LoginException {

		return new ResponseEntity<>(tripServiceImpl.calculateBill(tripId, key), HttpStatus.OK);
	}

	/* admin methods */

	@GetMapping("/getAllTrips")
	public ResponseEntity<List<TripBooking>> getAllTripsHandler() throws TripException {

		return new ResponseEntity<>(tripServiceImpl.getAllTrips(), HttpStatus.OK);
	}
}
