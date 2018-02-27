package devopsbuddy;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.persistence.Role;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.persistence.UserRole;
import com.devopsbuddy.service.UserService;
import com.devopsbuddy.utils.UserUtil;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
@ComponentScan(basePackages = "com.devopsbuddy.service,com.devopsbuddy.config,com.devopsbuddy.persistence")
public class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;

	@Test
	public void testCreateUser() {

		Set<UserRole> userroles = new HashSet<>();
		User basicuser = UserUtil.createBasicUser();
		userroles.add(new UserRole(basicuser, new Role(RoleEnum.BASIC)));
		User user = userService.CreateUser(basicuser, PlansEnum.BASIC, userroles);
		Assert.assertNotNull(user);
	}

}
