package lt.baraksoft.summersystem.portal.helper;

import lt.baraksoft.summersystem.portal.view.ServiceView;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-25.
 */

@Local
public interface ServiceViewHelper {

    List<ServiceView> getServicesBySummerhouse(int summerhouseID);
}
