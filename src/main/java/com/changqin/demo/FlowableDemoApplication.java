package com.changqin.demo;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.image.ProcessDiagramGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.Collections;

@SpringBootApplication
public class FlowableDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableDemoApplication.class, args);
    }

    /**
     * 绘制图片
     * @param repositoryService
     * @param runtimeService
     * @param taskService
     * @param processEngine
     * @return
     */
    @Bean
    public CommandLineRunner init(final RepositoryService repositoryService,
                                  final RuntimeService runtimeService,
                                  final TaskService taskService,
                                  final ProcessEngine processEngine) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                System.out.println("Number of process definitions : "
                        + repositoryService.createProcessDefinitionQuery().count());
                System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
                runtimeService.startProcessInstanceByKey("holidayRequest");
                System.out.println("Number of tasks after process start: "
                        + taskService.createTaskQuery().count());
//                ProcessDefinition definition = repositoryService.getProcessDefinition("");
                ProcessDefinition definition = repositoryService.getProcessDefinition("holidayRequest:1:1f9acbc7-f18a-11e9-bd49-2a0f998b0bc9");
                BpmnModel bpmnModel = repositoryService.getBpmnModel(definition.getId());
                ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
                ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
                InputStream in = diagramGenerator
                        .generateDiagram(bpmnModel, "png", Collections.emptyList(), Collections.emptyList(), false);
                System.out.println("11111111");
            }
        };
    }

}
