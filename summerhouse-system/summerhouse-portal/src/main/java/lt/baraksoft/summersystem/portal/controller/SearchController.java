package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.dao.impl.SummerhouseDaoImpl;
import lt.baraksoft.summersystem.dao.model.SummerhouseSearch;
import lt.baraksoft.summersystem.model.Summerhouse;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by etere on 2016-04-27.
 */
@ManagedBean
@ViewScoped
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
