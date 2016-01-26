package hannaeismant.m101j.session;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

public abstract class SessionTokenGenerator {

    private static SecureRandom random = new SecureRandom(new Date().toString().getBytes());

    public static String generate() {
        return new BigInteger(130, random).toString(32);
    }
}
