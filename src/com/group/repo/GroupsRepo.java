package com.group.repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.contacts.classes.Contact;
import com.groups.classes.Group;

public class GroupsRepo {
final String GROUPS_FILE = "contacts.txt";
	
	/*
	 * Function to write to contacts file
	 */
	public int writeArray(ArrayList<Group> array){
		File writeFile = new File(GROUPS_FILE);
		try {
			writeFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't create contacts file");
		}
		
		try(ObjectOutputStream writeStream = new ObjectOutputStream(new FileOutputStream(GROUPS_FILE))){
			writeStream.writeObject(array);
			writeStream.flush();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to locate contacts file");
			return 1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("I//O exception for write");
			return 1;
		}
		
		return 0;
	}
	
	/*
	 * Function to read from file
	 */
	public ArrayList<Contact> readFile(){
		try(ObjectInputStream readObj = new ObjectInputStream(new FileInputStream(GROUPS_FILE))){
			@SuppressWarnings("unchecked")
			ArrayList<Contact> obj = (ArrayList<Contact>) readObj.readObject();
			return obj;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
