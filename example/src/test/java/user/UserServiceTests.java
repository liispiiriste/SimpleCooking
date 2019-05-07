package user;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.rakendus.example.entity.Recipe;
import ee.rakendus.example.entity.User;
import ee.rakendus.example.controller.UserController;
import ee.rakendus.example.service.UserService;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = UserService.class)
public class UserServiceTests {

    public static final long TEST_USER_ID1 = 1L;
    public static final long TEST_USER_ID2 = 2L;
    public static final long TEST_USER_ID3 = 3L;

    public static final String TEST_USER_EMAIL = "test1@test.ee";
    public static final String TEST_USER_USERNAME = "test";
    public static final String TEST_USER_PASSWORD = "test";

    public static final long RECIPE_ID1 = 1L;
    public static final long RECIPE_ID2 = 2L;

    @MockBean
    UserService userService;

    @InjectMocks
    private UserController userController;

    ObjectMapper mapper = new ObjectMapper();

    @Resource
    private MockMvc mockMvc;


    private User user1;
    private User user2;

    private List<User> sampleUsers;


    private Recipe recipe1;
    private Recipe recipe2;

    private List<Recipe> sampleRecipes;

    @Before
    public void setUp() {
        recipe1.setId(RECIPE_ID1);
        recipe1.setName("kalkun");
        recipe1.setPortion(1);
        recipe1.setMaterials("sai");
        recipe1.setDescription("sai palju");
        recipe1.setPrice(3);
        recipe1.setUser(user1);

        sampleRecipes = new ArrayList<>();
        sampleRecipes.add(recipe1);

        user1 = new User();
        user1.setId(TEST_USER_ID1);
        user1.setEmail(TEST_USER_EMAIL);
        user1.setUsername(TEST_USER_USERNAME);
        user1.setPassword(TEST_USER_PASSWORD);


        sampleUsers = new ArrayList<>();
        sampleUsers.add(user1);

        given(userService.getAllUsers()).willReturn(sampleUsers);

        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CorsFilter())
                .build();
    }
    @Test
    public void createUserTest() throws Exception {
        User user = new User();
        user.setEmail("tere@tere.ee");
        user.setPassword("rertert");
        user.setUsername("rerterter");
    }
    @Test
    public void testAddUser() throws Exception {

    }
    @Test
    public void testGetUserById() throws Exception {

    }

    @Test
    public void testGetUserByEmail() throws Exception {

    }
}
