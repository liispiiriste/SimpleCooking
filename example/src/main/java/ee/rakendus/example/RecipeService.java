package ee.rakendus.example;

import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserService userService;

    public RecipeService(RecipeRepository recipeRepository, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }
    public void saveRecipe(Recipe recipe) {
        User user = userService.findCurrentUserId();
        recipe.setUser(user);
        recipeRepository.save(recipe);
    }

    public Recipe findById(long id) {
        return recipeRepository.findRecipeById(id);
    }
    public List<Recipe> searchRecipesByName(String searchStr) {
        return recipeRepository.findByName(searchStr);
    }
}
