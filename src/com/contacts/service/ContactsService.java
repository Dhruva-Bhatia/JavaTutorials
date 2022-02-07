package com.contacts.service;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.contacts.control.ContactControl;

public class ContactsService {
	// inititate the control
	ContactControl controlSrv;
	int operation;
	
	public ContactsService() {
		// intiate the control
		controlSrv = new ContactControl();
		controlSrv.fetchData();
	}
	
	// get the input
	public void execute(Scanner sc) {
		System.out.println("Enter the operation (0 exit, 1 Add Contact, 2 Delete Contact, 3 List Contacts, 4 Save Contacts):");
		while(true) {
			try {
				operation = sc.nextInt();
				break;
			} catch(InputMismatchException e) {
				System.out.println("Please enter an integer");
				sc.next();
			}
		}
		
		if(operation==0) {
			return;
		}
		
		switch(operation) {
		case 1:
			controlSrv.addContact(sc);
			break;
		case 2:
			controlSrv.deleteContact(sc);
			break;
		case 3:
			controlSrv.listContacts();
			break;
		case 4:
			controlSrv.saveData();
			break;
		default:
			System.out.println("Unknow operation please check");
		}
	}

	public void saveData() {
		controlSrv.saveData();		
	}
}
