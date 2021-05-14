package com.lwl.cbook.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lwl.cbook.domain.Contact;

public interface CbookRepo extends MongoRepository<Contact,String> {

	List<Contact> findByNameLike(String str);
	

}
