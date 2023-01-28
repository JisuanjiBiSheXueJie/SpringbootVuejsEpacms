package com.kevin.epacms;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kevin.epacms.entity.News;
import com.kevin.epacms.service.NewsService;
import com.kevin.epacms.util.HttpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: kevin
 * @since : 21:11 2022/3/29
 */
public class HttpTest {
    @Autowired
    private NewsService newsService;

    @Test
    public void test(){
        String url = "https://blog.csdn.net/Bao_S_J/article/details/62056543";
        String[] strings = spiltURL(url);
        for (String string : strings) {
            System.out.println(string);
        }
    }
    private static String getHostName(String url) {
        String substring = url.substring(url.indexOf("//") + 2);
        return substring.substring(0, substring.indexOf("/"));
    }
    private String[] spiltURL(String url){
        String regStr = "^((http|https)://)([\\w-]+\\.)+[\\w-]+";
        Pattern compile = Pattern.compile(regStr);
        Matcher matcher = compile.matcher(url);
        String hostName = "";
        if (matcher.matches()) {
            String substring = url.substring(url.indexOf("//") + 2);
            hostName = substring.substring(0, substring.indexOf("/"));
        }
        String baseUrl = "";
        while (matcher.find()){
            baseUrl = matcher.group(0);
        }
        String pathSrc = url.substring(baseUrl.length());
        return new String[]{baseUrl, pathSrc, hostName};
    }


    @Test
    public void test03(){
        String jsonStr = "{\"allNews\":[{\"id\":null,\"title\":\"天津津南和河南安阳现奥密克戎本土病例 呈现哪些特点？如…\",\"url\":\"http://health.people.com.cn/n1/2022/0110/c14739-32327891.html\",\"date\":\"2022-04-01\"},{\"id\":null,\"title\":\"深圳新冠疫苗接种过千万剂次\",\"url\":\"http://health.people.com.cn/n1/2021/0521/c14739-32109512.html\",\"date\":\"2022-04-01\"}]}";
        JSONObject jsonObject = JSONUtil.parseObj(jsonStr);
        String str  = jsonObject.get("allNews").toString();
        JSONArray jsonArray = JSONUtil.parseArray(str);
        //解析json数组
        List<News> list = JSONUtil.toList(jsonArray, News.class);
        System.out.println(list);
    }
}
