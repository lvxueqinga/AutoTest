package com.test.frame.mulitThraed;

import org.testng.annotations.Test;

public class multi3 {

    @Test
    public void  login(){
        System.out.println("吃饭成功");
        System.out.println("当前线程id = "+Thread.currentThread().getId());
    }

    @Test
    public void  pay(){
        System.out.println("睡觉成功");
        System.out.println("当前线程id = "+Thread.currentThread().getId());
    }

    @Test
    public void  getup(){
        System.out.println("早起成功");
        System.out.println("当前线程id = "+Thread.currentThread().getId());
    }



}
