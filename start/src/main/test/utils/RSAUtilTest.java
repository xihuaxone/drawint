package utils;

import com.drawint.common.utils.RSAUtil;
import org.junit.Test;

public class RSAUtilTest {
    @Test
    public void test() {
        String content = "aergaiure!@gbeai]rgre$iug[irey@gbr\\eiy#ey^gbareiug2&4355+475=6765*7657356(6y5wtgev5ybe)354_-3165156;'.lhk.h";
        String encryptStr = RSAUtil.encryptByPublicKey(content);
        System.out.println(encryptStr);
        String originContent = RSAUtil.decryptByPrivateKey(encryptStr);
        assert originContent.equals(content);
    }
}
