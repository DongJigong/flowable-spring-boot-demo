package com.changqin.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.InputStream;

@Controller
@Slf4j
public class DrawPicController {


    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;

    @GetMapping("/test")
    public ResponseEntity test(){

        ProcessDefinition definition = repositoryService.getProcessDefinition("");
        BpmnModel bpmnModel = repositoryService.getBpmnModel(definition.getId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator
                .generateDiagram(bpmnModel, "png", null, null, false);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test/all")
    public ResponseEntity testAll(){


//        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
////        BpmnModel bpmnModel = repositoryService.getBpmnModel(definition.getId());
////        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
//        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
//        InputStream in = diagramGenerator
//                .generateDiagram(bpmnModel, "png", null, null, false);
        return ResponseEntity.ok().build();
    }
}
