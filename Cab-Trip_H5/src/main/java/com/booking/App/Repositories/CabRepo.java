package com.booking.App.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.App.Model.Cab;
import com.booking.App.Model.CabType;
@Repository
public interface CabRepo extends JpaRepository<Cab,Integer> {

	 List<Cab> findAllByCabType(CabType cabType);
}
