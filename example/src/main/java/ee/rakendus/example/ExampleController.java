package ee.rakendus.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/api")
public class ExampleController {

    @Autowired
    RecipeRepository repository;

    @GetMapping("/recipes")
   public List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        repository.findAll().forEach(recipes::add);

        return recipes;
    }

    @PostMapping("/recipe")
    public Recipe postRecipe(@RequestBody Recipe recipe) {
        Recipe _recipe = repository.save(new Recipe(recipe.getName(), recipe.getDescription(), recipe.getMaterials(), recipe.getPortion(), recipe.getPrice()));
        return _recipe;
    }
}
