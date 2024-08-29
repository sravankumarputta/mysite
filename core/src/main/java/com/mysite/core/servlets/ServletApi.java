package com.mysite.core.servlets;

import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/sravan/Api")
public class ServletApi extends SlingAllMethodsServlet {


    public static Logger log= LoggerFactory.getLogger(ServletApi.class);
    String S1;
    String API;
    String lat;
    String lon;
    String name;
    String weather;
    String humid;
    String temp;
    String apiKey="1f87e5de93f1954a9246f0b344a4558a";


    @Override
    protected void doGet( SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
       lat= request.getParameter("lat");
       lon= request.getParameter("lon");
        JsonObject l = null;
        try {
            l = fetchApi();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("application/json");
        response.getWriter().write(String.valueOf(l));
    }

    public JsonObject fetchApi() throws IOException, JSONException {
    API="https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lat+"&appid="+apiKey;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet HTTP = new HttpGet(API);
        CloseableHttpResponse response = httpclient.execute(HTTP);
        HttpEntity entity = response.getEntity();
        S1 = EntityUtils.toString(entity);
        log.info("String thing "+S1);
        JSONObject json = new JSONObject(S1);

        JsonObject j1 = new JsonObject();

        weather=json.getJSONArray("weather").getJSONObject(0).getString("description");
        log.info("weatherApp"+weather);
        temp=json.getJSONObject("main").get("temp").toString();
        humid=json.getJSONObject("main").get("humidity").toString();
        name=json.get("name").toString();
        j1.addProperty("weather",weather);
        j1.addProperty("temp",temp);
        j1.addProperty("humid",humid);
        j1.addProperty("name",name);
       return j1;
    }

}
