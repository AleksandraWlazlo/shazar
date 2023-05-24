package com.fdmgroup.Group4ProjectShazar.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.Group4ProjectShazar.model.Address;
import com.fdmgroup.Group4ProjectShazar.repository.AddressRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AddressServiceTest {

	@InjectMocks
	AddressService service;

	@MockBean
	AddressRepository mockRepository;

	@Mock
	Address mockAddress;

	@Test
	public void test_getAddressFromDatabase_callsSearchForAddressMethodOfAddressRepository() {
		service.getAddressFromDatabase(mockAddress);

		verify(mockRepository, times(1)).searchForAddress(mockAddress.getStreet(), mockAddress.getHouseNumber(),
				mockAddress.getPostalCode(), mockAddress.getCity(), mockAddress.getCountry());

	}

	@Test
	public void test_saveAddressOfAddressService_callsGetAddressFromDatabase_ifReturnsNull_andThenCallsSaveMethodOfAddressRepository() {
		service.saveAddress(mockAddress);

		verify(mockRepository, times(1)).save(mockAddress);

	}

	@Test
	public void test_saveAddressOfAddressService_callsGetAddressFromDatabase_ifReturnsNotNull_doesNotCallSaveMethodOfAddressRepository() {

		Address address = new Address("", "", 0, "", "");

		when(service.getAddressFromDatabase(mockAddress)).thenReturn(address);

		service.saveAddress(mockAddress);

		verify(mockRepository, times(0)).save(address);

	}

}
