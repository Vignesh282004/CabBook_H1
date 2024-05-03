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

import com.booking.App.Exceptions.CabException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Cab;
import com.booking.App.Model.CabType;
import com.booking.App.Services.CabServiceImpl;

import jakarta.validation.Valid;

@RestController
public class CabController {
	@Autowired
	private CabServiceImpl cabServiceImpl;

	@PostMapping("/registercab/{key}")
	public ResponseEntity<Cab> registerCab(@Valid @RequestBody Cab cab, @PathVariable String key)
			throws CabException, LoginException {
		return new ResponseEntity<Cab>(cabServiceImpl.registerCab(cab, key), HttpStatus.CREATED);
	}

	@PutMapping("/updatecab/{cabId}/{key}")
	public ResponseEntity<Cab> updateCab(@PathVariable Integer cabId, @PathVariable String key, @RequestBody Cab cab)
			throws CabException, LoginException {
		return new ResponseEntity<Cab>(cabServiceImpl.updateCab(cabId, key, cab), HttpStatus.OK);
	}

	@DeleteMapping("/deletecab/{cabId}")
	public ResponseEntity<Cab> deleteCabHandler(@PathVariable("cabId") int cabId) throws CabException {
		return new ResponseEntity<>(cabServiceImpl.deleteCab(cabId), HttpStatus.OK);
	}

	@GetMapping("/cabs/{carType}")
	public ResponseEntity<List<Cab>> viewCabs(@PathVariable("carType") CabType carType) throws CabException {
		return new ResponseEntity<>(cabServiceImpl.viewCabs(carType), HttpStatus.OK);
	}

	@GetMapping("/cab/{carType}")
	public ResponseEntity<Integer> countCabs(@PathVariable("carType") CabType carType) throws CabException {
		return new ResponseEntity<Integer>(cabServiceImpl.countCabsOfType(carType), HttpStatus.OK);
	}
}
