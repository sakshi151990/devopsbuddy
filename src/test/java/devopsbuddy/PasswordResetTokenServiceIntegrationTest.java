package devopsbuddy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devopsbuddy.persistence.PasswordResetToken;
import com.devopsbuddy.persistence.User;
import com.devopsbuddy.service.PasswordResetTokenService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
@ComponentScan(basePackages = "com.devopsbuddy.service,com.devopsbuddy.config,com.devopsbuddy.persistence")
public class PasswordResetTokenServiceIntegrationTest extends AbstractServiceIntegrationTest {

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@Rule
	public TestName testName = new TestName();

	@Test
	public void testCreateNewTokenForUserEmail() throws Exception {

		User user = createUser(testName);

		PasswordResetToken passwordResetToken = passwordResetTokenService
				.createPasswordResetTokenForEmail(user.getEmail());
		Assert.assertNotNull(passwordResetToken);
		Assert.assertNotNull(passwordResetToken.getToken());

	}

	@Test
	public void testFindByToken() throws Exception {
		User user = createUser(testName);

		PasswordResetToken passwordResetToken = passwordResetTokenService
				.createPasswordResetTokenForEmail(user.getEmail());
		Assert.assertNotNull(passwordResetToken);
		Assert.assertNotNull(passwordResetToken.getToken());

		PasswordResetToken token = passwordResetTokenService.findByToken(passwordResetToken.getToken());
		Assert.assertNotNull(token);

	}

}
