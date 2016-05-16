package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import lt.baraksoft.summersystem.dao.model.SummerhouseSearch;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by etere on 2016-04-27.
 */
@Named
@SessionScoped
@Stateful
public class SearchController implements Serializable{

	private static final long serialVersionUID = 7491298817566550329L;

	@EJB
    private SummerhouseViewHelper summerhouseViewHelper;

    private List<SummerhouseView> list;
    private SummerhouseSearch searchObject;
	private SummerhouseView selectedSummerhouse;
	private Boolean disabled = true;
	private Date dateFrom;
    private Date dateTo;

    @PostConstruct
    public void init() {
		searchObject = new SummerhouseSearch();

    }

	public void onRowSelect() {
		disabled = false;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public List<SummerhouseView> getList() {
		return list;
	}

	public SummerhouseSearch getSearchObject() {
		return searchObject;
	}

	public SummerhouseView getSelectedSummerhouse() {
		return selectedSummerhouse;
	}

	public String doSelectSummerhouse() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("summerhouse", selectedSummerhouse);
		return "goToReservation";
	}

	public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
		this.selectedSummerhouse = selectedSummerhouse;
	}

	public void setSearchObject(SummerhouseSearch searchObject) {
		this.searchObject = searchObject;
	}

	public void doUpdateSummerhouseList() {
		if(dateFrom != null)
			searchObject.setDateFrom(dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		if(dateTo != null)
			searchObject.setDateTo(dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		list = summerhouseViewHelper.search(searchObject);
	}

}
