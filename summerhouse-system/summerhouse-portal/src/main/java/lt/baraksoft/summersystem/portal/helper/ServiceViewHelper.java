package lt.baraksoft.summersystem.portal.helper;

import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.model.Summerhouse;
import lt.baraksoft.summersystem.portal.view.ServiceView;
import lt.baraksoft.summersystem.portal.view.SummerhouseView;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-25.
 */

@Local
public interface ServiceViewHelper {

    List<ServiceView> buildViews(List<Service> entities);
}
