package com.sk.cnaps.edu.process;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sk.cnaps.domain.util.UtilsSupport;
import com.sk.cnaps.edu.process.domain.model.Activity;
import com.sk.cnaps.edu.process.domain.model.Phase;
import com.sk.cnaps.edu.process.domain.model.Resource;
import com.sk.cnaps.edu.process.domain.model.ResourceType;
import com.sk.cnaps.edu.process.domain.model.RoleType;
import com.sk.cnaps.edu.process.domain.model.Step;
import com.sk.cnaps.edu.process.domain.model.Task;
import com.sk.cnaps.edu.process.domain.repository.ActivityRepository;
import com.sk.cnaps.edu.process.domain.repository.PhaseRepository;
import com.sk.cnaps.edu.process.domain.repository.TaskRepository;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

@Import(SpringDataRestConfiguration.class)
@SpringBootApplication
public class ProcessApplication {
	private static final Logger log = LoggerFactory.getLogger(ProcessApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProcessApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner demo(TaskRepository taskRepository, ActivityRepository activityRepository, PhaseRepository phaseRepository) {
		return (args) -> {
			Task task = createTask(taskRepository);
			Activity activity = createActivity(activityRepository);
			Phase phase = createPhase(phaseRepository);
			
			activity.getTasksAggregate().add(task.getId());
			log.info(UtilsSupport.toPrettyFormat(activity.toString()));
			activityRepository.save(activity);
			
			phase.getActivitiesAggregate().add(activity.getId());
			log.info(UtilsSupport.toPrettyFormat(phase.toString()));
			phaseRepository.save(phase);

			log.info("----------------------------- Task ----------------------------");
			activity.getTasksAggregate().fillValues(taskRepository);			
			log.info(UtilsSupport.toPrettyFormat(activity.toString()));

			log.info("----------------------------- Phase ----------------------------");
			phase.getActivitiesAggregate().fillValues(activityRepository);			
			log.info(UtilsSupport.toPrettyFormat(phase.toString()));
		};
	}
		
	public Task createTask(TaskRepository taskRepository) {
		Resource input = Resource.builder()
		        				 .resourceType(ResourceType.INPUT)
		        				 .title("요구사항")
		        				 .url("https://inputs")
		        				 .build();
		
		Resource output = Resource.builder()
				        		  .resourceType(ResourceType.INPUT)
				        		  .title("설계서")
				        		  .url("https://outputs")
				        		  .build();

		Task task = Task.builder()
				        .number("3110")
				        .title("핵심개념식별(Key Conept)")
				        .description("핵심개념을 식별한다.")
				        .roleType(RoleType.PRODUCT_OWNER)
				        .input(input)
				        .output(output)
				        .build();

		task.getSteps().add(Step.builder().orderNumber(1).title("유비쿼터스 언어 정의").build());
		task.getSteps().add(Step.builder().orderNumber(2).title("핵심 개념 클래스 및 속성 식별").build());
		task.getSteps().add(Step.builder().orderNumber(3).title("객체 간 관계 정의").build());

		taskRepository.save(task);

		log.info(UtilsSupport.toPrettyFormat(task.toString()));
		
		return task;
	}
	
	public Activity createActivity(ActivityRepository activityRepository) {
		List<Resource> guidelines = new ArrayList<>();
		guidelines.add(Resource.builder()
				 			   .resourceType(ResourceType.MANUAL)
				               .title("전략적설계가이드")
				               .url("https://guide1")
				               .build());
		
		guidelines.add(Resource.builder()
							   .resourceType(ResourceType.MANUAL)
							   .title("유비쿼터스 언어정의 가이드")
							   .url("https://guide2")
							   .build());
		
		guidelines.add(Resource.builder()
	 			   			   .resourceType(ResourceType.MANUAL)
	 			   			   .title("이벤트스토밍가이드")
	 			   			   .url("https://guide3")
	 			   			   .build());
				
		List<Resource> templateOrSamples = new ArrayList<>();
		templateOrSamples.add(Resource.builder()
									  .resourceType(ResourceType.TEMPLATE_OR_SAMPLE)
									  .title("설계서")
									  .url("https://design")
									  .build());
		
		Activity activity = Activity.builder()
				                    .number("3100")
				                    .title("전략적 설계")
				                    .description("전략적 설계는 ....")
				                    .guideLines(guidelines)
				                    .templateOrSamples(templateOrSamples)
				                    .build();
		
		activityRepository.save(activity);

		log.info(UtilsSupport.toPrettyFormat(activity.toString()));
		
		return activity;
	}

	public Phase createPhase(PhaseRepository phaseRepository) {
		Phase phase = Phase.builder()
						   .number("3000")
						   .title("설계")
						   .description("SW 설계란 ....")
						   .consideration("고려사항 필수요소입니다.")
						   .build();
		
		phaseRepository.save(phase);

		log.info(UtilsSupport.toPrettyFormat(phase.toString()));
		
		return phase;
	}
	*/
}
