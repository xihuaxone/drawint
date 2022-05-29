package utils;

import com.alibaba.fastjson.JSON;
import com.drawint.common.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtilTest {
    @Test
    public void test() {
        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("a", "1");
        payloadMap.put("b", 2);

        {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, 10);
            Date time = calendar.getTime();

            String sign = JWTUtil.sign(payloadMap, time);

            System.out.println(sign);

            Claims claims = JWTUtil.unSign(sign);
            System.out.println(JSON.toJSONString(claims));
            assert claims.containsKey("a") && claims.get("a", String.class).equals(payloadMap.get("a"));
            assert claims.containsKey("b") && claims.get("b", Integer.class).equals(payloadMap.get("b"));
            assert claims.containsKey("exp") && claims.get("exp", Date.class).getTime() == (time.getTime() / 1000 * 1000);
        }

        {
            String sign = JWTUtil.sign(payloadMap);

            System.out.println(sign);

            Claims claims = JWTUtil.unSign(sign);
            System.out.println(JSON.toJSONString(claims));
            assert claims.containsKey("a") && claims.get("a", String.class).equals(payloadMap.get("a"));
            assert claims.containsKey("b") && claims.get("b", Integer.class).equals(payloadMap.get("b"));
            assert !claims.containsKey("exp");
        }
    }

    @Test
    public void test2() {
        byte a = '0';
        System.out.println((int)a);
    }
}
