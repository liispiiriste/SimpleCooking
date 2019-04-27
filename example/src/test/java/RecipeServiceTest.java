import ee.rakendus.example.Recipe;
import ee.rakendus.example.RecipeRepository;
import ee.rakendus.example.RecipeService;
import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserService;
import org.hibernate.validator.constraints.Range;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeService(recipeRepository, userService);
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
    public void testGetAllUserRecipes() {
        //TODO: poolik
        Recipe recipe = new Recipe();
        Recipe recipe2 = new Recipe();

        User user = new User();
        user.setId(1L);
        recipe2.setUser(user);
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);
        recipeList.add(recipe2);

        when(recipeService.getAllUserRecipes()).thenReturn(recipeList);

        List<Recipe> userRecipes = recipeService.getAllUserRecipes();

        assertEquals(userRecipes.size(), 1);
        verify(recipeRepository, times(1)).findAllByUserId(user.getId());
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    public void testDeleteById() throws Exception {
        long id = 2L;
        recipeService.deleteRecipeById(id);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}
