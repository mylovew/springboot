package com.zwk.springboot;

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
        String bb= DigestUtils.md5Hex("62552964123420190815120000company@97Result(*)".getBytes("UTF-8")).toUpperCase();
//        String bb= MD5.MD5("62552964123420190815120000company@97Result(*)");
        System.out.println(bb);
    }
}
