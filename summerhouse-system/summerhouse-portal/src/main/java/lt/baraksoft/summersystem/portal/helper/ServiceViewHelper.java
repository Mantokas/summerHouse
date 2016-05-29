package lt.baraksoft.summersystem.portal.helper;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.portal.view.ServiceView;

/**
 * Created by LaurynasC on 2016-04-25.
 */

@Local
public interface ServiceViewHelper {

	List<ServiceView> buildViews(List<Service> entities);

	List<ServiceView> getServicesBySummerhouse(Integer summerhouseId);

	void save(ServiceView view);

	Service buildEntity(ServiceView view);

	List<Service> buildEntities(List<ServiceView> views);
}
