package ee.rakendus.example;

import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserService userService;

    public RecipeService(RecipeRepository recipeRepository, UserService userService) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
    }

    public List<Recipe> getAllRecipes() {
        //ei saa niisama findAll jätta, sest tagastab iterationi, mitte listi
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }

    public List<Recipe> getAllUserRecipes() {
        long userId = userService.findCurrentUserId().getId();
        return recipeRepository.findAllByUserId(userId);
    }

    public void saveRecipe(Recipe recipe) {
        User user = userService.findCurrentUserId();
        recipe.setUser(user);
        recipeRepository.save(recipe);
    }
    public void updateRecipe(long id, Recipe recipe) {
        Recipe rec = findById(id);
        rec.setName(recipe.getName());
        rec.setDescription(recipe.getDescription());
        rec.setMaterials(recipe.getMaterials());
        rec.setPrice(recipe.getPrice());
        rec.setPortion(recipe.getPortion());
        
        recipeRepository.save(rec);
    }

    public Recipe findById(long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe Not Found!");
        }

        return recipeOptional.get();
    }
    public List<Recipe> searchRecipesByName(String searchStr) {
        return recipeRepository.findByName(searchStr);
    }

    public void deleteRecipeById(long id) {
        recipeRepository.deleteById(id);
    }
}
