package lt.baraksoft.summersystem.portal.controller;

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

	@PostConstruct
	public void init() {
		searchObject = new SummerhouseSearch();
		list = summerhouseViewHelper.search(searchObject);
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
		list = summerhouseViewHelper.search(searchObject);
	}

}
