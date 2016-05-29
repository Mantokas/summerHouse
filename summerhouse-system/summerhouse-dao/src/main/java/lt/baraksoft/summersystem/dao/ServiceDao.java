package lt.baraksoft.summersystem.dao;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.model.Service;

/**
 * Created by LaurynasC on 2016-05-02.
 */

@Local
public interface ServiceDao extends IGenericDao<Service, Integer> {

	List<Service> getServicesBySummerhouse(Integer summerhouseId);
	List<Service> getAllServices();
}
