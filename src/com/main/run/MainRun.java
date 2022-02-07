package com.main.run;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.contacts.service.ContactsService;

public class MainRun {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int mainOperation; // Group or Contact
		ContactsService contactSrv = new ContactsService();
		
		
		// loop till exit
		while(true) {
			try {
				System.out.println("Enter the Operation (0 Exit, 1 Groups, 2 Contacts)");
				mainOperation = sc.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("Please enter an integer");
				sc.next();
				continue;
			}
			
			if(mainOperation == 0) {
				break;
			}
			switch(mainOperation) {
			case 1:
				// TODO add functionality to Group functions
				break;
			case 2:
				contactSrv.execute(sc);
				break;
			default:
				System.out.println("Incorrect Input");
			}
		}
		// TODO save all data
		contactSrv.saveData();
		System.exit(1);
	}
}
