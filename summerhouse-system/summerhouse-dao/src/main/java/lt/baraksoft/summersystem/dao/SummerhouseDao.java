package lt.baraksoft.summersystem.dao;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.dao.generic.IGenericDao;
import lt.baraksoft.summersystem.dao.model.SummerhouseSearch;
import lt.baraksoft.summersystem.model.Summerhouse;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Local
public interface SummerhouseDao extends IGenericDao<Summerhouse, Integer> {

	List<Summerhouse> getAllSummerhouses();

	List<Summerhouse> search(SummerhouseSearch search);

}
