package lt.baraksoft.summersystem.portal.helper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

@Stateless
public class UserViewHelperImpl implements UserViewHelper {

	@Inject
	private UserDao userDao;

	@Override
	public void save(UserView view) {
		User entity = view.getId() != null ? userDao.get(view.getId()) : new User();
		entity.setFirstname(view.getFirstName());
		entity.setLastname(view.getLastName());
		entity.setEmail(view.getEmail());
		entity.setFacebookId(view.getFacebookId());
		entity.setApproved(view.isApproved());
		entity.setArchived(view.isArchived());
		entity.setPassword(view.getPassword());
		entity.setPoints(view.getPoints());
		entity.setGroupNumber(view.getGroupNumber());
		entity.setValidTo(view.getValidTo());
		entity.setImage(view.getImage());
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
		view.setFacebookId(entity.getFacebookId());
		view.setApproved(entity.isApproved());
		view.setArchived(entity.isArchived());
		view.setPassword(entity.getPassword());
		view.setPoints(entity.getPoints());
		view.setGroupNumber(entity.getGroupNumber());
		view.setValidTo(entity.getValidTo());
		view.setImage(entity.getImage());
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
	public UserView findUserByLogin(UserView view) {
		String email = view.getEmail();
		String password = view.getPassword();
		User entity = userDao.getUserByLogin(email, password);
		return entity != null ? buildView(entity) : null;
	}

	@Override
	public UserView getUserByEmail(String email) {
		User entity = userDao.getUserByEmail(email);
		return entity != null ? buildView(entity) : null;
	}

	@Override
	public UserView findUserByFbId(String facebookId) {
		User entity = userDao.getUserByFacebookId(facebookId);
		return entity != null ? buildView(entity) : null;
	}

	@Override
	public boolean register(UserView view) {
		String email = view.getEmail();
		if (userDao.getUserByEmail(email) != null)
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
		view.setFacebookId(entity.getFacebookId());
		view.setApproved(entity.isApproved());
		view.setArchived(entity.isArchived());
		view.setPassword(entity.getPassword());
		view.setPoints(entity.getPoints());
		view.setGroupNumber(entity.getGroupNumber());
		view.setImage(entity.getImage());
		view.setValidTo(entity.getValidTo());
		view.setImage(entity.getImage());
		return view;
	}
}
