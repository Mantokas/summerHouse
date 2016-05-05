package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

@Named
@RequestScoped
public class SummerhouseAdminController implements Serializable {
	private static final long serialVersionUID = 1969079359482463164L;

	@Inject
	private SummerhouseViewHelper summerhouseViewHelper;

	private SummerhouseView summerhouse = new SummerhouseView();
	private Date dateFrom;
	private Date dateTo;

	@PostConstruct
	public void init() {
	}

	public void doCreate() {
		summerhouse.setDateFrom(dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		summerhouse.setDateTo(dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		summerhouseViewHelper.save(summerhouse);
	}

	public SummerhouseView getSummerhouse() {
		return summerhouse;
	}

	public void setSummerhouse(SummerhouseView summerhouse) {
		this.summerhouse = summerhouse;
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

}
