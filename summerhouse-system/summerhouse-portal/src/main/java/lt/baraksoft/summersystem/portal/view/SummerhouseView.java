package lt.baraksoft.summersystem.portal.view;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-19.
 */
public class SummerhouseView implements Serializable {
	private static final long serialVersionUID = 5106422725979527271L;

	private Integer id;
	private String address;
	private String capacity;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private String description;
	private boolean archived;
	private String price;
	private String title;
	private List<ServiceView> serviceViews = new ArrayList<>();

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ServiceView> getServiceViews() {
		return serviceViews;
	}

	public void setServiceViews(List<ServiceView> serviceViews) {
		this.serviceViews = serviceViews;
	}
}
