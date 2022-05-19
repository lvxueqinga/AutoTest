package com.test.frame;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.Test;

public class IgnoreTest {

    @Ignore
    public void ignore1(){
        System.out.println("ignore .. ");
    }

    @Test(enabled = false)
    public void ignore2(){
        System.out.println("ignore .. ");
    }
}
