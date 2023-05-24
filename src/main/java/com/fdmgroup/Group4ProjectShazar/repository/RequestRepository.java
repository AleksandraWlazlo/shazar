package com.fdmgroup.Group4ProjectShazar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.Group4ProjectShazar.model.Request;
import com.fdmgroup.Group4ProjectShazar.model.User;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	List<Request> findByReceiverOrderBySendDateDesc(User userFromRepository);
}
