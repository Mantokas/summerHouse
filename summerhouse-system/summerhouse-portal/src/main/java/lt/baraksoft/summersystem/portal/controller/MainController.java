package lt.baraksoft.summersystem.portal.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class MainController implements Serializable {
	private static final long serialVersionUID = -3124976676079274504L;

	@PostConstruct
	public void init() {

	}

}