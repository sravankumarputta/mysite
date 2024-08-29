//package com.mysite.core.models;
//
//import com.day.cq.workflow.WorkflowException;
//import com.day.cq.workflow.WorkflowSession;
//import com.day.cq.workflow.exec.WorkItem;
//import com.day.cq.workflow.exec.WorkflowData;
//import com.day.cq.workflow.exec.WorkflowProcess;
//import com.day.cq.workflow.metadata.MetaDataMap;
//import org.osgi.framework.Constants;
//import org.osgi.service.component.annotations.Component;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Component(service = WorkflowProcess.class,immediate = true,property = {
//        "process.label"+"=WorkFlowTest",
//        Constants.SERVICE_VENDOR+"=TEST1",
//        Constants.SERVICE_DESCRIPTION+"=test1 WorkFlowtest"
//})
//public class WorkFlowTest implements WorkflowProcess {
//    public static final Logger log= LoggerFactory.getLogger(WorkFlowTest.class);
//    @Override
//    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
//        WorkflowData workFlowData = workItem.getWorkflowData();
//        log.info("workflowdata "+workFlowData);
////        if (workFlowData.getPayloadType().equals("JCR_PATH")){
////            Session session = workflowSession.getSession();
////        }
//    }
//}

//===============================================================================================
package com.mysite.core.models;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.exec.WorkflowProcess;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;

@Component(service = WorkflowProcess.class, property = {
        Constants.SERVICE_DESCRIPTION + "=Custom Workflow Process",
        Constants.SERVICE_VENDOR + "=MySite",
        "process.label=My Custom Workflow"
})
public class WorkFlowTest implements WorkflowProcess {
    private static final Logger log = LoggerFactory.getLogger(WorkFlowTest.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        log.info("Custom Workflow Process started for item: "+ workItem.getId());

        WorkflowData workflowData = workItem.getWorkflowData();
        String processArgs = metaDataMap.get("PROCESS_ARGS", "").split(":").toString();
        log.info("processArgs "+processArgs);
        if ("JCR_PATH".equals(workflowData.getPayloadType())) {
            String path = workflowData.getPayload().toString()+"/jcr:content";
            log.info("Processing path: "+ path);
            Session session = workflowSession.getSession();
            log.info("sessionSSR "+session);
            try {
                if (session.nodeExists(path)) {
//                    session.getNode(path).setProperty("customWorkflowProperty", "processed");
                    session.save();
                }
            } catch (Exception e) {
                log.error("Error processing workflow item", e);
                throw new WorkflowException(e.getMessage(), e);
            }
        } else {
            log.warn("Unsupported payload type: {}", workflowData.getPayloadType());
        }

        log.info("Custom Workflow Process completed for item: {}", workItem.getId());
    }
}
