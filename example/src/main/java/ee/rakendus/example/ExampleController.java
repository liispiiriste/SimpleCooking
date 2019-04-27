package ee.rakendus.example;

import ee.rakendus.example.categories.Categories;
import ee.rakendus.example.categories.CategoryRepository;
import ee.rakendus.example.image.ImageService;
import ee.rakendus.example.user.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/api")
public class ExampleController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository repository;
    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Autowired
    RecipeService recipeService;

    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping(value = "/recipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        return new ResponseEntity<>(recipeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/userRecipes")
    public ResponseEntity<List<Recipe>> getAllUserRecipes() {
        return new ResponseEntity<>(recipeService.getAllUserRecipes(), HttpStatus.OK);
    }


    @GetMapping("/recipes/{category}")
    public List<Recipe> getRecipesByCategory(@PathVariable("category") String category) {
        List<Recipe> recipes = new ArrayList<>();
        repository.findByCategory(category).forEach(recipes::add);

        return recipes;
    }

    @GetMapping("/categories")
    public List<Categories> getAllCategories() {
        List<Categories> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);

        return categories;
    }

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
            recipeService.saveRecipe(recipe);
        }
        return new ResponseEntity<>(recipe.getId(), HttpStatus.CREATED);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") long id) {
        recipeService.deleteRecipeById(id);
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
            _recipe.setImage(recipe.getImage());
            return new ResponseEntity<>(repository.save(_recipe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/recipe/{id}/image")
    public ResponseEntity<Recipe> handleImagePost(@PathVariable("id") long id, @RequestParam("image") MultipartFile file) {
        imageService.saveImageFile(id, file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/recipe/{id}/recipeImage")
    public void renderImageFromDB(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
        Recipe recipe = recipeService.findById(id);
        if (recipe.getImage() != null) {
            byte[] byteArray = new byte[recipe.getImage().length];

            int i = 0;

            for (Byte wrappedByte : recipe.getImage()) byteArray[i++] = wrappedByte;

            response.setContentType("image/jpeg");
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            IOUtils.copy(inputStream, response.getOutputStream());
        } else {
            System.out.println("feil");
        }
    }

    @RequestMapping(value = "/recipes/search/{searchStr}", method = RequestMethod.GET)
    public List<Recipe> searchRecipes(@PathVariable("searchStr") String searchStr) {
        return recipeService.searchRecipesByName(searchStr);
    }

}
