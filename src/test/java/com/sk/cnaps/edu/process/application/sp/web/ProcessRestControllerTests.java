package com.sk.cnaps.edu.process.application.sp.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sk.cnaps.domain.util.JsonUtil;
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
import com.sk.cnaps.edu.process.domain.service.ProcessService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProcessRestControllerTests {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ProcessRestController controller;
	
	@Autowired
	private ProcessService service;
	
	@Autowired
	private PhaseRepository phaseRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private TaskRepository taskRepository;
	
	private Long phaseId;
	private Long activityId;


	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		phaseRepository.deleteAll();
		activityRepository.deleteAll();
		taskRepository.deleteAll();
		
		Task task = createTask(taskRepository);
		Activity activity = createActivity(activityRepository);
		Phase phase = createPhase(phaseRepository);
		phaseId = phase.getId();
		activityId = activity.getId();
		
		activity.getTasksAggregate().add(task.getId());
		logger.info(UtilsSupport.toPrettyFormat(activity.toString()));
		activityRepository.save(activity);
		
		phase.getActivitiesAggregate().add(activity.getId());
		logger.info(UtilsSupport.toPrettyFormat(phase.toString()));
		phaseRepository.save(phase);
		
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

		logger.info(UtilsSupport.toPrettyFormat(task.toString()));
		
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

		logger.info(UtilsSupport.toPrettyFormat(activity.toString()));
		
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

		logger.info(UtilsSupport.toPrettyFormat(phase.toString()));
		
		return phase;
	}
	

	
	@Test
	public void testFindPhaseWithActivitiesById() throws Exception {
		Phase phase = service.findPhaseWithActivitiesById(phaseId);
		
		mockMvc.perform(get("/v1/cnaps/process/phases+activities/" + phaseId))
		       .andExpect(status().isOk())
		       .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		       .andExpect(content().string(equalTo(JsonUtil.toJsonStr(phase))));
		
		phaseRepository.deleteAll();
		activityRepository.deleteAll();
		taskRepository.deleteAll();
	}

	/*
	@Test
	public void testFindActivityWithTasksById() throws Exception {	
		//Activity activity = service.findActivityWithTasksById(activityId);
	
		mockMvc.perform(get("/v1/cnaps/process/activities+tasks/" + 100))
	           .andExpect(status().isN);
		
		phaseRepository.deleteAll();
		activityRepository.deleteAll();
		taskRepository.deleteAll();
	}
	 */
}
