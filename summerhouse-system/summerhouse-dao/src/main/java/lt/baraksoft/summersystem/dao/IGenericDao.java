package lt.baraksoft.summersystem.dao;

/**
 * Created by Zygimantas on 2016.03.18.
 */
public interface IGenericDao<T, K>{

        T save(T entity);

        void remove(T entity);

        T findById(K id);
}
