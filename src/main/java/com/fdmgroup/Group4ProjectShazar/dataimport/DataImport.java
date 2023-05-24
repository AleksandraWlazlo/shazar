package com.fdmgroup.Group4ProjectShazar.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdmgroup.Group4ProjectShazar.model.Address;
import com.fdmgroup.Group4ProjectShazar.model.Role;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.UserRepository;
import com.fdmgroup.Group4ProjectShazar.service.AddressService;

@Component
public class DataImport implements ApplicationRunner {

	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;
	
	private AddressService addressService;
	
	@Autowired
	public DataImport(UserRepository userRepository, PasswordEncoder passwordEncoder, AddressService addressService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;	
		this.addressService = addressService;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(!userRepository.findByUsername("admin").isPresent()) {
			Role roleAdmin = new Role("Admin");
			Role roleCustomer = new Role("Customer");
		
			Address defaultAddress = new Address("Default Street", "1", 12345, "Default City", "Default Country");
			addressService.saveAddress(defaultAddress);
			
			User admin = new User("admin", passwordEncoder.encode("123"), "Admin", "User", "1234567", "admin@shazar.com", "defaultCity", 5.0, 4, defaultAddress, roleAdmin);
			userRepository.save(admin);
		
			User customer = new User("customer", passwordEncoder.encode("321"), "Default", "User", "9876543", "user@abc.com", "defaultCity", 4.2, 10, defaultAddress, roleCustomer);
			userRepository.save(customer);
		}
		
	}
	
	
}
