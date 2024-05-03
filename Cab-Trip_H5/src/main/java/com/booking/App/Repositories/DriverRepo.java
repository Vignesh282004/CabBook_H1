package com.booking.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.App.Model.Driver;

public interface DriverRepo extends JpaRepository<Driver,Integer> {

}
