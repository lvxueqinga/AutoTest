package com.test.frame.mulitThraed;

import org.testng.annotations.Test;

public class multi1 {

    /**
     * 多线程 方法1
     *
     * invocationCount:用来设置用例被重复调用的次数
     * threadPoolSize： 来设置线程池数
     */

    @Test(invocationCount = 5,threadPoolSize = 3)
    public void  login(){
        System.out.println("吃饭成功");
        System.out.println("当前线程id = "+Thread.currentThread().getId());
    }

}
