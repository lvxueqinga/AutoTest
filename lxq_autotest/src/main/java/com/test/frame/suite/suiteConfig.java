package com.test.frame.suite;

import org.testng.annotations.*;

public class suiteConfig {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite..");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite..");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest..");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest..");
    }


}
