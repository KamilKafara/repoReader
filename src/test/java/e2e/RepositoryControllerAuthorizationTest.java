package e2e;

import com.kafarson.Main;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        properties = {"github.authorization.token=github_fakeToken145124354wetaswrqwrwr"},
        classes = Main.class)
@AutoConfigureMockMvc
public class RepositoryControllerAuthorizationTest {

    @Autowired
    private MockMvc mvc;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testShouldReturnUnauthorized() throws Exception {
        //given
        String endpoint = "/KamilKafara/test_repo";
        String exceptionMessage = "Access denied. You are not authorized to fetch this data. Please log in with appropriate credentials or contact the administrator.";
        //when
        exceptionRule.expectMessage(exceptionMessage);
        //then
        mvc.perform(get(endpoint))
                .andExpect(status().isUnauthorized())
                .andExpect(status().reason(exceptionMessage))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
