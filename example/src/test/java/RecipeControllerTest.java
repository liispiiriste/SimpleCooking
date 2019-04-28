import com.fasterxml.jackson.databind.ObjectMapper;
import ee.rakendus.example.Recipe;
import ee.rakendus.example.RecipeController;
import ee.rakendus.example.RecipeService;
import ee.rakendus.example.image.ImageService;
import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class RecipeControllerTest {
    @Mock
    RecipeService recipeService;
    @Mock
    UserService userService;
    @Mock
    ImageService imageService;
    @InjectMocks
    RecipeController recipeController;

    private MockMvc mockMvc;

    private static final long TEST_RECIPE_ID1 = 1L;
    private static final long TEST_RECIPE_ID2 = 2L;
    private static final long TEST_USER_ID = 1L;

    private Recipe sampleRecipe1;
    private Recipe sampleRecipe2;

    private User sampleUser1;

    private List<Recipe> sampleRecipes = new ArrayList<>();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        recipeController = new RecipeController();
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void testSaveRecipe() throws Exception {
        sampleRecipe1 = new Recipe();
        sampleRecipe1.setId(TEST_RECIPE_ID1);
        sampleRecipe1.setName("Content");
        sampleRecipe1.setDescription("desc");
        sampleRecipe1.setCategory("magustoit");
        sampleRecipe1.setMaterials("õun");
        sampleRecipe1.setPortion(2);
        sampleRecipe1.setPrice(2);

        sampleRecipe1 = new Recipe();
        sampleRecipe1.setId(TEST_RECIPE_ID2);
        sampleRecipe1.setName("Content3");
        sampleRecipe1.setDescription("desc");
        sampleRecipe1.setCategory("magustoit");
        sampleRecipe1.setMaterials("õun");
        sampleRecipe1.setPortion(2);
        sampleRecipe1.setPrice(2);

        sampleUser1 = new User();
        sampleUser1.setId(TEST_USER_ID);
        sampleUser1.setUsername("koer");
        sampleUser1.setPassword("123");
        sampleUser1.setEmail("koer@koer.ee");

        sampleRecipes.add(sampleRecipe1);
        sampleRecipes.add(sampleRecipe2);

        sampleRecipe1.setUser(sampleUser1);


        given(recipeService.getAllRecipes()).willReturn(sampleRecipes);
        //when(noteService.getNoteById(TEST_RECIPE_ID1)).thenReturn(sampleRecipe1);
        mockMvc.perform(post("http://localhost:8080/api//recipe")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(asJsonString(sampleRecipe1)))
                .andExpect(status().isCreated());
        verify(recipeService, times(1)).saveRecipe(sampleRecipe1);
        verifyNoMoreInteractions(recipeService);
    }

    @Test
    public void testDeleteRecipe() throws Exception {
        doNothing().when(recipeService).deleteRecipeById(sampleRecipe1.getId());
        mockMvc.perform(delete("http://localhost:8080/api/recipe/{id}", sampleRecipe1.getId()))
                .andExpect(status().isOk());
        verify(recipeService, times(1)).deleteRecipeById(sampleRecipe1.getId());
        verifyNoMoreInteractions(recipeService);
    }

    @Test
    public void handleImagePost() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        MockMultipartFile multipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                        "Spring Framework Guru".getBytes());

        mockMvc.perform(multipart("api/recipe/0/image").file(multipartFile))
                .andExpect(status().isCreated());

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
