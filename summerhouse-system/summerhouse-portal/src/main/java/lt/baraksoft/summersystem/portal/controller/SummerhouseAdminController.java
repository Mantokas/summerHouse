package lt.baraksoft.summersystem.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import lt.baraksoft.summersystem.dao.ConfigurationEntryDao;
import lt.baraksoft.summersystem.model.ConfigurationEntryEnum;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

@Named
@SessionScoped
public class SummerhouseAdminController implements Serializable {
	private static final long serialVersionUID = 1969079359482463164L;

	@Inject
	private SummerhouseViewHelper summerhouseViewHelper;
	@Inject
	private ConfigurationEntryDao configurationEntryDao;

	private List<SummerhouseView> summerhousesList;
	private SummerhouseView selectedSummerhouse;
	private SummerhouseView summerhouse = new SummerhouseView();
	private String yearlyPayment;
	private String maxUsersSize;
	private Date dateFrom;
	private Date dateTo;
	private Part image;

	@PostConstruct
	public void init() {
		yearlyPayment = configurationEntryDao.getByType(ConfigurationEntryEnum.YEARLY_PAYMENT_PRICE).getValue();
		maxUsersSize = configurationEntryDao.getByType(ConfigurationEntryEnum.MAX_USERS_SIZE).getValue();
		summerhousesList = summerhouseViewHelper.getAllSummerhouses();
	}

	public void handleFileUpload(AjaxBehaviorEvent event) {
		System.out.println("******************DYDIS: " + image.getSize());
		try {
			summerhouse.setImage(IOUtils.toByteArray(image.getInputStream()));
		} catch (IOException e) {
			throw new IllegalStateException("Failed to convert image to byte array!");
		}
	}

	public void doSave() {
		summerhouse.setDateFrom(dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		summerhouse.setDateTo(dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

		if (!isSummerhouseValid()) {
			return;
		}

		RequestContext.getCurrentInstance().execute("PF('summerhouseDialog').hide()");
		summerhouseViewHelper.save(summerhouse);
		summerhousesList = summerhouseViewHelper.getAllSummerhouses();
	}

	public boolean isSummerhouseValid() {
		return StringUtils.isNotBlank(summerhouse.getTitle()) && StringUtils.isNotBlank(summerhouse.getAddress()) && summerhouse.getPrice() != null
				&& summerhouse.getDateFrom() != null && summerhouse.getDateTo() != null;
	}

	public void doShowCreateDialog() {
		dateFrom = null;
		dateTo = null;
		summerhouse = new SummerhouseView();
	}

	public void doShowEditDialog() {
		dateFrom = Date.from(selectedSummerhouse.getDateFrom().atStartOfDay(ZoneId.systemDefault()).toInstant());
		dateTo = Date.from(selectedSummerhouse.getDateTo().atStartOfDay(ZoneId.systemDefault()).toInstant());
		summerhouse = selectedSummerhouse;
	}

	public void doArchive() {
		selectedSummerhouse.setArchived(true);
		summerhouseViewHelper.save(selectedSummerhouse);
	}

	public void doReset() {
		selectedSummerhouse.setArchived(false);
		summerhouseViewHelper.save(selectedSummerhouse);
	}

	public void onSelect() {
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

	public String getYearlyPayment() {
		return yearlyPayment;
	}

	public void setYearlyPayment(String yearlyPayment) {
		this.yearlyPayment = yearlyPayment;
	}

	public String getMaxUsersSize() {
		return maxUsersSize;
	}

	public void setMaxUsersSize(String maxUsersSize) {
		this.maxUsersSize = maxUsersSize;
	}

	public Part getImage() {
		return image;
	}

	public void setImage(Part image) {
		this.image = image;
	}
}
