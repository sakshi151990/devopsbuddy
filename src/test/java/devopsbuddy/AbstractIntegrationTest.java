package devopsbuddy;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.persistence.Plan;
import com.devopsbuddy.persistence.PlanRepository;
import com.devopsbuddy.persistence.Role;
import com.devopsbuddy.persistence.RoleRepository;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.persistence.UserRepository;
import com.devopsbuddy.persistence.UserRole;
import com.devopsbuddy.utils.UserUtil;

public abstract class AbstractIntegrationTest {

	@Autowired
	protected PlanRepository planRepository;
	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected RoleRepository roleRepository;

	protected Plan createPlan(PlansEnum plansEnum) {
		return new Plan(plansEnum);
	}

	protected Role createRole(RoleEnum rolesEnum) {
		return new Role(rolesEnum);
	}

	protected User createUser(String username, String email) {

		Plan basicPlan = createPlan(PlansEnum.BASIC);
		planRepository.save(basicPlan);

		User basicUser = UserUtil.createBasicUser(username, email);
		basicUser.setPlan(basicPlan);

		Role basicRole = createRole(RoleEnum.BASIC);
		roleRepository.save(basicRole);

		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole(basicUser, basicRole);
		userRoles.add(userRole);

		basicUser.getUserroles().addAll(userRoles);
		basicUser = userRepository.save(basicUser);
		return basicUser;
	}

	protected User createUser(TestName testName) {
		return createUser(testName.getMethodName(), testName.getMethodName() + "@devopsbuddy.com");
	}
}
