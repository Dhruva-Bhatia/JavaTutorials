package com.group.classes;

import java.io.Serializable;
import java.util.ArrayList;

import com.contacts.classes.Contact;

public class Group implements Serializable{
	private static final long serialVersionUID = 1L;

	public ArrayList<String> contactNames = new ArrayList<String>();
	
	public String name;
	
	public Group(String name, ArrayList<Contact> contacts) {
		this.name = name;
		for(Contact contact: contacts) {
			contactNames.add(contact.fullName);
		}
	}
}
