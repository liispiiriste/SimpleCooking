package ee.rakendus.example;

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

    public List<Recipe> searchRecipesByName(String searchStr) {
        return recipeRepository.findByName(searchStr);
    }
}
