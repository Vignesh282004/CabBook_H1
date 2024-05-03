package com.booking.App.Services;

import java.util.List;

import com.booking.App.Exceptions.CabException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Cab;
import com.booking.App.Model.CabType;

public interface CabService {

	public Cab registerCab(Cab cab,String key) throws CabException,LoginException;
	
	public Cab updateCab(int cabId,String key,Cab cab) throws CabException,LoginException;
	
	public Cab deleteCab(int cabId) throws CabException;
	
	public List<Cab> viewCabs(CabType cabType) throws CabException;
	
	public Integer countCabsOfType(CabType cabType) throws CabException;
}
