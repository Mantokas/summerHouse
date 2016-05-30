package lt.baraksoft.summersystem.portal.helper.impl;

import lt.baraksoft.summersystem.portal.helper.CryptoService;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;

/**
 * Created by Žygimantas on 2016-05-31.
 */
@Stateless
public class BcryptService implements CryptoService {

    private static final int SALT_LOG_ROUNDS = 12;

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(SALT_LOG_ROUNDS));
    }

    @Override
    public boolean checkPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate, hashed);
    }
}

