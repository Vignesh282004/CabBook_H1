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
import com.booking.App.Exceptions.DriverException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Driver;
import com.booking.App.Services.DriverServiceImpl;

import jakarta.validation.Valid;

@RestController
public class DriverController {

	@Autowired
	private DriverServiceImpl ds;

	@PostMapping("/addDriver/{key}")
    public ResponseEntity<Driver> addDriverDetailsHandler(@Valid @RequestBody Driver driver, @PathVariable String key) throws DriverException, LoginException {

        return new ResponseEntity<>(ds.registerDriver(driver, key), HttpStatus.OK);
    }

    @PutMapping("/updateDriver/{driverId}/{key}")
    public ResponseEntity<Driver> updateDriverDetailsHandler(@Valid @RequestBody Driver driver, @PathVariable Integer driverId, @PathVariable String key) throws DriverException, LoginException, CabException {

        return new ResponseEntity<>(ds.updateDriver(driver, driverId, key), HttpStatus.OK);
    }

    @DeleteMapping("/deleteDriver/{driverId}/{key}")
    public ResponseEntity<Driver> deleteDriverDetailsHandler(@PathVariable Integer driverId, @PathVariable String key) throws DriverException, LoginException {

        return new ResponseEntity<>(ds.deleteDriver(driverId, key), HttpStatus.OK);
    }

    @GetMapping("/getBestDrivers")
    public ResponseEntity<List<Driver>> getBestDriversListHandler() throws DriverException {

        return new ResponseEntity<>(ds.viewBestDrivers(), HttpStatus.OK);
    }

    @GetMapping("/viewDriver/{driverId}/{key}")
    public ResponseEntity<Driver> getBestDriversListHandler(@PathVariable Integer driverId, @PathVariable String key) throws DriverException, LoginException {

        return new ResponseEntity<>(ds.viewDriver(driverId, key), HttpStatus.OK);
    }
}
