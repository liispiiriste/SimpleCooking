package ee.rakendus.tests;

import ee.rakendus.example.Recipe;
import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserController;
import ee.rakendus.example.user.UserService;
import org.apache.catalina.filters.CorsFilter;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
//@AutoConfigureWebMvc
//@SpringBootTest(classes = Application.class)
public class UserRepositoryTest {
    public static final long TEST_USER_ID1 = 1L;
    public static final long TEST_USER_ID2 = 2L;
    public static final long TEST_USER_ID3 = 3L;

    public static final String TEST_USER_EMAIL = "test1@test.ee";

    public static final long RECIPE_ID1 = 1L;
    public static final long RECIPE_ID2 = 2L;

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user1;
    private User user2;
    private User user3;

    private List<User> sampleUsers;

    private Recipe sampleRecipe1;
    private Recipe sampleRecipe2;

    private List<Recipe> sampleRecipes;

    @Before
    public void setup() throws Exception {

        sampleRecipe1.setId(RECIPE_ID1);
        sampleRecipe1.setPrice(2);
        sampleRecipe1.setMaterials("tere");
        sampleRecipe1.setDescription("tere");
        sampleRecipe1.setName("tere");
        sampleRecipe1.setPortion(3);

        sampleRecipe2.setId(RECIPE_ID2);
        sampleRecipe2.setPrice(2);
        sampleRecipe2.setMaterials("tere");
        sampleRecipe2.setDescription("tere");
        sampleRecipe2.setName("tere");
        sampleRecipe2.setPortion(3);


        sampleRecipes = new ArrayList<>();
        sampleRecipes.add(sampleRecipe1);
        sampleRecipes.add(sampleRecipe2);

        user1 = new User();
        user1.setId(TEST_USER_ID1);
        user1.setEmail(TEST_USER_EMAIL);
        user1.setPassword("123");
        user1.setUsername("test1");


        user2 = new User();
        user2.setId(TEST_USER_ID2);
        user2.setEmail("test2@test.ee");
        user2.setPassword("456");
        user2.setUsername("test2");

        user3 = new User();
        user3.setId(TEST_USER_ID3);
        user3.setEmail("test3@test.ee");
        user3.setPassword("789");
        user3.setUsername("test3");

        sampleUsers = new ArrayList<>();
        sampleUsers.add(user1);
        sampleUsers.add(user2);
        sampleUsers.add(user3);

        given(userService.getAllUsers()).willReturn(sampleUsers);

        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CorsFilter())
                .build();
    }
    @Test
    public void testAddUser() throws Exception {

    }
    @Test
    public void testGetAllUsers() throws Exception {
        Mockito.when(userService.getAllUsers()).thenReturn(sampleUsers);
        mockMvc.perform(get("api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect((ResultMatcher) jsonPath("$", hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", Matchers.is(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].email", Matchers.is("test1@test.ee")))
                .andExpect((ResultMatcher) jsonPath("$[0].password", Matchers.is("123")))
                //.andExpect(jsonPath("$[0].complete", Matchers.is(true)))
                //.andExpect(jsonPath("$[0].user", Matchers.is(1)))

        ;
        Mockito.verify(userService, Mockito.times(1)).getAllUsers();
        verifyNoMoreInteractions(userService);
        Assert.assertEquals(userService.getAllUsers(), sampleUsers);
    }

    @Test
    public void testGetUserById() throws Exception {

    }

    @Test
    public void testGetUserByEmail() throws Exception {

    }
}
