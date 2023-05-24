package com.fdmgroup.Group4ProjectShazar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Group4ProjectShazar.model.Message;
import com.fdmgroup.Group4ProjectShazar.model.User;


@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	List<Message> findByReceiverOrderBySendDateDesc(User userFromRepository);

	List<Message> findBySenderOrderBySendDateDesc(User userFromDatabase);
	
}
