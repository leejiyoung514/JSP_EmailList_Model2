package com.javaex.vo;

public class EmailVO {
	
	private int no;
    private String lastName;
    private String firstName;
    private String email;
    
    
    public EmailVO() {
    	
    }
    
    public EmailVO(String lastName, String firstName, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
	}
    
	public EmailVO(int no, String lastName, String firstName, String email) {
		this.no = no;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "emailVO [no=" + no + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + "]";
	}    
    
}
