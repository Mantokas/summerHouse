package lt.baraksoft.summersystem.portal.view;

import lt.baraksoft.summersystem.model.Summerhouse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-25.
 */
public class ServiceView implements Serializable{
    private static final long serialVersionUID = 999322965585444064L;

    private int id;
    private String description;
    private Boolean archived;
    private BigDecimal price;
    private String title;
    private List<Summerhouse> summerhouseList;

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

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
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

    public List<Summerhouse> getSummerhouseList() {
        return summerhouseList;
    }

    public void setSummerhouseList(List<Summerhouse> summerhouseList) {
        this.summerhouseList = summerhouseList;
    }
}
