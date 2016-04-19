package lt.baraksoft.summersystem.portal.controller;

import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@ManagedBean
@ViewScoped
public class SummerhouseController implements Serializable{
    @Inject
    private SummerhouseViewHelper summerhouseViewHelper;

    private List<SummerhouseView> summerhousesList;
    private SummerhouseView selectedSummerhouse;


    @PostConstruct
    public void init() {
        summerhousesList = summerhouseViewHelper.getAllSummerhouses();
    }

    public List<SummerhouseView> getSummerhousesList() {
        return summerhousesList;
    }

    public void setSummerhousesList(List<SummerhouseView> summerhousesList) {
        this.summerhousesList = summerhousesList;
    }

    public SummerhouseView getSelectedSummerhouse() {
        return selectedSummerhouse;
    }

    public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
        this.selectedSummerhouse = selectedSummerhouse;
    }
}
