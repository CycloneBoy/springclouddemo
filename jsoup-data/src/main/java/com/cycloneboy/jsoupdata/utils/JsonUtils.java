package com.cycloneboy.jsoupdata.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cycloneboy.jsoupdata.entity.Proxy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: learn
 * @description:
 * @author: cycloneboy
 * @create:2018-12-15 19:41
 **/
public class JsonUtils {

    private String filename = "crawer/proxylist.json";

    public JsonUtils(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 读取 proxy List from json
     */
    public  List<Proxy> readProxyJson(){
        StringBuilder jsonStr  = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            String temp = "";
            while ((temp = reader.readLine()) != null){
                jsonStr.append(temp);
            }
            reader.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        JSONObject json = JSON.parseObject(jsonStr.toString());

        List<Proxy> proxyList = JSON.parseArray(json.getString("proxy"),Proxy.class);

        return  proxyList;
    }

    /**
     * 写proxy list to json
     * @param list
     */
    public void writeProxyJson(List<Proxy> list){
        try {

            String jsonstr = JSON.toJSONString(list);
            System.out.println(list);
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(filename))));
            writer.write("{\"proxy\" : " + jsonstr + "}");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {

        JsonUtils jsonUtils = new JsonUtils("test.json");
        List<Proxy> list  = new ArrayList<>();
        list.add(new Proxy("127.0.0.1","1000","1.1秒"));
        list.add(new Proxy("127.0.0.1","1002","1.99秒"));
        list.add(new Proxy("127.0.0.1","1003","0.1秒"));
        list.add(new Proxy("127.0.0.1","1004","1秒"));

        jsonUtils.writeProxyJson(list);

        List<Proxy> readList = jsonUtils.readProxyJson();

        for (Proxy proxy : readList) {
            System.out.println(proxy);
            System.out.println(proxy.getWeight());
        }
    }
}
