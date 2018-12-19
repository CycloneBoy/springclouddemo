package com.cycloneboy.jsoupdata.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: learn
 * @description:
 * @author: cycloneboy
 * @create:2018-12-14 00:33
 **/
@NoArgsConstructor
@Data
public class Proxy {

    private String ip;

    private String port;

    private String speed;

    private boolean state = true;

    public double getWeight(){
        try {
            if (speed != null && speed != "" && speed.length() >=1){
                return  Double.valueOf(speed.substring(0,speed.indexOf("秒")));
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return 0;
    }

    public Proxy(String ip, String port, String speed) {
        this.ip = ip;
        this.port = port;
        this.speed = speed;
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy("","","");
        System.out.println(String.valueOf(proxy.getWeight()));
        System.out.println(proxy.toString());
    }
}
