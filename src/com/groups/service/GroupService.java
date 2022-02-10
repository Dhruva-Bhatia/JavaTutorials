package com.groups.service;

import java.util.Scanner;

import com.contacts.control.ContactControl;
import com.contacts.service.ContactsService;
import GroupControl.GroupControl;

public class GroupService {
	
	GroupControl controlSrv;
	ContactControl contactControlSrv;
	
	public GroupService(ContactsService contactSrv) {
		this.contactControlSrv = contactSrv.controlSrv;
		this.controlSrv = new GroupControl(this.contactControlSrv);
		this.controlSrv.fetchData();
	}
	
	public void saveData() {
		this.controlSrv.save();
		System.out.println("Groups saved");
	}
	
	public void execute(Scanner sc) {
		// make them choose between add, delete or group operations
		System.out.println("Select Operation (0: exit, 1: Add group, 2: Delete Group, 3: Select Group, 4: save)");
		int operation = sc.nextInt();
		if(operation == 0) {
			return;
		}
		
		switch(operation) {
		case 1:
			controlSrv.addGroup(sc);
			break;
			
		case 2:
			controlSrv.deleteGroup(sc);
			break;
			
		case 3:
			controlSrv.groupOperations(sc);
			break;
			
		case 4:
			controlSrv.save();
			break;
			
		default:
			System.out.println(operation + " Incorrect Selection");
		}
	}
}
