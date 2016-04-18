package lt.baraksoft.summersystem.portal.helper.impl;

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
		entity.setIsArchived(view.isArchived());
		entity.setPassword(view.getPassword());
		entity.setPoints(view.getPoints());
		entity.setId(1);
		userDao.save(entity);
	}

	@Override
	public UserView getUser(Integer id) {
		User entity = userDao.get(id);

		UserView view = new UserView();
		view.setId(entity.getId());
		view.setFirstName(entity.getFirstname());

		return view;
	}

}
