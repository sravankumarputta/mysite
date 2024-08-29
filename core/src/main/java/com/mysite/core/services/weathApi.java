package com.mysite.core.services;

import com.mysite.core.config.Api1;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
@Component(service = weathApi.class)
@Designate(ocd = Api1.class)
public class weathApi {
    private volatile String apiKey;
    private volatile String apiUrl;

    @Activate
    @Modified
    public void Act(Api1 ap){
        this.apiKey=ap.apiKey();
        this.apiUrl=ap.apiUrl();
    }
    public String getApiKey(){
        return apiKey;
    }
    public String getApiUrl(){
        return apiUrl;
    }
}
