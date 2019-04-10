package ee.rakendus.example;

import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/api")
public class ExampleController {

    @Autowired
    RecipeRepository repository;
    @Autowired
    UserService userService;

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        repository.findAll().forEach(recipes::add);

        return recipes;
    }
    private List<Recipe> getAllRecipesList() {
        long userId = userService.findCurrentUserId().getId();
        List<Recipe> recipes = getRecipesByUserList(userId);
        Collections.reverse(recipes);
        return recipes;
    }
    @GetMapping("/userRecipes")
    public ResponseEntity<List<Recipe>> getAllUserRecipes() {
        return new ResponseEntity<>(getAllRecipesList(), HttpStatus.OK);
    }


    @GetMapping("/recipes/{c}")
    public List<Recipe> getRecipesByCategory(@PathVariable("c") String category) {
        List<Recipe> recipes = new ArrayList<>();
        repository.findByCategory(category).forEach(recipes::add);

        return recipes;
    }

    private void saveRecipe(Recipe recipe) {
        User user = userService.findCurrentUserId();
        recipe.setUser(user);
        repository.save(recipe);

    }
   /* private void saveCategory(Categories categories)
    {

    }*/
    @PostMapping("/recipe")
    public ResponseEntity postRecipe(@RequestBody Recipe recipe) {
        boolean error = false;
        if (recipe.getName().isEmpty()) error = true;
        if (recipe.getDescription().isEmpty()) error = true;
        /*if(recipe.getCategory().isEmpty){
            error=true;
        }*/
        if (recipe.getMaterials().isEmpty()) error = true;
        if (error == false) {
            saveRecipe(recipe);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Retsept on kustutatud!", HttpStatus.OK);
    }

    @PutMapping("/recipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe) {
        Optional<Recipe> recipeData = repository.findById(id);

        if (recipeData.isPresent()) {
            Recipe _recipe = recipeData.get();
            _recipe.setName(recipe.getName());
            _recipe.setDescription(recipe.getDescription());
            _recipe.setMaterials(recipe.getMaterials());
            _recipe.setCategory(recipe.getCategory());
            _recipe.setPrice(recipe.getPrice());
            _recipe.setPortion(recipe.getPortion());
            return new ResponseEntity<>(repository.save(_recipe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private List<Recipe> getRecipesByUserList(Long userId) {
        return repository.findAllByUserId(userId);
    }
    @GetMapping("/user/{user}")
    public ResponseEntity<List<Recipe>> getRecipesByUser(@PathVariable("user") long userId) {
        return new ResponseEntity<>(getRecipesByUserList(userId), HttpStatus.OK);
    }

}
