package com.booking.App.Repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.App.Model.Customer;
import com.booking.App.Model.TripBooking;

public interface TripbookingRepo extends JpaRepository<TripBooking,Integer> {

	public Set<TripBooking> findByCustomer(Customer customer);
}
