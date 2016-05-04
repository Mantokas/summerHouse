package lt.baraksoft.summersystem.portal.view;

import lt.baraksoft.summersystem.model.Service;

import java.io.Serializable;
import java.math.BigDecimal;
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
	private int capacity;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private String description;
	private Boolean isArchived;
	private BigDecimal price;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
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

	public Boolean isArchived() {
		return isArchived;
	}

	public void setArchived(Boolean isArchived) {
		this.isArchived = isArchived;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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
