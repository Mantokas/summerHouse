package lt.baraksoft.summersystem.portal.helper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lt.baraksoft.summersystem.dao.ServiceDao;
import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.portal.helper.ServiceViewHelper;
import lt.baraksoft.summersystem.portal.view.ServiceView;

/**
 * Created by LaurynasC on 2016-04-25.
 */

@Stateless
public class ServiceViewHelperImpl implements ServiceViewHelper {

	@Inject
	private ServiceDao serviceDao;

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
	public void save(ServiceView view) {
		serviceDao.update(buildEntity(view));
	}

	@Override
	public Service buildEntity(ServiceView view) {
		Service entity = view.getId() != 0 ? serviceDao.get(view.getId()) : new Service();
		entity.setArchived(view.isArchived());
		entity.setDescription(view.getDescription());
		entity.setPrice(view.getPrice());
		entity.setTitle(view.getTitle());
		return entity;
	}

	@Override
	public List<Service> buildEntities(List<ServiceView> views) {
		List<Service> entities = new ArrayList<>();
		views.stream().forEach(v -> entities.add(buildEntity(v)));
		return entities;
	}

	@Override
	public List<ServiceView> buildViews(List<Service> entities) {
		List<ServiceView> views = new ArrayList<>();
		entities.stream().forEach(e -> views.add(buildView(e)));
		return views;
	}

	@Override
	public List<ServiceView> getServicesBySummerhouse(Integer summerhouseId) {
		return buildViews(serviceDao.getServicesBySummerhouse(summerhouseId));
	}
}
