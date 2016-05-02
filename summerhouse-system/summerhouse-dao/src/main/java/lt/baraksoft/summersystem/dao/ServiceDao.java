package lt.baraksoft.summersystem.dao;

import lt.baraksoft.summersystem.model.Service;
import lt.baraksoft.summersystem.model.Summerhouse;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by LaurynasC on 2016-05-02.
 */

@Local
public interface ServiceDao {
    List<Service> getServicesBySummerhouse(Summerhouse summerhouse);
}
