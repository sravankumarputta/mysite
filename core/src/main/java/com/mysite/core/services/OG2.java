package com.mysite.core.services;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Component;

@Component(service = OG1.class)
public class OG2 implements OG1{

    @ValueMapValue(name = "fname")
    String fname;

    @Override
    public String t1() {
        return "hi sravan this is servlets class u can use this..";
    }

    @Override
    public String getFname() {
        return fname;
    }
}