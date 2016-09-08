package data;

public enum Category {
	USERS("Users"),
	TICKETS("Tickets"),
	ORGANISATIONS("Organisations");
	
	private String value;
	
	private Category(String value) {
		this.value = value;
	}
	
	/**
	 * Gets the String value of the enum
	 * 
	 * @return the String value of the enum
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * Gets the corresponding enum from the given value
	 * 
	 * @param value the value for which to retrieve the corresponding enum
	 * @return the enum which corresponds to the given value
	 */
	public static Category getEnum(String value) {
		for (Category category : values()) {
		    if (category.getValue().equals(value)) {
		      return category;
		    }
		  }
		
		throw new IllegalArgumentException(String.valueOf(value));
	}
}