package lt.baraksoft.summersystem.portal.controller;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.dao.model.SummerhouseSearch;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by etere on 2016-04-27.
 */
@Named
@RequestScoped
public class SearchController {

    @Inject
    private SummerhouseViewHelper summerhouseViewHelper;

    private List<SummerhouseView> list;
    private SummerhouseSearch searchObject;
	private Date dateFrom;
    private Date dateTo;

    @PostConstruct
    public void init() {
		searchObject = new SummerhouseSearch();
		list = summerhouseViewHelper.search(searchObject);
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

	public List<SummerhouseView> getList() {
		return list;
	}

	public SummerhouseSearch getSearchObject() {
		return searchObject;
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
