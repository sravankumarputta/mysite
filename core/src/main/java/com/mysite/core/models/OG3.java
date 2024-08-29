package com.mysite.core.models;

import com.mysite.core.services.OG1;
import com.mysite.core.services.OG2;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
//@Component(service = Model.class)
@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OG3 {
    public static Logger log= LoggerFactory.getLogger(OG3.class);


    @OSGiService
    OG1 gg;
//@Activate
//@Modified
    public String getT1(){
        log.info("ggtt "+gg.t1());

        return gg.t1();
    }
    public String cichu(){
        return gg.getFname();
    }
}
