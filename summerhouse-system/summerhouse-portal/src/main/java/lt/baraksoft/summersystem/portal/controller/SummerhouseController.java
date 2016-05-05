package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Named
@RequestScoped
public class SummerhouseController implements Serializable {
	private static final long serialVersionUID = 7327490916016914082L;

	@Inject
	private SummerhouseViewHelper summerhouseViewHelper;

	@Inject
	private SummerhouseDao summerhouseDao;

	private List<SummerhouseView> summerhousesList;
	private SummerhouseView selectedSummerhouse;
	private Boolean disabled = true;

	@PostConstruct
	public void init() {
		summerhousesList = summerhouseViewHelper.getAllSummerhouses();
	}

	public void onRowSelect(SelectEvent event) {
		disabled = false;
	}

	public void doSelectSummerhouse() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("summerhouse", selectedSummerhouse);
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
