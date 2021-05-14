package com.lwl.cbook.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.lwl.cbook.domain.Contact;
import com.lwl.cbook.exception.ContactAlreadyExistsException;
import com.lwl.cbook.exception.ContactNotFoundException;
import com.lwl.cbook.repo.CbookRepo;

@Service
public class CbookServiceImpl implements CbookService {

	private static final Logger log = LoggerFactory.getLogger(CbookServiceImpl.class);

	@Autowired
	private CbookRepo CbookRepo;

	@Override
	public Contact addContact(Contact contact) throws ContactAlreadyExistsException {

		Assert.notNull(contact, "Contact object can't be null");
		Assert.notNull(contact.getName(), "Contact name can't be empty");
		Assert.notNull(contact.getMobile(), "Contact mobile number can't empty");
		Contact savedContact = CbookRepo.save(contact);

		log.info("Contact saved with id {}", contact.getId());

		return savedContact;
	}

	@Override
	public Contact updateContact(Contact contact) throws ContactAlreadyExistsException {

		Assert.notNull(contact, "Contact object can't be null");
		Assert.notNull(contact.getName(), "Contact name can't be empty");
		Assert.notNull(contact.getMobile(), "Contact mobile number can't empty");
		Assert.notNull(contact.getId(), "Contact isn't present");

		Contact updatedContact = CbookRepo.save(contact);

		log.info("Contact successfully updated with id {}", updatedContact.getId());

		return updatedContact;
	}

	@Override
	public boolean deleteContact(String cid) {

		Assert.notNull(cid, "Contact id can't be null or empty");
		Optional<Contact> contact = CbookRepo.findById(cid);
		if (contact.isPresent()) {
			CbookRepo.deleteById(cid);
			log.info("Contact with id {} deleted successfully",cid);
			return true;
		}
		log.info("Contact with id {} could't be deleted",cid);
		return false;
	}

	@Override
	public List<Contact> allContacts() {

		List<Contact> allContacts = CbookRepo.findAll();
		log.info("Number of contacts {}", allContacts.size());
		return allContacts;
	}

	@Override
	public List<Contact> search(String str) {

		Assert.notNull(str, "Search string cannot be null");
		List<Contact> contacts = CbookRepo.findByNameLike(str);
		log.info("Total {} contacts found for given search string : {}", contacts.size(), str);
		return contacts;
	}

	@Override
	public Contact getContactById(String cid) throws ContactNotFoundException {

		Assert.notNull(cid, "Contact id can't be null or empty");
		Optional<Contact> contact = CbookRepo.findById(cid);
		if (contact.isPresent()) {
			return contact.get();
		}

		log.info("Contact isn't found with given id {}", cid);

		throw new ContactNotFoundException("Contact not found with given id {}" + cid);

	}

}
