package spittr;

public class Spitter {
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	public Spitter(String firstName, String lastName, String username, String password){
		this.id=null;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	public Spitter(Long id, String firstName, String lastName, String username, String password){
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Long getId() {
		return id;
	}
}
