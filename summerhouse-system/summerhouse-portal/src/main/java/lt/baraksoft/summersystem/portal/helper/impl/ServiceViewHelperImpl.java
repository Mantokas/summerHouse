package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.portal.helper.ServiceViewHelper;
import lt.baraksoft.summersystem.portal.view.ServiceView;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-25.
 */

@Stateless
public class ServiceViewHelperImpl implements ServiceViewHelper{

    @Override
    public List<ServiceView> getServicesBySummerhouse(int summerhouseID) {


        return null;
    }
}
