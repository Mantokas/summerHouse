package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@ManagedBean
@ViewScoped
public class SummerhouseController implements Serializable {
	private static final long serialVersionUID = 7327490916016914082L;

	@Inject
	private SummerhouseViewHelper summerhouseViewHelper;

	private List<SummerhouseView> summerhousesList;
	private SummerhouseView selectedSummerhouse;

	@PostConstruct
	public void init() {
		summerhousesList = summerhouseViewHelper.getAllSummerhouses();
	}

	public void doSelectSummerhouse() {
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("summerhouse", selectedSummerhouse);
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
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("summerhouse", selectedSummerhouse);
	}
}
