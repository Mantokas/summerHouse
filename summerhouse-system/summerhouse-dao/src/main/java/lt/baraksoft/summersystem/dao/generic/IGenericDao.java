package lt.baraksoft.summersystem.dao.generic;

import javax.ejb.Local;

/**
 * Created by Zygimantas on 2016.03.18.
 */
@Local
public interface IGenericDao<T, K> {

	void remove(T entity);

	void save(T entity);

	T get(K id);

	T update(T entity);

}
