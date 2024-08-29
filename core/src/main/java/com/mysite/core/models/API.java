//package com.mysite.core.models;
//
//
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.apache.commons.io.IOUtils;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.*;
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.models.annotations.DefaultInjectionStrategy;
//import org.apache.sling.models.annotations.Model;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
////import org.springframework.util.FileCopyUtils;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import javax.inject.Inject;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
//@Model(adaptables = {SlingHttpServletRequest.class, Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//public class API {
//    public static final Logger log= LoggerFactory.getLogger(API.class);
//    @Inject
//    int fname;
//
//    String name;
//    String humid;
//    String weatherDescription;
//
//
//    @PostConstruct
//    public void init() throws IOException, JSONException {
//          fetchApi(fname);
//     }
//
//     public void fetchApi(int i) throws IOException, JSONException {
//         List<String> lt=new ArrayList<>();
//         log.info("1");
//         String Api="https://api.openweathermap.org/data/2.5/weather?q="+i+"&appid=1f87e5de93f1954a9246f0b344a4558a";
//
////         ======================1 method==================
//         URL url=new URL(Api);
//         HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
//          httpURLConnection.setRequestMethod("GET");
//         InputStreamReader inpStream = new InputStreamReader(httpURLConnection.getInputStream());
//         String data= IOUtils.toString(inpStream);
//         JsonObject jj = JsonParser.parseString(data).getAsJsonObject();
//         JsonObject data2 = jj.getAsJsonObject("main");
//         String temp = data2.get("temp").toString();
//         log.info("temp1 "+temp);
////=================================2 method===========================================
//         CloseableHttpClient httpReq = HttpClients.createDefault();
//         log.info("httpreq "+httpReq);
//         HttpGet getReq = new HttpGet(Api);
//         log.info("getReq "+getReq);
//         CloseableHttpResponse closereq = httpReq.execute(getReq);
//         log.info("closeReq "+closereq);
//         String jsonRes = EntityUtils.toString(closereq.getEntity());
//         log.info("jsonRes "+jsonRes);
//         JSONObject json = new JSONObject(jsonRes);
//         log.info("jsonFormate "+json);
//         weatherDescription = json.getJSONArray("weather").getJSONObject(0).getString("description");
//         humid = json.getJSONObject("main").get("humidity").toString();
//
//         log.info("weatherDescription"+weatherDescription);
//          name = json.get("name").toString();
//         log.info("name1 "+name);
//         log.info("humid1 "+humid);
//     }
//
//     public String getHumid(){
//        return humid;
//     }
//     public String getName(){
//        return name;
//     }
//     public String getWeatherDescription(){
//        return weatherDescription;
//     }
//
//}
//
//
//
//
///*HttpClient httpClient=
//         CloseableHttpClient httpC = HttpClients.createDefault();
//         try{
//             HttpGet httpGet = new HttpGet(Api);
//             log.info("httpreq "+httpGet);
//             CloseableHttpResponse response = httpC.execute(httpGet);
//             String JSONobj=EntityUtils.toString(response.getEntity());
//             JSONObject jsonObject = new JSONObject(JSONobj);
//             log.info("json data"+jsonObject);
////             String string = EntityUtils.toString(execute.getEntity());
////             log.info("api call "+string);
//
//         }catch(Exception e) {
//                 log.info("thappu"+e);
//         }*/