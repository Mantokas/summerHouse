package lt.baraksoft.summersystem.portal.controller;

import java.io.IOException;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

@Named
@SessionScoped
public class SummerhouseAdminController implements Serializable {
	private static final long serialVersionUID = 1969079359482463164L;

	@Inject
	private SummerhouseViewHelper summerhouseViewHelper;

	private List<SummerhouseView> summerhousesList;
	private SummerhouseView selectedSummerhouse;
	private SummerhouseView summerhouse = new SummerhouseView();
	private Date dateFrom;
	private Date dateTo;
	private UploadedFile image;

	@PostConstruct
	public void init() {
		summerhousesList = summerhouseViewHelper.getAllSummerhouses();
	}

	public void doSave() {
		summerhouse.setDateFrom(dateFrom.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		summerhouse.setDateTo(dateTo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		try {
			if (image != null) {
				summerhouse.setImage(IOUtils.toByteArray(image.getInputstream()));
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed to convert image to byte array!");
		}

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

	public UploadedFile getImage() {
		return image;
	}

	public void setImage(UploadedFile image) {
		this.image = image;
	}
}
