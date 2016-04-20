package lt.baraksoft.summersystem.dao;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.model.Summerhouse;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Local
public interface SummerhouseDao {

	List<Summerhouse> getAllSummerhouses();

	void save(Summerhouse entity);

	Summerhouse get(Integer id);

}
