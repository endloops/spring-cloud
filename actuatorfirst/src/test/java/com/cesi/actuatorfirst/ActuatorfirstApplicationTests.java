package com.cesi.actuatorfirst;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActuatorfirstApplicationTests {
	private static Logger log = LogManager.getLogger(ActuatorfirstApplicationTests.class);
	@Test
	public void testSocketAppender() throws Exception{  
      
        System.out.println(11);
        log.warn("111 logger warn");  
        log.debug("222 logger debug");  
        log.info("333 bar logger info");  
        log.debug("444 bar logger debug long long ");  
		log.exit();
    }

}
