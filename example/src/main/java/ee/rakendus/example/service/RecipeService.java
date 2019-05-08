package ee.rakendus.example.service;

import ee.rakendus.example.entity.Categories;
import ee.rakendus.example.entity.Recipe;
import ee.rakendus.example.repository.CategoryRepository;
import ee.rakendus.example.repository.RecipeRepository;
import ee.rakendus.example.entity.User;
import ee.rakendus.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, UserService userService, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
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
    public Set<Recipe> getAllUserFavouriteRecipes() {
        User user = userService.findCurrentUserId();
        return user.getFavouriteRecipes();
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
    @Transactional
    public void addToFavourite(Recipe recipe, User user) {
        recipe.getUserFavourites().add(user);
        user.getFavouriteRecipes().add(recipe);
        recipeRepository.save(recipe);
        userRepository.save(user);
    }

    public void removeFromFavourites(Recipe recipe, User user) {
        recipe.getUserFavourites().remove(user);
        user.getFavouriteRecipes().remove(recipe);
    }
}
