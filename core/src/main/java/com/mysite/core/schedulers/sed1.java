package com.mysite.core.schedulers;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Component(service = {Runnable.class},immediate = true)
public class sed1 implements Runnable {
public static  final Logger log= LoggerFactory.getLogger(sed1.class);

//String SCEDNAME="new page-"+ UUID.randomUUID().toString();
String SCEDNAME = "newpage" + UUID.randomUUID().toString().replace("-", "");
String templatePath="/conf/mysite/settings/wcm/templates/temp2";
String parentPath="/content/mysite/us/en";
String title="new page "+System.currentTimeMillis();
    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Reference
    Scheduler scheduler;
    @Override
    public void run() {
        try {
            createPage();
        } catch (LoginException | WCMException e) {
            throw new RuntimeException(e);
        }
    }
    @Deactivate
    private void deActivate() {
        scheduler.unschedule(SCEDNAME);
        log.info("Scheduler deactivated: "+ SCEDNAME);
    }

    @Activate
    public void activate(){

        ScheduleOptions options = scheduler.EXPR("* * * * * ?").name(SCEDNAME).canRunConcurrently(false);
            scheduler.schedule(this, options);
            log.info("Scheduler activated: {}", SCEDNAME);
    }
    public void createPage() throws LoginException, WCMException {
        log.info("page creation started");
        log.info("================================================================================");
        Map<String, Object> service1 = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "writeService");
        log.info("service1 "+service1);
        log.info("resourceResolverFactory "+resourceResolverFactory);
        ResourceResolver reslover = resourceResolverFactory.getServiceResourceResolver(service1);
        log.info("resourceReslover1 "+reslover);
         PageManager pageManager = reslover.adaptTo(PageManager.class);
         log.info("pagemanager1 "+pageManager);
//        Page pageDone = pageManager.create(parentPath, SCEDNAME, templatePath, title);
//        log.info("pageDone "+pageDone);
    }

}

