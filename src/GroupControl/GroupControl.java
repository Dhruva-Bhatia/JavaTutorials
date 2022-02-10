package GroupControl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.contacts.control.ContactControl;
import com.groups.classes.Group;
import com.groups.repo.GroupsRepo;

public class GroupControl {
	ContactControl control;
	GroupsRepo repo;
	ArrayList<Group> groups;;
	
	public GroupControl(ContactControl control) {
		this.control = control;
		this.repo = new GroupsRepo();
	}
	
	public void fetchData() {
		this.groups = this.repo.readFile();
		if(this.groups == null) {
			this.groups = new ArrayList<Group>();
		}
	}
	
	public void save() {
		this.repo.writeArray(groups);
	}
	
	private void addContact(Group group, Scanner sc) {
		
		while(true) {
			System.out.println("Add contact from index below (0 to exit):");
			this.control.listContacts();
			Long currentContact = sc.nextLong();
			
			if(currentContact != 0) {
				System.out.println(currentContact);
				if(control.getContact(currentContact) != null) {
					group.addContact(currentContact);
					System.out.println("Contact added");
				}else {
					System.out.println("Contact not found");
				}
			}else {
				return;
			}
		}
	}
	
	private void viewGroups() {
		int index = 1;
		for(Group group:groups) {
			System.out.print(index+". ");
			System.out.println(group.name);
			index++;
		}
	}
	
	private void viewGroup(Group group) {
		// get the iterator for the group
		Iterator<Long> iterator = group.contacts.iterator();
		
		while(iterator.hasNext()) {
			Long contact = iterator.next();
			System.out.println(control.getContact(contact));			
		}
		
	}
	
	private void deleteContact(Group group, Scanner sc) {
		System.out.println("Select contact number to delete");
		viewGroup(group);
		Long contact = sc.nextLong();
		group.removeContact(contact);
	}
	
	private Group selectGroup(Scanner sc) {
		viewGroups();
		System.out.println("Select group index");
		int groupIndex = sc.nextInt() - 1;
		if(groupIndex >= this.groups.size() || groupIndex < 0) {
			System.out.println("IncorrectIndex");
			return null;
		}
		return groups.get(groupIndex);
	}
	
	public void groupOperations(Scanner sc) {
		Group currentGroup = selectGroup(sc);
		if(currentGroup == null) {
			System.out.println("Group is not defined");
			return;
		}
		
		// now we list the operations for the group
		System.out.println("Enter the operation (0: exit, 1: list details, 2: add contact, 3: delete contact");
		int operation = sc.nextInt();
		if(operation == 0) {
			return;
		}
		switch(operation) {
		case 1:
			viewGroup(currentGroup);
			break;
		case 2:
			addContact(currentGroup, sc);
			break;
		case 3:
			deleteContact(currentGroup, sc);
		default:
			System.out.println("Invalid Operation\n");
		}
	}
	
	public void addGroup(Scanner sc) {
		System.out.println("Enter group name: ");
		sc.nextLine();
		String groupName =sc.nextLine();
		Group currGroup = new Group(groupName);
		addContact(currGroup, sc);
		groups.add(currGroup);
	}
	
	public void deleteGroup(Scanner sc) {
		viewGroups();
		System.out.println("Select group index to be deleted");
		int groupIndex = sc.nextInt() - 1;
		if(groupIndex >= this.groups.size() || groupIndex < 0) {
			System.out.println("IncorrectIndex");
			return;
		}
		this.groups.remove(groupIndex);
		// delete group by index
	}
}
