package com.kevin.epacms.util;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author: kevin
 * @since: 20:50 2022/3/29
 */
public class HttpUtil {
    private static final PoolingHttpClientConnectionManager CM;

    static {
        CM = new PoolingHttpClientConnectionManager();
        CM.setMaxTotal(100);
        CM.setDefaultMaxPerRoute(10);
    }

    public static String doGetHtml(String url){
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(CM).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getConfig());
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                if(response.getEntity() != null){
                    return EntityUtils.toString(response.getEntity(), "GBK");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static RequestConfig getConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(100000)
                .build();
    }

}
