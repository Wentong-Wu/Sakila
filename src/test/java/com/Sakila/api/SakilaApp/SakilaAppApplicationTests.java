package com.Sakila.api.SakilaApp;

import io.cucumber.java.bs.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SakilaAppApplicationTests {

//	@Test
//	void contextLoads() {
//	}
    @Test
    public void test_actor_initalise(){
        Actor a = new Actor();
    }

    @Test
    public void test_actor_full_init(){
        Actor a = new Actor("Who","Wu");
    }
}
