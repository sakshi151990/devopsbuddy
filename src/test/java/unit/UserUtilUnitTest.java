package unit;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.devopsbuddy.utils.UserUtil;

import junit.framework.Assert;

public class UserUtilUnitTest {

	private MockHttpServletRequest mockHttpServletRequest;

	@Before
	public void init() {
		mockHttpServletRequest = new MockHttpServletRequest();
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
}
