package lt.baraksoft.summersystem.portal.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by LaurynasC on 2016-04-19.
 */
public class ReservationView implements Serializable {
	private static final long serialVersionUID = -3832371726357941392L;

	private Integer id;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private boolean archived;
	private int summerhouseID;
	private int userID;
	private String userFirstname;
	private String userLastname;
    private int price;
    private String number;

	public SummerhouseView getReservedSummerhouse() {
		return reservedSummerhouse;
	}

	public void setReservedSummerhouse(SummerhouseView reservedSummerhouse) {
		this.reservedSummerhouse = reservedSummerhouse;
	}

	private SummerhouseView reservedSummerhouse;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
