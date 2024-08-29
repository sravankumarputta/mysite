package com.mysite.core.models;

import com.adobe.xfa.Obj;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.components.ComponentContext;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.ServletException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


//@Model(adaptables = {SlingHttpServletRequest.class,Resource.class},adapters = Author1.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
//@SlingServletResourceTypes(resourceTypes = "",methods = "",selectors = "",extensions = "")
@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, adapters = Emp.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Author1 extends SlingAllMethodsServlet implements Emp {
    //   private static final Logger log=LoggerFactory.getLogger(Author1.class);
    private static final Logger log = LoggerFactory.getLogger(Author1.class);
//    @RequestAttribute()

    @ScriptVariable//it is used for current objects
    Page currentPage;

    @ScriptVariable
    ComponentContext context;

    @ScriptVariable
    Node currentNode;


    @ScriptVariable
    ComponentContext componentContext;


    //    ===========================================
    @SlingObject  //commonly used object purpose we used it .
            Resource resource;
    @Self
    SlingHttpServletRequest req;

    @SlingObject
    ResourceResolver resourceResolver;
//    ====================================================


    @Inject
//    @Via("resource")
    @Default(values = "sravan")
    String fname;
    @Inject
    String lname;
    @Inject
    @Default(doubleValues = 10.0)
//    @Via("resource")
    double percentage;
    @Inject
    int rollno;

    @Override
    public String getFirstName() {
        return fname;
    }

    @Override
    public String getLastName() {
        return lname;
    }

    @Override
    public double getPercentage() {
        return percentage;
    }

    @Override
    public int getRollNo() {
        return rollno;
    }

    public String getContext() {
        return componentContext.getDecorationTagName();
    }
//=========================pagetitle=======================================
    public String getPageTitle() {
//        return currentPage.getTitle();
        return currentPage.getPageTitle();

    }
//    ============================================================================

    public String getContext1() {
        Resource res = resourceResolver.getResource("/apps/mysite/components/Gemini");

        log.info("resouce" + Objects.isNull(res));
        return res.getName();
    }
//======================session================================================
    public Object getSession() {
        ResourceResolver res = req.getResourceResolver();
        Session res1 = res.adaptTo(Session.class);
        Object ip = res1.getAttribute("ip");
//        String[] at = res1.getAttributeNames();
//        Object agent = res1.getAttribute("useragent");
        return ip;
    }

//===========================node===============================================
    public List getChildNode() {
        List al=new ArrayList();
        al.add("hello");
        String current = "/content/mysite/us/en";
        log.info("current URI"+current);
         Resource path = resourceResolver.getResource(current);
         Iterable<Resource> child = path.getChildren();
         log.info("path URI"+path);
        for(Resource s:child){
            al.add(s);
        }
        return al;
    }

//    =============================== pagetitle================================================================
    //
//    @Override
//    public String getPageTitle() {
//
//        log.info("Present :" + Objects.nonNull(resourceResolver));
//        if(Objects.nonNull(resourceResolver)){
//            PageManager  pageManager = resourceResolver.adaptTo(PageManager.class);
//            log.info("pageManager :"+Objects.nonNull(pageManager));
//            if(pageManager!=null){
////                Page page = pageManager.getContainingPage(resource);
//                Page page = pageManager.getContainingPage(resource);
//                log.info("pageTitle:"+Objects.nonNull(page));
//                if(page!=null){
//                    return page.getTitle();
//
//                }
//            }
//        }
//       return "";
//    }
//    ========================================================================================
}
