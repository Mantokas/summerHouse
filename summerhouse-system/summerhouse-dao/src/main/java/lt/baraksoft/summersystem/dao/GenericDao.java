package lt.baraksoft.summersystem.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import lt.baraksoft.summersystem.model.IEntity;

/**
 * Created by Zygimantas on 2016.03.18.
 */
public abstract class GenericDao<T extends IEntity<K>, K extends Serializable> implements IGenericDao<T, K> {

	// reikia ka�kaip suwirinti, gal @Inject?
	private EntityManager entityManager;

	protected Class<T> entityClass;

	public GenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T save(T entity) {
		if (entity.getVersion() == null) {
			entityManager.persist(entity);
			return entity;
		} else {
			return entityManager.merge(entity);
		}
	}

	@Override
	public void remove(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	// reikia užžymėti kaip transactional read only true, bet ne kaip springe
	// :D
	// man atrodo taip pat settinasi kaip ir springe su anotacija ir tiek
	@Override
	public T findById(K id) {
		return entityManager.find(entityClass, id);
	}
}
