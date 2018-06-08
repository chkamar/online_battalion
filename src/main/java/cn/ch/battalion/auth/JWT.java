package cn.ch.battalion.auth;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by karma on 2018/4/6.
 */
public class JWT {

    //md5("cumt")
    private static final String SECRET = "781b4e431f0bb2dc5a60df3f165080e5";
    //7天后过期
    private static final Long EXPMILLIS = 1000L * 60L * 60L * 24 * 7;
    //如果距离过期还有2天，则更新token
    private static final Long BEFORE_EXPMILLTS = 1000L * 60L * 24 * 2;

    private static final String EXP = "exp";

    private static final String OBJECT = "object";

    public static <T> String creatToken(T object) {
        try {
            //默认HS256
            final JWTSigner jwtSigner = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(OBJECT, jsonString);
            claims.put(EXP, System.currentTimeMillis() + EXPMILLIS);
            return jwtSigner.sign(claims);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parseToken(String token, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String, Object> claims = verifier.verify(token);
            if (claims.containsKey(EXP) && claims.containsKey(OBJECT)) {
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String) claims.get(OBJECT);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static String updateToken(String token) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String, Object> claims = verifier.verify(token);
            if (claims.containsKey(EXP) && claims.containsKey(OBJECT)) {
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis && exp - currentTimeMillis < BEFORE_EXPMILLTS) {
                    Object object = claims.get(OBJECT);
                    return creatToken(object);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
