package com.cycloneboy.jsoupdata.crawer;

import com.cycloneboy.jsoupdata.entity.Proxy;
import com.cycloneboy.jsoupdata.entity.moive.Moive;
import com.cycloneboy.jsoupdata.entity.moive.MoiveUrl;
import com.cycloneboy.jsoupdata.repository.MoiveUrlRepository;
import com.cycloneboy.jsoupdata.utils.JsonUtils;
import com.cycloneboy.jsoupdata.utils.JsoupUtils;
import com.cycloneboy.jsoupdata.utils.ProxyConstants;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: springclouddemo
 * @description:
 * @author: cycloneboy
 * @create:2018-12-12 00:17
 **/
@Log
@RunWith(SpringRunner.class)
@SpringBootTest
public class DouBanMoiveCrawer {

    @Autowired
    private MoiveUrlRepository moiveUrlRepository;

    String doubanMoiveTop250Url = "https://movie.douban.com/top250?start=0&filter=";

    public void getMoiveUrl(String url) throws IOException {
        //获取编辑推荐页
        Document document = Jsoup.connect(url)
                //模拟火狐浏览器
                .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")
                .get();
        Elements content = document.select("#content > div > div.article > ol");

        Elements infos = document.getElementsByClass("info");
        String moiveUrl ;
        String moiveTitle;
        MoiveUrl moive;
        for (Element info : infos) {
            moiveUrl = info.getElementsByTag("a").attr("href");
//            System.out.println("url : " + moiveUrl);
            moiveTitle = info.getElementsByClass("title").text();
//            System.out.println("title : " + moiveTitle );
            moive =  new MoiveUrl(moiveTitle,moiveUrl);
            moiveUrlRepository.save(moive);
            log.info("save one moive: " + moive.toString());
        }

    }

    @Test
    public void test1() throws IOException {

        String url = "https://movie.douban.com/top250?start=0&filter=";
        List<Map<String,String>> moiveList = new ArrayList<>();
        Map<String,String> moive = new HashMap<>();

        for (int i = 0; i < 250; i += 25) {
            url = "https://movie.douban.com/top250?start="+String.valueOf(i)+ "&filter=";
            System.out.println("爬取 ：" + String.valueOf(i) + " : " + url);
            getMoiveUrl(url);
        }

    }

    @Test
    public void testGetOneDobanMoive(){
        String moiveDetileUrl = "https://movie.douban.com/subject/1292052/";
        String proxyFilename = "D:\\java\\idea\\a2018\\springcloud\\springclouddemo\\jsoup-data\\src\\main\\resources" +
                "\\crawer\\proxylist.json";
        JsonUtils jsonUtils = new JsonUtils(proxyFilename);

        List<Proxy>proxyList = jsonUtils.readProxyJson();

        boolean downSuccessFlag = false;
        int index = 0;
        Document document = null;

        while (downSuccessFlag != true){
            Proxy useProxy = new Proxy();
            try {
                useProxy = proxyList.get(index);
                document = Jsoup.connect(moiveDetileUrl).ignoreContentType(true)
                        .userAgent(ProxyConstants.getRandomUserAgent())
                        .proxy(useProxy.getIp(),Integer.valueOf(useProxy.getPort()))
                        .ignoreHttpErrors(true)
                        .timeout(3000)
                        .get();
                downSuccessFlag = true ;
                break;
            } catch (Exception e) {
                log.info("proxy error : "  + e.getMessage() + " ," + useProxy.toString());
                proxyList.remove(index);
                if(++index == proxyList.size()){
                    log.info("proxy use off");
                }
            }

        }

        Elements infos = document.select("#info");
        Moive movie = new Moive();

        for (Element info : infos) {
            if (info.childNodeSize() > 0) {
                String key = info.getElementsByAttributeValue("class", "pl").text();
                if ("导演".equals(key)) {
                    movie.setDirector(info.getElementsByAttributeValue("class", "attrs").text());
                } else if ("编剧".equals(key)) {
                    movie.setScenarist(info.getElementsByAttributeValue("class", "attrs").text());
                } else if ("主演".equals(key)) {
                    movie.setActors(info.getElementsByAttributeValue("class", "attrs").text());
                } else if ("类型:".equals(key)) {
                    movie.setType(info.getElementsByAttributeValue("property", "v:genre").text());
                } else if ("制片国家/地区:".equals(key)) {
                    Pattern patternCountry = Pattern.compile(".制片国家/地区:</span>.+[\\u4e00-\\u9fa5]+.+[\\u4e00-\\u9fa5]+\\s+<br>");
                    Matcher matcherCountry = patternCountry.matcher(info.html());
                    if (matcherCountry.find()) {
                        movie.setCountry(matcherCountry.group().split("</span>")[1].split("<br>")[0].trim());// for example: >制片国家/地区:</span> 中国大陆 / 香港     <br>
                    }
                } else if ("语言:".equals(key)) {
                    Pattern patternLanguage = Pattern.compile(".语言:</span>.+[\\u4e00-\\u9fa5]+.+[\\u4e00-\\u9fa5]+\\s+<br>");
                    Matcher matcherLanguage = patternLanguage.matcher(info.html());
                    if (matcherLanguage.find()) {
                        movie.setLanguage(matcherLanguage.group().split("</span>")[1].split("<br>")[0].trim());
                    }
                } else if ("上映日期:".equals(key)) {
                    movie.setReleaseDate(info.getElementsByAttributeValue("property", "v:initialReleaseDate").text());
                } else if ("片长:".equals(key)) {
                    movie.setRuntime(info.getElementsByAttributeValue("property", "v:runtime").text());
                }
            }
        }
        movie.setTags(document.getElementsByClass("tags-body").text());
        movie.setName(document.getElementsByAttributeValue("property", "v:itemreviewed").text());
        movie.setRatingNum(document.getElementsByAttributeValue("property", "v:average").text());
        //#interest_sectl > div.rating_wrap.clearbox > div.rating_self.clearfix > div > div.rating_sum > a > span
        movie.setVoteNum(document.getElementsByAttributeValue("property", "v:votes").text());

        System.out.println("---------------------------------");
        System.out.println(movie.toString());
    }
}
