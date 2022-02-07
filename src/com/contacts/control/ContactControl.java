package com.contacts.control;

import java.util.ArrayList;
import java.util.Scanner;

import com.contacts.classes.Contact;
import com.contacts.repo.ContactsRepo;

public class ContactControl {

	// regex for email
	static String check_email_pattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

	// create an array of the Contacts
	ArrayList<Contact> contacts = new ArrayList<Contact>();
	
	// populate the contacts from file
	ContactsRepo repoSrv = new ContactsRepo();
	
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
		long number = sc.nextLong();
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
		
		contacts.add(new Contact(number, f_name, l_name, email));
	}
	
	/*
	 * Delete contact by name
	 */
	private void delete(String name) {
		int index = 0;
		boolean found = false;
		for(Contact contact:contacts) {
			if(contact.f_name == name) {
				found = true;
				break;
			}
			index++;
		}
		if(found) {
		contacts.remove(index);
		}else {
			System.out.println("Contact not found");
		}
	}
	
	/*
	 * Delete contact by number
	 */
	private void delete(long phone) {
		int index = 0;
		boolean found = false;
		for(Contact contact:contacts) {
			if(contact.number == phone) {
				found = true;
				break;
			}
			index++;
		}
		if(found) {
		contacts.remove(index);
		}else {
			System.out.println("Contact not found");
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
			System.out.println("Enter the name");
			sc.nextLine();
			String deleteName = sc.nextLine();
			delete(deleteName);
		}
	}
	
	/*
	 * Collect data from file
	 */
	public void fetchData() {
		// Fetch the data from the contacts file
		ArrayList<Contact> curr_contacts = repoSrv.readFile();
		// Append to contacts
		if(curr_contacts == null) {
			
		}else {
			for(Contact curr_contact:curr_contacts) {
				contacts.add(curr_contact);
			}
		}
	}
	
	/*
	 * Lists the contacts
	 */
	public void listContacts() {
		for(Contact contact:contacts) {
			System.out.println(contact);
			System.out.println("\n");
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
