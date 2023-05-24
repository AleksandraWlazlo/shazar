package com.fdmgroup.Group4ProjectShazar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Address;
import com.fdmgroup.Group4ProjectShazar.repository.AddressRepository;

@Service
public class AddressService {

	AddressRepository addressRepository;
	
	@Autowired
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public Address getAddressFromDatabase(Address address) {
		Address addressFromDatabase = addressRepository.searchForAddress(address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry());

		return addressFromDatabase;
	}
		
	public void saveAddress(Address address) {
		Address addressFromDatabase = getAddressFromDatabase(address);
		
		if(addressFromDatabase == null) {
			addressRepository.save(address);
		}
		 
	}
	
}
