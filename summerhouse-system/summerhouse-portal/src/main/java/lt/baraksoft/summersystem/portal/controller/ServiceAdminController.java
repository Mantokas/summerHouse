package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import lt.baraksoft.summersystem.portal.helper.ServiceViewHelper;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.ServiceView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

@Named
@SessionScoped
public class ServiceAdminController implements Serializable {
	private static final long serialVersionUID = 4448497430743665911L;

	@EJB
	private ServiceViewHelper serviceViewHelper;

	@EJB
	private SummerhouseViewHelper summerhouseViewHelper;

	@Inject
	private SummerhouseAdminController summerhouseAdminController;

	private List<ServiceView> servicesList;
	private ServiceView selectedService;
	private ServiceView service = new ServiceView();
	private SummerhouseView selectedSummerhouse;

	@PostConstruct
	public void init() {
		selectedSummerhouse = summerhouseAdminController.getSelectedSummerhouse();
		servicesList = serviceViewHelper.getServicesBySummerhouse(selectedSummerhouse.getId());
	}

	public void doSave() {
		if (!isSummerhouseValid()) {
			return;
		}


		if (service.getId() != 0) {
			serviceViewHelper.save(service);
		} else {
			selectedSummerhouse.getServiceViews().add(service);
			summerhouseViewHelper.save(selectedSummerhouse);
		}
		servicesList = serviceViewHelper.getServicesBySummerhouse(selectedSummerhouse.getId());
        RequestContext.getCurrentInstance().execute("PF('summerhouseDialog').hide()");
	}

	private boolean isSummerhouseValid() {
		return StringUtils.isNotBlank(service.getTitle()) && service.getPrice() != null;
	}

	public void doShowCreateDialog() {
		service = new ServiceView();
	}

	public void doShowEditDialog() {
		service = selectedService;
	}

	public void doArchive() {
		selectedService.setArchived(true);
		serviceViewHelper.save(selectedService);
	}

	public void doReset() {
		selectedService.setArchived(false);
		serviceViewHelper.save(selectedService);
	}

	public SummerhouseView getSelectedSummerhouse() {
		return selectedSummerhouse;
	}

	public void setSelectedSummerhouse(SummerhouseView selectedSummerhouse) {
		this.selectedSummerhouse = selectedSummerhouse;
	}

	public ServiceViewHelper getServiceViewHelper() {
		return serviceViewHelper;
	}

	public void setServiceViewHelper(ServiceViewHelper serviceViewHelper) {
		this.serviceViewHelper = serviceViewHelper;
	}

	public List<ServiceView> getServicesList() {
		return servicesList;
	}

	public void setServicesList(List<ServiceView> servicesList) {
		this.servicesList = servicesList;
	}

	public ServiceView getSelectedService() {
		return selectedService;
	}

	public void setSelectedService(ServiceView selectedService) {
		this.selectedService = selectedService;
	}

	public SummerhouseAdminController getSummerhouseAdminController() {
		return summerhouseAdminController;
	}

	public void setSummerhouseAdminController(SummerhouseAdminController summerhouseAdminController) {
		this.summerhouseAdminController = summerhouseAdminController;
	}

	public SummerhouseViewHelper getSummerhouseViewHelper() {
		return summerhouseViewHelper;
	}

	public void setSummerhouseViewHelper(SummerhouseViewHelper summerhouseViewHelper) {
		this.summerhouseViewHelper = summerhouseViewHelper;
	}

	public ServiceView getService() {
		return service;
	}

	public void setService(ServiceView service) {
		this.service = service;
	}
}
