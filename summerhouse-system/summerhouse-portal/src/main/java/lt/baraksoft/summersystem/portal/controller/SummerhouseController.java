package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Named
@SessionScoped
@Stateful
public class SummerhouseController implements Serializable {
	private static final long serialVersionUID = 7327490916016914082L;

	@EJB
	private SummerhouseViewHelper summerhouseViewHelper;

	private List<SummerhouseView> summerhousesList;
	private SummerhouseView selectedSummerhouse;
	private Boolean disabled = true;

	@PostConstruct
	public void init() {
		summerhousesList = summerhouseViewHelper.getAllSummerhouses();
	}

	public void onRowSelect() {
		disabled = false;
	}

	public String doSelectSummerhouse() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("summerhouse", selectedSummerhouse);
        return "goToReservation";
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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
}
