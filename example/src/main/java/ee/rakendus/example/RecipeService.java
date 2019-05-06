package ee.rakendus.example;

import ee.rakendus.example.categories.Categories;
import ee.rakendus.example.categories.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    public RecipeService(RecipeRepository recipeRepository, UserService userService, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    public List<Recipe> getAllRecipes() {
        //ei saa niisama findAll jätta, sest tagastab iterationi, mitte listi
        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }
    public List<Categories> getAllCategories() {
        List<Categories> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
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
        rec.setCategory(recipe.getCategory());
        rec.setPrice(recipe.getPrice());
        rec.setPortion(recipe.getPortion());

        recipeRepository.save(rec);
    }

    public Recipe findById(long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if (!recipeOptional.isPresent()) throw new RuntimeException("Recipe Not Found!");

        return recipeOptional.get();
    }

    public List<Recipe> getRecipesByCategory(String category) {

        return recipeRepository.findByCategory(category);
    }
    public List<Recipe> searchRecipesByName(String searchStr) {

        return recipeRepository.findByName(searchStr);
    }

    public void deleteRecipeById(long id) {

        recipeRepository.deleteById(id);
    }
}
