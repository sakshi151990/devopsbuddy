package devopsbuddy;

import java.util.HashSet;
import java.util.Set;

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
import com.devopsbuddy.service.UserService;
import com.devopsbuddy.utils.UserUtil;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
@ComponentScan(basePackages = "com.devopsbuddy.service,com.devopsbuddy.config,com.devopsbuddy.persistence")
public class UserServiceIntegrationTest {

	@Rule
	public TestName testname = new TestName();

	@Autowired
	private UserService userService;

	@Autowired
	private PlanRepository planRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void testCreateUser() {

		User user = createUser();
		Assert.assertNotNull(user);
	}

	private User createUser() {

		String username = testname.getMethodName();
		String email = testname.getMethodName() + "@devopsbuddy.com";

		Plan basicPlan = createPlan(PlansEnum.BASIC);
		planRepository.save(basicPlan);

		User basicuser = UserUtil.createBasicUser(username, email);
		basicuser.setPlan(basicPlan);
		Role basicrole = createRole(RoleEnum.BASIC);
		roleRepository.save(basicrole);

		Set<UserRole> userrole = new HashSet<>();
		UserRole userole = new UserRole(basicuser, basicrole);
		userrole.add(userole);
		basicuser.setUserroles(userrole);
		basicuser = userRepository.save(basicuser);
		return basicuser;

	}

	@Test
	public void testDelete() {
		User basicuser = createUser();
		userRepository.delete(basicuser.getId());
	}

	private Role createRole(RoleEnum basic) {
		// TODO Auto-generated method stub
		return new Role(basic);
	}

	private Plan createPlan(PlansEnum basic) {
		// TODO Auto-generated method stub
		return new Plan(basic);
	}

}
