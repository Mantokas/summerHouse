package lt.baraksoft.summersystem.portal.view;

import java.io.Serializable;
import java.time.LocalDate;

public class UserView implements Serializable {
	private static final long serialVersionUID = 2954419213235282035L;

	private Integer id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String facebookId;
	private boolean approved;
	private boolean archived;
	private int points;
	private int groupNumber;
	private LocalDate validTo;

	public UserView(String firstName, String lastName, String email, String facebookId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.facebookId = facebookId;
	};

	public UserView() {
	};

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getArchivedString() {
		return archived ? "Archyvuotas" : "Nearchyvuotas";
	}

	public String getApprovedString() {
		return approved ? "Patvirtintas" : "Nepatvirtintas";
	}
}
