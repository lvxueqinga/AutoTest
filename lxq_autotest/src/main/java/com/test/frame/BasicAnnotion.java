package com.test.frame;

import org.testng.annotations.*;

public class BasicAnnotion {

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("beforeMethod");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("afterMethod");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("beforeClass");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("afterClass");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("beforeSuite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
    }

    @Test
    public void test1(){
        System.out.println("12345rt");

    }

    @Test
    public void test2(){
        System.out.println("456778");

    }
}
