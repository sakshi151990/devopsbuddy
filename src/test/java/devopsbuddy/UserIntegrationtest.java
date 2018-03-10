package devopsbuddy;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.persistence.Plan;
import com.devopsbuddy.persistence.PlanRepository;
import com.devopsbuddy.persistence.Role;
import com.devopsbuddy.persistence.RoleRepository;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.persistence.UserRepository;
import com.devopsbuddy.persistence.UserRole;
import com.devopsbuddy.service.I18NService;
import com.devopsbuddy.utils.UserUtil;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
@ComponentScan(basePackages = "com.devopsbuddy.service,com.devopsbuddy.config,com.devopsbuddy.persistence")

public class UserIntegrationtest {
	@Rule
	public TestName testname = new TestName();

	@Autowired
	I18NService i18service;

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Before
	public void init() {
		Assert.assertNotNull(planRepository);
		Assert.assertNotNull(userRepository);
		Assert.assertNotNull(roleRepository);
	}

	@Test
	public void testcreationrepo() {
		String username = testname.getMethodName();
		String email = testname.getMethodName() + "@devopsbuddy.com";
		planRepository.save(createBasicPlan(PlansEnum.BASIC));
		Plan retrivePlan = planRepository.findOne(PlansEnum.BASIC.getId());
		Assert.assertNotNull(retrivePlan);

		roleRepository.save(createBaiscRole(RoleEnum.BASIC));
		Role retriveRole = roleRepository.findOne(RoleEnum.BASIC.getId());
		Assert.assertNotNull("not null", retriveRole);

		User user = createNewUser(username, email);
		userRepository.save(user);
		User getuser = userRepository.findOne(user.getId());
		Assert.assertNotNull(getuser.getDescription());

	}

	private Role createBaiscRole(RoleEnum role) {
		Role basicRole = new Role(role);

		return basicRole;

	}

	private Plan createBasicPlan(PlansEnum basic) {

		Plan basicPlan = new Plan(PlansEnum.BASIC);

		return basicPlan;

	}

	private User createNewUser(String username, String email) {

		User user = UserUtil.createBasicUser(username, email);
		Plan userPlan = createBasicPlan(PlansEnum.BASIC);
		planRepository.save(userPlan);

		user.setPlan(userPlan);

		Role userrole = createBaiscRole(RoleEnum.BASIC);
		Set<UserRole> userroles = new HashSet<>();
		UserRole userr = new UserRole(user, userrole);
		userr.setRole(userrole);
		userr.setUser(user);
		userroles.add(userr);
		user.getUserroles().addAll(userroles);

		for (UserRole us : userroles) {
			roleRepository.save(us.getRole());

		}

		return user;

	}

	@Test
	public void testDeleteUser() {

		String username = testname.getMethodName();
		String email = testname.getMethodName() + "@devopsbuddy.com";

		User basicuser = createNewUser(username, email);
		userRepository.delete(basicuser.getId());

	}

	@Test
	public void testgetUser() {

		String username = testname.getMethodName();
		String email = testname.getMethodName() + "@devopsbuddy.com";

		User basicuser = createNewUser(username, email);
		User user = userRepository.findByEmail(basicuser.getEmail());
		Assert.assertNotNull(user);

	}

	@Test
	public void userupdatepassword() {
		User user = createNewUser(testname.getMethodName(), testname.getMethodName() + "@devopsbuddy.com");
		Assert.assertNotNull(user);
		String newpassword = UUID.randomUUID().toString();
		userRepository.updateUserPassword(user.getId(), newpassword);
		user = userRepository.findOne(user.getId());
		Assert.assertEquals(newpassword, user.getPassword());
		;
	}

}
