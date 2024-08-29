package com.mysite.core.models;

import com.mysite.core.config.Api1;
import com.mysite.core.services.weathApi;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ApiSer {
    public static final Logger log= LoggerFactory.getLogger(ApiSer.class);
    @Inject
    @Via("resource")
    int fname;
//    String apiKey;
//    String apiUrl;
    String name;
    String humid;
    String weatherDescription;
    String icon;

    @OSGiService
    weathApi wp;

    @PostConstruct
    public void init() throws IOException, JSONException {
        log.info("class API SER");
        fetchApi(fname);
    }

    public void fetchApi(int i) throws IOException, JSONException {
        List<String> lt = new ArrayList<>();
        log.info("1");
        String Api = wp.getApiUrl() + i + wp.getApiKey();
        CloseableHttpClient httpReq = HttpClients.createDefault();
        log.info("httpreq "+httpReq);
        HttpGet getReq = new HttpGet(Api);
        log.info("getReq "+getReq);
        CloseableHttpResponse closereq = httpReq.execute(getReq);
        log.info("closeReq "+closereq);
        String jsonRes = EntityUtils.toString(closereq.getEntity());
        log.info("jsonRes "+jsonRes);
        JSONObject json = new JSONObject(jsonRes);
        log.info("jsonFormate "+json);
        weatherDescription = json.getJSONArray("weather").getJSONObject(0).getString("description");
        icon=json.getJSONArray("weather").getJSONObject(0).getString("icon").toString();
        humid = json.getJSONObject("main").get("humidity").toString();

        log.info("weatherDescription"+weatherDescription);
        name = json.get("name").toString();
        log.info("name1 "+name);
        log.info("humid1 "+humid);
    }


    public String getHumid(){
        return humid;
    }

    public String getName(){
        return name;
    }


    public String getWeatherDescription(){
        return weatherDescription;
    }
    public String getIcon(){
        return icon;
    }


    }


