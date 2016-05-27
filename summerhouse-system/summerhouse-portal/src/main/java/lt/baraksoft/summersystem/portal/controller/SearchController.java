package lt.baraksoft.summersystem.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import lt.baraksoft.summersystem.dao.model.SummerhouseSearch;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by etere on 2016-04-27.
 */
@Named
@SessionScoped
@Stateful
public class SearchController implements Serializable {

	private static final long serialVersionUID = 7491298817566550329L;

	@EJB
	private SummerhouseViewHelper summerhouseViewHelper;

	@Inject
	UserLoginController userLoginController;


	private List<SummerhouseView> list;
	private SummerhouseSearch searchObject;
	private SummerhouseView selectedSummerhouse;
	private Boolean disabled = true;
	private Date dateFrom;
	private Date dateTo;
	private Date today;
	private boolean visibleResults;
	private Part image;

	@PostConstruct
	public void init() {
		searchObject = new SummerhouseSearch();
		today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		visibleResults = false;
	}

	public void handleFileUpload(AjaxBehaviorEvent event) {
		try {
			selectedSummerhouse.setImage(new DefaultStreamedContent(image.getInputStream(), "image/jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summerhouseViewHelper.save(selectedSummerhouse);
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

	public void makeSelectedSummerhouse(SummerhouseView summerhouse) {
		selectedSummerhouse = summerhouse;
	}

	public String doSelectSummerhouse() {
		if (selectedSummerhouse.getPrice().intValue() > userLoginController.getLoggedUser().getPoints()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida", "Nepakanka pinigų rezervacijai");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			return "/reservationPaymentProcess.xhtml?faces-redirect=true";
		}
		return "";
	}

	public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
		this.selectedSummerhouse = selectedSummerhouse;
	}

	public void setSearchObject(SummerhouseSearch searchObject) {
		this.searchObject = searchObject;
	}

	public void doUpdateSummerhouseList() {
		searchObject.setDateFrom(dateFrom != null ? dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
		searchObject.setDateTo(dateTo != null ? dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
		list = summerhouseViewHelper.search(searchObject);
		visibleResults = true;
		RequestContext context = RequestContext.getCurrentInstance();
		context.scrollTo("form2:cars");
	}

	public StreamedContent getStreamedImage(SummerhouseView view) {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			return view.getImage();
		}
	}

	public Date getToday() {
		return today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public boolean isVisibleResults() {
		return visibleResults;
	}

	public void setVisibleResults(boolean visibleResults) {
		this.visibleResults = visibleResults;
	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}
}
