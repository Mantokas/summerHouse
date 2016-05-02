package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.dao.ServiceDao;
import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.model.Reservation;
import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.model.Summerhouse;
import lt.baraksoft.summersystem.portal.helper.ServiceViewHelper;
import lt.baraksoft.summersystem.portal.view.ReservationView;
import lt.baraksoft.summersystem.portal.view.ServiceView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-25.
 */

@Stateless
public class ServiceViewHelperImpl implements ServiceViewHelper{

    @Inject
    private ServiceDao serviceDao;

    @Inject
    private SummerhouseDao summerhouseDao;

    private ServiceView buildView(Service entity) {
        ServiceView view = new ServiceView();
        view.setId(entity.getId());
        view.setDescription(entity.getDescription());
        view.setArchived(entity.isArchived());
        view.setPrice(entity.getPrice());
        view.setTitle(entity.getTitle());
        return view;
    }

    @Override
    public List<ServiceView> buildViews(List<Service> entities) {
        List<ServiceView> views = new ArrayList<>();
        entities.stream().forEach(e -> views.add(buildView(e)));
        return views;
    }
}
