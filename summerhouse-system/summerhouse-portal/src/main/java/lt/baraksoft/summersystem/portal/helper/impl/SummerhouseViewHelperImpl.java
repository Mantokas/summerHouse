package lt.baraksoft.summersystem.portal.helper.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.dao.model.SummerhouseSearch;
import lt.baraksoft.summersystem.model.Summerhouse;
import lt.baraksoft.summersystem.portal.helper.ServiceViewHelper;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by LaurynasC on 2016-04-19.
 */

@Stateless
public class SummerhouseViewHelperImpl implements SummerhouseViewHelper, Serializable {

	@Inject
	private SummerhouseDao summerhouseDao;

	@Inject
	private ServiceViewHelper serviceViewHelper;

	@Override
	public List<SummerhouseView> getAllSummerhouses() {
		List<Summerhouse> entities = summerhouseDao.getAllSummerhouses();
		List<SummerhouseView> views = new ArrayList<>();
		entities.stream().forEach(e -> views.add(buildView(e)));
		return views;
	}

	@Override
	public void save(SummerhouseView view) {
		Summerhouse entity = view.getId() != null ? summerhouseDao.get(view.getId()) : new Summerhouse();
		entity.setAddress(view.getAddress());
		entity.setCapacity(view.getCapacity());
		entity.setDateFrom(view.getDateFrom());
		entity.setDateTo(view.getDateTo());
		entity.setDescription(view.getDescription());
		entity.setArchived(view.isArchived());
		entity.setPrice(view.getPrice());
		entity.setTitle(view.getTitle());
		summerhouseDao.save(entity);
	}

	@Override
	public List<SummerhouseView> search(SummerhouseSearch search) {
		List<SummerhouseView> views = new ArrayList<>();
		summerhouseDao.search(search).stream().forEach(s -> views.add(buildView(s)));
		return views;
	}

	private SummerhouseView buildView(Summerhouse entity) {
		SummerhouseView view = new SummerhouseView();
		view.setId(entity.getId());
		view.setAddress(entity.getAddress());
		view.setCapacity(entity.getCapacity());
		view.setDateFrom(entity.getDateFrom());
		view.setDateTo(entity.getDateTo());
		view.setDescription(entity.getDescription());
		view.setArchived(entity.isArchived());
		view.setPrice(entity.getPrice());
		view.setTitle(entity.getTitle());
		view.setServiceViews(serviceViewHelper.buildViews(entity.getServiceList()));
		return view;
	}

}
