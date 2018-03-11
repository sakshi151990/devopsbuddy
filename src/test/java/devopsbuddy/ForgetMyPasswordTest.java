package devopsbuddy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.devopsbuddy.web.ForgotMyPasswordContorller;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootApplication
@WebAppConfiguration
@ComponentScan(basePackages = "com.devopsbuddy.service,com.devopsbuddy.config,com.devopsbuddy.persistence")
public class ForgetMyPasswordTest {

	@Test
	public void urltest() throws Exception {

		ForgotMyPasswordContorller controller = new ForgotMyPasswordContorller();
		new MockMvcBuilders();
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		mock.perform(get("/forgotmypassword")).andExpect(view().name("forgotmypassword/emailform"));
	}

}
