package lt.baraksoft.summersystem.portal.view;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by LaurynasC on 2016-04-19.
 */
public class ReservationView implements Serializable {
	private static final long serialVersionUID = -3832371726357941392L;

	private Integer id;
	private LocalDate date_from;
	private LocalDate date_to;
	private Boolean isApproved;
	private Boolean isArchived;
	private int summerhouseID;
	private int userID;
	private String userFirstname;
	private String userLastname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate_from() {
		return date_from;
	}

	public void setDate_from(LocalDate date_from) {
		this.date_from = date_from;
	}

	public LocalDate getDate_to() {
		return date_to;
	}

	public void setDate_to(LocalDate date_to) {
		this.date_to = date_to;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Boolean getIsArchived() {
		return isArchived;
	}

	public void setIsArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public int getSummerhouseID() {
		return summerhouseID;
	}

	public void setSummerhouseID(int summerhouseID) {
		this.summerhouseID = summerhouseID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserFirstname() {
		return userFirstname;
	}

	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}

	public String getUserLastname() {
		return userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}
}
