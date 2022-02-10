package com.groups.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import com.contacts.classes.Contact;

public class Group implements Serializable{
	private static final long serialVersionUID = 1L;

	public HashSet<Long> contacts = new HashSet<Long>();
	
	public String name;
	
	public Group(String name) {
		this.name = name;
	}
	
	public void addContact(long contact) {
		contacts.add(contact);
	}
	
	public void removeContact(long contact) {
		int index = 0;
		if(contacts.contains(contact)) {
			contacts.remove(contact);
		}
	}
}
