package com.booking.App.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.App.Model.CurrentSessionUser;

public interface CurrentSessionUserRepo extends JpaRepository<CurrentSessionUser,Integer>
{

	public  CurrentSessionUser findByUuid(String key);
}
