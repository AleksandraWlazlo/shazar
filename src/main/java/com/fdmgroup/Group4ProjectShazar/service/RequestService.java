package com.fdmgroup.Group4ProjectShazar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Request;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.RequestRepository;

@Service
public class RequestService {

	private RequestRepository requestRepository;
	
	@Autowired
	public RequestService(RequestRepository requestRepository) {
		this.requestRepository = requestRepository;
	}

	public void saveRequest(Request request) {
		requestRepository.save(request);
	}
	
	public List<Request> listRequests(User user) {
		
		return requestRepository.findByReceiverOrderBySendDateDesc(user);
		
	}
	
	public void deleteRequest(int requestId) {
		
		requestRepository.deleteById(requestId);
	}
	
	public Request getRequestFromDatabase(int requestId) {
		Optional<Request> requestFromDatabase = requestRepository.findById(requestId);

		return requestFromDatabase.orElse(new Request());
	}
	
	
}
