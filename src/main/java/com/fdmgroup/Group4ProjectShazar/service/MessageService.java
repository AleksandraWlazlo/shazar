package com.fdmgroup.Group4ProjectShazar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.Group4ProjectShazar.model.Message;
import com.fdmgroup.Group4ProjectShazar.model.User;
import com.fdmgroup.Group4ProjectShazar.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public void saveMessage(Message message) {
		messageRepository.save(message);
	}

	public List<Message> listAllReceivedMessages(User userFromDatabase) {
		
		return messageRepository.findByReceiverOrderBySendDateDesc(userFromDatabase);
	}

	public List<Message> listAllSentMessages(User userFromDatabase) {
		
		return messageRepository.findBySenderOrderBySendDateDesc(userFromDatabase);
	}
	
}
