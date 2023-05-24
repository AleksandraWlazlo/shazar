package com.fdmgroup.Group4ProjectShazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Group4ProjectShazar.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query("SELECT a from Address a WHERE upper(a.street) = upper(:street) AND upper(a.houseNumber) = upper(:houseNumber) AND upper(a.postalCode) = upper(:postalCode) AND upper(a.city) = upper(:city) AND upper(a.country) = upper(:country)")
	Address searchForAddress(@Param("street") String street, @Param("houseNumber") String houseNumber, @Param("postalCode") int postalCode, @Param("city") String city, @Param("country") String country);
	
}
