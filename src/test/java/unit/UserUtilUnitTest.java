package unit;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.devopsbuddy.persistence.User;
import com.devopsbuddy.utils.UserUtil;
import com.devopsbuddy.web.basicAccountPayload;

import junit.framework.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class UserUtilUnitTest {

	private MockHttpServletRequest mockHttpServletRequest;
	private PodamFactory podamfactory;

	@Before
	public void init() {
		mockHttpServletRequest = new MockHttpServletRequest();
		podamfactory = new PodamFactoryImpl();
	}

	@Test
	public void test() {
		mockHttpServletRequest.setServerPort(8080);
		String token = UUID.randomUUID().toString();
		long userId = 12345;
		String expectedUrl = "http://localhost:8080" + "/changeuserpassword" + "?id=" + userId + "&token=" + token;
		String actulaurl = UserUtil.createPasswordResetURL(mockHttpServletRequest, userId, token);
		Assert.assertEquals(expectedUrl, actulaurl);

	}

	@Test
	public void mapWebUserToDomainUser() {

		basicAccountPayload webUser = podamfactory.manufacturePojo(basicAccountPayload.class);
		webUser.setEmail("me@example.com");

		User user = UserUtil.fromWebUserToDomainUser(webUser);
		Assert.assertNotNull(user);

		Assert.assertEquals(webUser.getUsername(), user.getUsername());
		Assert.assertEquals(webUser.getPassword(), user.getPassword());
		Assert.assertEquals(webUser.getFirstName(), user.getFirstName());
		Assert.assertEquals(webUser.getLastName(), user.getLastName());
		Assert.assertEquals(webUser.getEmail(), user.getEmail());
		Assert.assertEquals(webUser.getPhoneNumber(), user.getPhone());
		Assert.assertEquals(webUser.getCountry(), user.getCountry());
		Assert.assertEquals(webUser.getDescription(), user.getDescription());

	}

}
