package com.cycloneboy.jsoupdata.utils;

import com.cycloneboy.jsoupdata.entity.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-12-15 22:03
 **/
public class JsoupUtils {

    public static Document getDocumentFromUrlUseProxy(String url, Proxy useProxy) throws IOException {

        Document document = Jsoup.connect(url).ignoreContentType(true)
                .userAgent(ProxyConstants.getRandomUserAgent())
                .proxy(useProxy.getIp(),Integer.valueOf(useProxy.getPort()))
                .ignoreHttpErrors(true)
                .timeout(3000)
                .get();

        return document;
    }

}
