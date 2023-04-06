package domain;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class Customer {

	String fName;
	String lName;
	String email;
	String icon;
	String subType;
	
	public Customer(String fName, String lName, String email, String icon, String subType) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.icon = icon;
		this.subType = subType;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}
	
	
	
}
