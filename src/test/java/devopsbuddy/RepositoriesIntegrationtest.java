package devopsbuddy;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devopsbuddy.persistence.Plan;
import com.devopsbuddy.persistence.PlanRepository;
import com.devopsbuddy.persistence.Role;
import com.devopsbuddy.persistence.RoleRepository;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.persistence.UserRepository;
import com.devopsbuddy.persistence.UserRole;
import com.devopsbuddy.service.I18NService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
@ComponentScan(basePackages = "com.devopsbuddy.service,com.devopsbuddy.config,com.devopsbuddy.persistence")
// @ComponentScan(basePackages = { "com.devopsbuddy.service",
// "com.devopsbuddy.config", "com.devopsbuddy.persistence" })
public class RepositoriesIntegrationtest {

	@Autowired
	I18NService i18service;

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	private static final int BASIC_PLAN_ID = 1;
	private static final int BAISC_ROLE_ID = 1;

	@Before
	public void init() {
		Assert.assertNotNull(planRepository);
		Assert.assertNotNull(userRepository);
		Assert.assertNotNull(roleRepository);
	}

	@Test
	public void testcreationrepo() {

		planRepository.save(createBasicPlan());
		Plan retrivePlan = planRepository.findOne(BASIC_PLAN_ID);
		Assert.assertNotNull(retrivePlan);

		roleRepository.save(createBaiscRole());
		Role retriveRole = roleRepository.findOne(BASIC_PLAN_ID);
		Assert.assertNotNull("not null", retriveRole);

		User user = createNewUser();
		userRepository.save(user);
		User getuser = userRepository.findOne(user.getId());
		Assert.assertNotNull(getuser.getDescription());

	}

	private Role createBaiscRole() {
		Role basicRole = new Role();
		basicRole.setId(BAISC_ROLE_ID);
		basicRole.setName("BASIC");
		return basicRole;

	}

	private Plan createBasicPlan() {

		Plan basicPlan = new Plan();
		basicPlan.setId(BASIC_PLAN_ID);
		basicPlan.setName("BAISC");
		return basicPlan;

	}

	private User createNewUser() {
		Plan userPlan = createBasicPlan();
		planRepository.save(userPlan);

		User user = new User();
		user.setPlan(userPlan);

		Role userrole = createBaiscRole();
		Set<UserRole> userroles = new HashSet<>();
		UserRole userr = new UserRole();
		userr.setRole(userrole);
		userr.setUser(user);
		userroles.add(userr);
		user.getUserroles().addAll(userroles);

		for (UserRole us : userroles) {
			roleRepository.save(us.getRole());

		}

		user.setCountry("India");
		user.setDescription("basicplan and role");
		user.setEmail("sakshi@gmail.com");
		user.setFirstName("sakshi");
		user.setLastName("ag");
		user.setPassword("password");
		user.setPhone("6789098");
		user.setProfile_img("http:bla.com");
		user.setStripecustomerid("234");
		user.setEnabled(true);
		return user;

	}

}
