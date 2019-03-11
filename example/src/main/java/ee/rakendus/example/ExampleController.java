package ee.rakendus.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Retsept on kustutatud!", HttpStatus.OK);
    }

    @PutMapping("/recipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") long id, @RequestBody Recipe recipe){
        Optional<Recipe> recipeData = repository.findById(id);

        if(recipeData.isPresent()) {
            Recipe _recipe = recipeData.get();
            _recipe.setName(recipe.getName());
            _recipe.setDescription(recipe.getDescription());
            _recipe.setMaterials(recipe.getMaterials());
            //_recipe.setCategories(recipe.getCategories());
            _recipe.setPrice(recipe.getPrice());
            _recipe.setPortion(recipe.getPortion());
            return new ResponseEntity<>(repository.save(_recipe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
