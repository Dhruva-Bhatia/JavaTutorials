package com.contacts.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import com.contacts.classes.Contact;
import com.contacts.repo.ContactsRepo;

public class ContactControl {

	// regex for email
	static String check_email_pattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

	// create an array of the Contacts
	private HashMap<Long, Contact> contacts = new HashMap<Long, Contact>();
	
	// populate the contacts from file
	ContactsRepo repoSrv = new ContactsRepo();
	
	// fetch contact by number
	public Contact getContact(long number) {
		// TODO implement this
		if(contacts.containsKey(number)) {
			return contacts.get(number);
		}else {
			return null;
		}
		
	}
	
	/*
	 * Add a contact
	 */
	public void addContact(Scanner sc) {
		System.out.println("Enter the first name: ");
		sc.nextLine();
		String f_name = sc.nextLine();
		if(f_name.length() == 0) {
			System.out.println("Name too short");
			return;
		}
		System.out.println("Enter the last name (Leave blank to skip): ");
		String l_name = sc.nextLine();
		
		System.out.println("Enter the number: ");
		Long number = Long.valueOf(sc.nextLong());
		if(Math.log10(number) + 1 < 10) {
			System.out.println("Number too short");
			return;
		}
		sc.nextLine();
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		System.out.println(email.matches(check_email_pattern));
		if(!email.matches(check_email_pattern)) {
			System.out.println("incorrect email");
			return;
		}
		
		contacts.put(number ,new Contact(number, f_name, l_name, email));
	}
	
	/*
	 * Delete contact by name
	 */
	private void delete(String name) {
		Long number = 0l;
		
		// iterate through the values to find the name
		Iterator<Entry<Long, Contact>> iterator = contacts.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Long, Contact> entry = iterator.next();
			if(entry.getValue().fullName == name) {
				number = entry.getKey();
				break;
			}
		}
		
		if(number == 0l) {
			System.out.println("No name called "+name+" is found in contacts");
		}else {
			this.contacts.remove(number);
			System.out.println("Contact deleted");
		}
	}
	
	/*
	 * Delete contact by number
	 */
	private void delete(long phone) {
		if(!this.contacts.containsKey(phone)) {
			System.out.println("Contact not found");
		}else {
			contacts.remove(Long.valueOf(phone));
			System.out.println("Contact deleted");
		}
	}
	
	/*
	 * Delete contact
	 */
	public void deleteContact(Scanner sc) {
		System.out.println("To search by contact no press 1, to search by name press 2");
		int method = sc.nextInt();
		if(method == 1) {
			System.out.println("Enter the number");
			long deleteNumber = sc.nextLong();   // TODO add try catch block
			delete(deleteNumber);
		}else {
			System.out.println("Enter the first name");
			sc.nextLine();
			String deleteName = sc.nextLine();
			System.out.println("Enter the last name");
			deleteName += " " + sc.nextLine();
			
			delete(deleteName);
		}
	}
	
	/*
	 * Collect data from file
	 */
	public void fetchData() {
		// Fetch the data from the contacts file
		HashMap<Long, Contact> curr_contacts = repoSrv.readFile();
		// Append to contacts
		if(curr_contacts != null) {
			this.contacts = curr_contacts;
		}
	}
	
	/*
	 * Lists the contacts
	 */
	public void listContacts() {
		int index = 1;
		
		Iterator<Entry<Long, Contact>> iterator = contacts.entrySet().iterator();
		while(iterator.hasNext()) {
			System.out.println(index+".");
			System.out.println(iterator.next().getValue());
			System.out.println("\n");
			index++;
		}
	}
	
	/*
	 * Save the data to file
	 */
	public void saveData() {
		int result = repoSrv.writeArray(contacts);
		if(result == 0) {
			System.out.println("Contact Write Successfull");
		}else {
			System.out.println("Contact Write Failure");
		}
	}
}
