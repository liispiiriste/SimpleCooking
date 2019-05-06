import com.fasterxml.jackson.databind.ObjectMapper;
import ee.rakendus.example.Recipe;
import ee.rakendus.example.RecipeRepository;
import ee.rakendus.example.RecipeService;
import ee.rakendus.example.categories.Categories;
import ee.rakendus.example.categories.CategoryRepository;
import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserRepository;
import ee.rakendus.example.user.UserService;
import org.hibernate.validator.constraints.Range;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecipeServiceTest {
    @Mock
    RecipeService recipeService;
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeService(recipeRepository, userService, categoryRepository);
    }

    @Test
    public void getRecipeByIdTest() throws Exception {
            Recipe recipe = new Recipe();
            recipe.setId(1L);
            Optional<Recipe> recipeOptional = Optional.of(recipe);

            when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

            Recipe recipeReturned = recipeService.findById(1L);

            assertNotNull("Null recipe returned", recipeReturned);
            verify(recipeRepository, times(1)).findById(anyLong());
            verify(recipeRepository, never()).findAll();
    }

    @Test
    public void testGetAllRecipes() {
        Recipe recipe = new Recipe();
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        when(recipeService.getAllRecipes()).thenReturn(recipeList);

        List<Recipe> recipes = recipeService.getAllRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }
    @Test
    public void testGetAllCategories() {
        Categories category = new Categories();
        List<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(category);

        when(recipeService.getAllCategories()).thenReturn(categoriesList);

        List<Categories> categories = recipeService.getAllCategories();

        assertEquals(categories.size(), 1);
        verify(categoryRepository, times(1)).findAll();
        verify(categoryRepository, never()).findById(anyLong());
    }

    @Test
    public void testGetAllUserRecipes() {
        //TODO: poolik
        Recipe recipe = new Recipe();
        Recipe recipe2 = new Recipe();

        User user = new User();
        user.setId(1L);
        user.setEmail("test@test.ee");
        user.setUsername("test");
        user.setPassword("test");
        userService.saveUser(user);
        recipe2.setUser(user);
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);
        recipeList.add(recipe2);

        when(recipeRepository.findByName(anyString())).thenReturn(recipeList);

        List<Recipe> userRecipes = recipeService.getAllUserRecipes();

        assertEquals(userRecipes.size(), 1);
        verify(recipeRepository, times(1)).findAllByUserId(user.getId());
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void testSaveRecipe() throws Exception {
        User user = new User();
        user.setId(2L);
        user.setEmail("test@test.ee");
        user.setUsername("test");
        user.setPassword("test");
        userService.saveUser(user);

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setName("test");
        recipe.setMaterials("test");
        recipe.setDescription("test");
        recipe.setPortion(1);
        recipe.setPrice(1);

        recipe.setUser(user);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }

    @Test
    public void testDeleteById() throws Exception {
        long id = 2L;
        recipeService.deleteRecipeById(id);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testSearchRecipesByName() {
        Recipe recipe = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe.setId(1L);
        recipe.setName("test");
        recipe.setDescription("test");
        recipe.setMaterials("test");
        recipe.setPortion(1);
        recipe.setPrice(1);
        recipe2.setName("test2");
        recipe2.setId(2L);
        recipe2.setName("test2");
        recipe2.setDescription("test");
        recipe2.setMaterials("test");
        recipe2.setPortion(1);
        recipe2.setPrice(1);

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);

        when(recipeService.searchRecipesByName(anyString())).thenReturn(recipes);

        List<Recipe> recipesByName = recipeService.searchRecipesByName("test");

        assertEquals(recipesByName.size(), 1);
        verify(recipeRepository, times(1)).findByName("test");
        verify(recipeRepository, never()).findAll();

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
