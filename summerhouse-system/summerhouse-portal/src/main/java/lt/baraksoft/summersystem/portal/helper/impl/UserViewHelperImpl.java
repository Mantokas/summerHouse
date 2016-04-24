package lt.baraksoft.summersystem.portal.helper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lt.baraksoft.summersystem.dao.UserDao;
import lt.baraksoft.summersystem.model.User;
import lt.baraksoft.summersystem.portal.helper.UserViewHelper;
import lt.baraksoft.summersystem.portal.view.UserView;

public class UserViewHelperImpl implements UserViewHelper {
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

	public void updateFirstname(UserView view){
        User entity = userDao.get(view.getId());
        entity.setFirstname(view.getFirstName());
        userDao.update(entity);
    }
}
