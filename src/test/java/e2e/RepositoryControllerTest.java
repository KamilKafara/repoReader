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
        classes = Main.class)
@AutoConfigureMockMvc
public class RepositoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void givenNonExistentRepository_whenGetRepository_thenStatus404() throws Exception {
        String exceptionMessage = "The resource you are looking for could not be found. Please check the URL or try a different search term.";
        exceptionRule.expectMessage(exceptionMessage);
        mvc.perform(get("/testName/testRepo"))
                .andExpect(status().isNotFound())
                .andExpect(status().reason(exceptionMessage))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testShouldReturnSuccess() throws Exception {
        String endpoint = "/KamilKafara/test_repo";
        String expectedJson = """
                {
                    "fullName": "KamilKafara/test_repo",
                    "description": "Test repository for integration test",
                    "cloneUrl": "https://github.com/KamilKafara/test_repo.git",
                    "stars": 0,
                    "createdAt": "2023-03-24 23:06",
                    "private": false
                }""";
        mvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void testShouldReturnSuccessWithDifferentDateTimePattern() throws Exception {
        String endpoint = "/KamilKafara/test_repo?dataTimePattern=YYYY-MM";
        String expectedJson = """
                {
                    "fullName": "KamilKafara/test_repo",
                    "description": "Test repository for integration test",
                    "cloneUrl": "https://github.com/KamilKafara/test_repo.git",
                    "stars": 0,
                    "createdAt": "2023-03",
                    "private": false
                }""";
        mvc.perform(get(endpoint))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
}
