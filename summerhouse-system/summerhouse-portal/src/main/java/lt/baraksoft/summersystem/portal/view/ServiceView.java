package lt.baraksoft.summersystem.portal.view;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by LaurynasC on 2016-04-25.
 */
public class ServiceView implements Serializable {
	private static final long serialVersionUID = 999322965585444064L;

	private int id;
	private String description;
	private boolean archived;
	private BigDecimal price;
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getArchivedString() {
		return archived ? "Archyvuota" : "Nearchyvuota";
	}

}
