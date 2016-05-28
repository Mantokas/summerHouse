package lt.baraksoft.summersystem.portal.controller;

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
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.ReservationPaymentHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.UserView;
import org.primefaces.context.RequestContext;

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
	private UserLoginController userLoginController;

    @EJB
    private ReservationPaymentHelper reservationPaymentHelper;

	private List<SummerhouseView> list;
    private List<ReservationView> reservations;
	private SummerhouseSearch searchObject;
	private SummerhouseView selectedSummerhouse;
	private Date dateFrom;
	private Date dateTo;
	private Date today;
	private boolean visibleResults;
    private UserView loggedUser;

	@PostConstruct
	public void init() {
		searchObject = new SummerhouseSearch();
		today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		visibleResults = false;
        loggedUser = userLoginController.getLoggedUser();
	}

	public void makeSelectedSummerhouse(SummerhouseView summerhouse) {
		selectedSummerhouse = summerhouse;
        reservations = reservationPaymentHelper.getReservationsBySummerhouse(selectedSummerhouse.getId());
	}

	public String doSelectSummerhouse() {
        loggedUser = userLoginController.getLoggedUser();
		if (selectedSummerhouse.getPrice().intValue() > loggedUser.getPoints()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida", "Nepakanka pinigų rezervacijai");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
        else if (loggedUser.getValidTo() == null || loggedUser.getValidTo().isBefore(LocalDate.now())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Klaida", "Sumokėkite metinį narystės mokestį");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else {
			return "/reservationPaymentProcess.xhtml?faces-redirect=true";
		}
		return "";
	}

    private void createErrorMessage(String first, String second) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, first, second);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	public void doUpdateSummerhouseList() {
		if (dateFrom != null && dateTo != null && dateFrom.after(dateTo)){
            createErrorMessage("Klaida", "Pasirinktas neteisingas laikotarpis");
            list = null;
        }
        else{
            searchObject.setDateFrom(dateFrom != null ? dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            searchObject.setDateTo(dateTo != null ? dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null);
            list = summerhouseViewHelper.search(searchObject);
            visibleResults = !list.isEmpty();
            RequestContext context = RequestContext.getCurrentInstance();
            context.scrollTo("form2:cars");
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

    public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
        this.selectedSummerhouse = selectedSummerhouse;
    }

    public void setSearchObject(SummerhouseSearch searchObject) {
        this.searchObject = searchObject;
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

    public SummerhouseView getSelectedSummerhouse() {

        return selectedSummerhouse;
    }

    public List<ReservationView> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationView> reservations) {
        this.reservations = reservations;
    }

    public UserView getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserView loggedUser) {
        this.loggedUser = loggedUser;
    }
}