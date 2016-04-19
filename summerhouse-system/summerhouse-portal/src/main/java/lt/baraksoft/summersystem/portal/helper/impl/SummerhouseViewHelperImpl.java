package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.dao.SummerhouseDao;
import lt.baraksoft.summersystem.model.Summerhouse;
import lt.baraksoft.summersystem.portal.helper.SummerhouseViewHelper;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-19.
 */
public class SummerhouseViewHelperImpl implements SummerhouseViewHelper, Serializable{
    @Inject
    private SummerhouseDao summerhouseDao;

    @Override
    public List<SummerhouseView> getAllSummerhouses() {
        List<Summerhouse> entities = summerhouseDao.getAllSummerhouses();
        List<SummerhouseView> views = new ArrayList<>();
        entities.stream().forEach(e -> views.add(buildView(e)));
        return views;
    }

    private SummerhouseView buildView(Summerhouse entity) {
        SummerhouseView view = new SummerhouseView();
        view.setId(entity.getId());
        view.setAddress(entity.getAddress());
        view.setCapacity(entity.getCapacity());
        view.setDate_from(entity.getDateFrom());
        view.setDate_to(entity.getDateTo());
        view.setDescription(entity.getDescription());
        view.setIsArchived(entity.getIsArchived());
        view.setPrice(entity.getPrice());
        view.setTitle(entity.getTitle());
        return view;
    }


}
