package lt.baraksoft.summersystem.portal.helper;

import java.util.List;

import javax.ejb.Local;

import lt.baraksoft.summersystem.portal.view.SummerhouseView;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Local
public interface SummerhouseViewHelper {

	List<SummerhouseView> getAllSummerhouses();
}