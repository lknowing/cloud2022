package com.atguigu.springcloud;

import java.time.ZonedDateTime;

public class TestTime {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        //2022-04-22T14:36:26.638+08:00[Asia/Shanghai]
    }
}
