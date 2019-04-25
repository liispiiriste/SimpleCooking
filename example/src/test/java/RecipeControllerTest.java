import ee.rakendus.example.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class RecipeControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getRecipesList() throws Exception {
        String uri = "/api/recipes";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Recipe[] recipeslist = super.mapFromJson(content, Recipe[].class);
        assertTrue(recipeslist.length > 0);
    }
}
