package lt.baraksoft.summersystem.dao.generic;

/**
 * Created by Zygimantas on 2016.03.18.
 */
public interface IGenericDao<T, K> {

	void remove(T entity);

	void save(T entity);

	T get(K id);

	T update(T entity);

}
