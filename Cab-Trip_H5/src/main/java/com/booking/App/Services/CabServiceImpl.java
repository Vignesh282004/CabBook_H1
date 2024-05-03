package com.booking.App.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.App.Exceptions.CabException;
import com.booking.App.Exceptions.LoginException;
import com.booking.App.Model.Cab;
import com.booking.App.Model.CabType;
import com.booking.App.Repositories.CabRepo;
import com.booking.App.Repositories.CurrentSessionUserRepo;

@Service
public class CabServiceImpl implements CabService{

	@Autowired
	private CabRepo cabRepo;
	@Autowired
	private CurrentSessionUserRepo currentSessionUserRepo;
	
	public boolean isAdminLogin(String key) throws LoginException
	{
		String role = currentSessionUserRepo.findByUuid(key).getRole();
		if(role.equals("Admin")) {
			return true;
		}
	    throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}
	
	
	public Cab registerCab(Cab cab, String key) throws CabException, LoginException {
		
		if(isAdminLogin(key)) {
			CabType cabType = cab.getCabType();
			cab.setPerkmrate(cabType.providePrice());
			cab.setSittingCapacity(cabType.sittingCapacity());
			return cabRepo.save(cab);
		}
		 throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}
	@Override
	public Cab updateCab(int cabId, String key, Cab cab) throws CabException,LoginException {
		if(isAdminLogin(key)) {
			Cab cab2 = cabRepo.findById(cabId).orElseThrow(() -> new CabException("Cab dont eXist with this id " + cabId));
			CabType cabType = cab.getCabType();
			cab.setPerkmrate(cabType.providePrice());
			cab.setSittingCapacity(cabType.sittingCapacity());
			return cabRepo.save(cab);
		}
		 throw new LoginException("Admin with this Key is not LoggedIn. Please provide valid Key ");
	}
	@Override
	public Cab deleteCab(int cabId) throws CabException {
		   Cab cab = cabRepo.findById(cabId).orElseThrow(() -> new CabException("Cab does not exist with ID" + cabId));
	        cabRepo.delete(cab);
	        return cab;
	}
	@Override
	public List<Cab> viewCabs(CabType cabType) throws CabException {
		List<Cab> allcabs = cabRepo.findAllByCabType(cabType);
		return allcabs;
	}
	@Override
	public Integer countCabsOfType(CabType cabType) throws CabException {
		int size = cabRepo.findAllByCabType(cabType).size();
		return size;
	}
	
	
}
