package com.zwk.springboot;

import com.zwk.springboot.util.HttpUtils;
import com.zwk.springboot.util.MD5;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Test
    public void contextLoads() {
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        DisableSSLCertificateCheckUtil.disableChecks();
        String url = "https://192.168.22.104:8088/serverhttps/test";
        String s = HttpUtils.httpsConnection(url,"GET",null,"UTF-8");
        System.out.println("返回结果："+s);
    }
}
