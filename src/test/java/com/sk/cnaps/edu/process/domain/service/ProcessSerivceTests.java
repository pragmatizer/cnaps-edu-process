package com.sk.cnaps.edu.process.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessSerivceTests {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PhaseRepository phaseRepository;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ProcessService processService;

	private Long phaseId;
	private Long activityId;
	/**
	 * 단위 테스트 안의 모든 메소드 실행 전에 한번만 수행
	 *
	 * JUnit5의 경우 @BeforeAll
	 * JUnit4의 경우 @BeforeClass
	 */
	@BeforeClass
	public static void initAll() {
		//
	}

	/**
	 * 단위 테스트 안의 각 메소드가 실행될 때마다 실행 전에 수행
	 *
	 * JUnit5의 경우 @BeforeEach
	 * Junit4의 경우 @Before
	 *
	 * @throws Exception 에러처리
	 */
	@Before
	public void init() throws Exception {
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
	public void testFindPhaseWithActivitiesById() {
		Phase phase = processService.findPhaseWithActivitiesById(phaseId);
		phase.getActivitiesAggregate().fillValues(activityRepository);
		logger.info(UtilsSupport.toPrettyFormat(phase.toString()));
		
		assertThat(phase.getActivitiesAggregate().getValues()).isNotEmpty().hasSize(1);
		
		phaseRepository.deleteAll();
		activityRepository.deleteAll();
		taskRepository.deleteAll();
	}
	
	@Test
	public void testFindActivityWithTasksById() {
		Activity activity = processService.findActivityWithTasksById(activityId);
		activity.getTasksAggregate().fillValues(taskRepository);
		logger.info(UtilsSupport.toPrettyFormat(activity.toString()));
		
		assertThat(activity.getTasksAggregate().getValues()).isNotEmpty().hasSize(1);
		
		phaseRepository.deleteAll();
		activityRepository.deleteAll();
		taskRepository.deleteAll();
	}

	/**
	 * 단위 테스트 안의 각 메소드가 실행될 때마다 실행 후에 수행
	 *
	 * JUnit5의 경우 @AfterEach
	 * Junit4의 경우 @After
	 *
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {

	}

	/**
	 * 단위 테스트 안의 모든 메소드 실행이 된 후 마지막에 한번만 수행
	 *
	 * JUnit5의 경우 @AfterAll
	 * Junit4의 경우 @AfterClass
	 */
	@AfterClass
	public static void tearDownAll() {
		//
	}
}