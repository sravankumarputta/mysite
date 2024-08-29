
package com.mysite.core.models;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MyApi {
    public static Logger log = LoggerFactory.getLogger(MyApi.class);

    private String name;
    private String weather;
    private String temp;
    private String humid;

    private String API;
    private String lat;
    private String lon;

    @SlingObject
    private SlingHttpServletRequest request;

    @PostConstruct
    public void init() throws IOException {
        lat = request.getParameter("lat");
        lon = request.getParameter("lon");

        // Log parameters to ensure they are retrieved correctly
        log.info("Latitude: " + lat);
        log.info("Longitude: " + lon);

        // Validate if parameters are provided
        if (lat != null && lon != null) {
            API = "http://localhost:4502/bin/sravan/Api?lat=" + lat + "&lon=" + lon;

            BasicCredentialsProvider crdpro = new BasicCredentialsProvider();
            crdpro.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "admin"));

            CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(crdpro).build();
            HttpGet httpResponse = new HttpGet(API);

            try (CloseableHttpResponse res = httpClient.execute(httpResponse)) {
                String entity = EntityUtils.toString(res.getEntity());
                JsonObject obj = JsonParser.parseString(entity).getAsJsonObject();

                temp = obj.get("temp").getAsString();
                humid = obj.get("humid").getAsString();
                name = obj.get("name").getAsString();
                weather = obj.get("weather").getAsString();

                log.info("Temperature: " + temp);
                log.info("Humidity: " + humid);
                log.info("Name: " + name);
                log.info("Weather: " + weather);
            }
        } else {
            log.error("Latitude or Longitude is missing in the request.");
        }
    }

    public String getWeather() {
        return weather;
    }

    public String getHumid() {
        return humid;
    }

    public String getTemp() {
        return temp;
    }

    public String getName() {
        return name;
    }
}


//working code need to check once again
