package com.booking.App.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cabId;
	
	@NotNull
	private CabType cabType;
	private double perkmrate;
	
	private Integer sittingCapacity;
	
	private Boolean cabAvailable = true;
	
	@NotNull
	private String registrationNumber;
	
	@JsonIgnore
	@OneToOne(mappedBy = "cab",cascade = CascadeType.ALL)
	private Driver driver;
}
