package com.booking.App.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Data
public class TripBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer TripBookingId;
	
	@NonNull
	private String fromlocation;
	
	@NonNull
	private String tolocation;
	private LocalDate fromDate;
	private LocalDate ToDate;
	 @NonNull
	    private Double distanceInKm;
	@JsonIgnore
	private Double Bill;
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Customer customer;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Driver driver;

}
