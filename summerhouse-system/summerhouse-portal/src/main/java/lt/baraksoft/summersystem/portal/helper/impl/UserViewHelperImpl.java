package lt.baraksoft.summersystem.portal.helper.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.TransactionSynchronizationRegistry;

import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

@Stateless
public class UserViewHelperImpl implements UserViewHelper, Serializable {

	private static final long serialVersionUID = 3704824831918769814L;

	@Inject
	private UserDao userDao;

	@Override
	public void save(UserView view) {
		User entity = view.getId() != null ? userDao.get(view.getId()) : new User();
		entity.setFirstname(view.getFirstName());
		entity.setLastname(view.getLastName());
		entity.setEmail(view.getEmail());
		entity.setApproved(view.isApproved());
		entity.setArchived(view.isArchived());
		entity.setPassword(view.getPassword());
		entity.setPoints(view.getPoints());
		entity.setGroupNumber(view.getGroupNumber());
        entity.setValidTo(view.getValidTo());
		userDao.save(entity);
	}

	@Override
	public UserView getUser(Integer id) {
		User entity = userDao.get(id);
		UserView view = new UserView();
		view.setId(entity.getId());
		view.setFirstName(entity.getFirstname());
		view.setLastName(entity.getLastname());
		view.setEmail(entity.getEmail());
		view.setApproved(entity.isApproved());
		view.setArchived(entity.isArchived());
		view.setPassword(entity.getPassword());
		view.setPoints(entity.getPoints());
		view.setGroupNumber(entity.getGroupNumber());
		return view;
	}

	@Override
	public List<UserView> getAllUsers() {
		List<User> entities = userDao.getAllUsers();
		List<UserView> views = new ArrayList<>();
		entities.stream().forEach(e -> views.add(buildView(e)));
		return views;
	}

	@Override
	public UserView validateLogin(UserView view) {
        String email = view.getEmail();
        String password = view.getPassword();
		User entity = userDao.validateLogin(email, password);
        return entity != null ? buildView(entity) : null;
	}

	@Override
	public boolean register(UserView view) {
		String email = view.getEmail();

		if(userDao.getUserByEmail(email) != null)
			return false;
		save(view);
		return true;
	}

	private UserView buildView(User entity) {
		UserView view = new UserView();
		view.setId(entity.getId());
		view.setFirstName(entity.getFirstname());
		view.setLastName(entity.getLastname());
		view.setEmail(entity.getEmail());
		view.setApproved(entity.isApproved());
		view.setArchived(entity.isArchived());
		view.setPassword(entity.getPassword());
		view.setPoints(entity.getPoints());
		view.setGroupNumber(entity.getGroupNumber());
		return view;
	}
}
