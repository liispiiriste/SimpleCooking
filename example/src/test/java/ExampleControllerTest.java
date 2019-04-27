import ee.rakendus.example.ExampleController;
import ee.rakendus.example.Recipe;
import ee.rakendus.example.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class ExampleControllerTest {
    @Mock
    RecipeService recipeService;
    ExampleController exampleController;
    MockMvc mockMvc;

    /*@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        exampleController = new ExampleController();
        mockMvc = mockMvc.standaloneSetup(exampleController).build();
    }


    @Test
    public void testDeleteRecipe() throws Exception {

    }*/
}
