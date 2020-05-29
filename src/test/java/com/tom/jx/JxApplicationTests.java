package com.tom.jx;

import com.tom.jx.service.AsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JxApplicationTests {

    @Resource
    private AsyncService asyncService;

    @Test
    public void asyncTest() throws InterruptedException {
//        for (int i = 0; i < 10; i++) {
//            asyncService.executeAsync1();
//            asyncService.executeAsync2();
//        }
//        Thread.sleep(1000);
    }

}
