package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.portal.helper.ReservationPaymentHelper;
import lt.baraksoft.summersystem.portal.view.ServiceView;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-12.
 */
@Stateless
public class ReservationPaymentHelperImpl implements ReservationPaymentHelper {

    @Override
    public List<Service> buildEntities(List<ServiceView> selectedServices) {
        List<Service> selectedServicesEntities = new ArrayList<>();
        selectedServices.stream().forEach(serviceView -> addToServiceList(serviceView, selectedServicesEntities));
        return selectedServicesEntities;
    }

    private void addToServiceList(ServiceView serviceView, List<Service> selectedServices) {
        Service entity = new Service();
        entity.setPrice(serviceView.getPrice());
        entity.setArchived(serviceView.getArchived());
        entity.setDescription(serviceView.getDescription());
        entity.setSummerhouseList(serviceView.getSummerhouseList());
        entity.setTitle(serviceView.getTitle());
        selectedServices.add(entity);
    }
}
