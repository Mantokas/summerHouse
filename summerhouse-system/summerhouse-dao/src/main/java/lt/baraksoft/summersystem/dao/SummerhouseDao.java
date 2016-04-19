package lt.baraksoft.summersystem.dao;

import lt.baraksoft.summersystem.model.Summerhouse;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by LaurynasC on 2016-04-19.
 */
@Local
public interface SummerhouseDao {

    List<Summerhouse> getAllSummerhouses();

}
