package devopsbuddy;

import java.util.HashSet;
import java.util.Set;

import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.persistence.Role;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.persistence.UserRole;
import com.devopsbuddy.service.UserService;
import com.devopsbuddy.utils.UserUtil;

public abstract class AbstractServiceIntegrationTest {
	@Autowired
	protected UserService userService;

	protected User createUser(TestName testName) {
		String username = testName.getMethodName();
		String email = testName.getMethodName() + "@devopsbuddy.com";

		Set<UserRole> userRoles = new HashSet<>();
		User basicUser = UserUtil.createBasicUser(username, email);
		userRoles.add(new UserRole(basicUser, new Role(RoleEnum.BASIC)));

		return userService.CreateUser(basicUser, PlansEnum.BASIC, userRoles);
	}
}
