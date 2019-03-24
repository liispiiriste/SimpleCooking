/*package ee.rakendus.example.categories;


import ee.rakendus.example.Recipe;
import ee.rakendus.example.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    CategoryRepository repository;

    @GetMapping("/recipes/{c}")
    public List<Recipe> getRecipesByCategory(@PathVariable("c") String category) {
        List<Recipe> recipes = new ArrayList<>();
        repository.findByCategory(category).forEach(recipes::add);

        return recipes;
    }
}
*/