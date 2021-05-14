package com.lwl.cbook.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwl.cbook.domain.Contact;
import com.lwl.cbook.exception.ContactAlreadyExistsException;
import com.lwl.cbook.exception.ContactNotFoundException;
import com.lwl.cbook.service.CbookService;

@RestController
@RequestMapping("/api/v1/cbook")
public class CbookController {

	@Autowired
	private CbookService cbookService;

	@PostMapping("/addnewContact")
	public Contact addContact(@RequestBody Contact contact) throws ContactAlreadyExistsException {
		return cbookService.addContact(contact);
	}

	@GetMapping("/getContacts")
	public List<Contact> allContacts() {
		return cbookService.allContacts();
	}

	@PutMapping("/updateContact")
	public Contact updateContact(@RequestBody Contact contact) throws ContactAlreadyExistsException {
		return cbookService.updateContact(contact);
	}

	@GetMapping("/search/{str}")
	public List<Contact> search(@PathVariable("str") String str) {
		return cbookService.search(str);
	}

	@GetMapping("/getContactById/{cid}")
	public Contact getContactById(@PathVariable("cid") String cid) throws ContactNotFoundException {
		return cbookService.getContactById(cid);
	}

	@DeleteMapping("/deleteContactById/{cid}")
	public String deleteContact(@PathVariable("cid") String cid) {
		boolean isDeleted = cbookService.deleteContact(cid);
		return isDeleted ? "Contact deleted successfully" : "Contact with id" + cid +"isn't found";
	}

}
