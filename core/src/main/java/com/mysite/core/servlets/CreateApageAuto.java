package com.mysite.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
//import com.day.cq.wcm.api.WCMException;
import com.day.cq.wcm.api.WCMException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/createPage")
public class CreateApageAuto extends SlingSafeMethodsServlet {
    public static final Logger log= LoggerFactory.getLogger(CreateApageAuto.class);
//    String NewpageName="newPageSRK";
    String parentPath="/content/mysite/us/en";
    String templatePath="/conf/mysite/settings/wcm/templates/temp2";
//    String jcrTitle="newSRK";

//    protected void doPost(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException, IOException {
//         ResourceResolver resource = request.getResourceResolver();
//         PageManager pageManager = resource.adaptTo(PageManager.class);
//        try {
//            Page newPage = pageManager.create(parentPath, templatePath, NewpageName, jcrTitle);
//            response.getWriter().write("newpagecreated "+newPage);
//        } catch (WCMException e) {
//            response.getWriter().write("pages is not created "+e.getStackTrace());
//        }
//    }

    @Override
    protected void doGet(SlingHttpServletRequest request,  SlingHttpServletResponse response) throws ServletException, IOException {
        String newName = request.getParameter("name");
        String title = request.getParameter("title");
        log.info("namer "+newName);
        log.info("titler "+title);
        ResourceResolver resorceReslover = request.getResourceResolver();
         PageManager pageManager = resorceReslover.adaptTo(PageManager.class);
         log.info("pagerManager "+pageManager);
        try {
            log.info("hi sravan");
            assert pageManager != null;
//            Page newPage = pageManager.create(parentPath, templatePath, newName, title);
            Page newPage = pageManager.create(parentPath, newName, templatePath, title);

            log.info("newpage1 "+newPage.getTitle());
            response.getWriter().write("newPage created"+ newPage.getTitle());
        } catch (WCMException e) {
            log.info("error in my "+e.getStackTrace());
        }
    }
}
