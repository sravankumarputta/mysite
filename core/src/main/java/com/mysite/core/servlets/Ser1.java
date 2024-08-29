package com.mysite.core.servlets;
import com.day.cq.commons.jcr.JcrConstants;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
//        resourceTypes = "/apps/mysite/components/Gemini",
        resourceTypes = "mysite/components/page",
        selectors = "one",
        extensions ="html",
        methods = HttpConstants.METHOD_GET
)
public class Ser1 extends SlingAllMethodsServlet {
    public static Logger log= LoggerFactory.getLogger(Ser1.class);



        @Override
    protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
         Resource resource = request.getResource();
         log.info("resource1 "+resource);
//         response.setContentType("text/plain");
         response.setContentType("text/plain");
        log.info("myinfo ");
        response.getWriter().write("pagetitle= " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
        log.info("Page Title = "+resource.getValueMap().get(JcrConstants.JCR_TITLE));
//        response.getWriter().write("pagetitle= ");

        log.info("myinfo ");

    }
}
//=============================================================================================

