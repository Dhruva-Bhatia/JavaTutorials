package com.contacts.classes;

import java.io.Serializable;

public class Contact implements Serializable{

	/**
	 * implements Contact class
	 */
	private static final long serialVersionUID = 1L;
	public String f_name;
	public String l_name;
	public String fullName;
	public String email;
	public long number;
	
	public Contact(long number, String f_name, String l_name, String email) {
		// Create the contact from the given information
		this.email = email;
		this.number = number;
		this.f_name = f_name;
		if(l_name.length() != 0) {
			this.l_name = l_name;
		}
		
		fullName = this.f_name+" "+this.l_name;
	}
	
	@Override
	public String toString() {
		return "f_name=" + f_name + ", l_name=" + l_name + ", email=" + email + ", number=" + number;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = prime + Long.valueOf(this.number).hashCode();
		
		return result;
	}
	

}
