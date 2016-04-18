package lt.baraksoft.summersystem.dao.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lt.baraksoft.summersystem.model.IEntity;

/**
 * Created by Zygimantas on 2016.03.18.
 */

// Butu neblogai kazkoki equalsAndHashProvideri extendint
public abstract class GenericDao<T extends IEntity<K>, K extends Serializable> implements IGenericDao<T, K> {

	@PersistenceContext(unitName = "summerhousePU")
	private EntityManager entityManager;
	private final Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public GenericDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Parameter entity is required");
		}
		entityManager.persist(entity);
	}

	@Override
	public void remove(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	// TODO transactional readonly true
	@Override
	public T get(K id) {
		if (id == null) {
			throw new IllegalArgumentException("Parameter id is required");
		}
		return entityManager.find(entityClass, id);
	}

	@Override
	public T update(T entity) {
		if (entity == null) {
			throw new IllegalArgumentException("Parameter entity is required");
		} else if (entity.getId() == null) {
			save(entity);
			return entity;
		}
		return entityManager.merge(entity);
	}

}
