package devopsbuddy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devopsbuddy.persistence.PlanRepository;
import com.devopsbuddy.service.I18NService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
@ComponentScan(basePackages = "com.devopsbuddy.service,com.devopsbuddy.config")
public class DevopsBuddyApplicationtest {

	@Autowired
	I18NService i18service;

	@Autowired
	private PlanRepository planRepository;

	@Test
	public void getmessagetest() {
		String expected = "Basic Membership";
		String actual = i18service.getMessage("index.basic.membership");
		assertEquals(expected, actual);

	}

}
