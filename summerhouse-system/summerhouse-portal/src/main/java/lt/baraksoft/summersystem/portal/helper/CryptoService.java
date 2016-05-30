package lt.baraksoft.summersystem.portal.helper;

/**
 * Created by Žygimantas on 2016-05-31.
 */
public interface CryptoService {

    String hashPassword(String password);

    boolean checkPassword(String candidate, String hashed);
}
