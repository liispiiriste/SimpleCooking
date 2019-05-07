package ee.rakendus.example.controller;

import ee.rakendus.example.repository.RecipeRepository;
import ee.rakendus.example.service.RecipeService;
import ee.rakendus.example.entity.Categories;
import ee.rakendus.example.entity.Recipe;
import ee.rakendus.example.service.ImageService;
import ee.rakendus.example.service.UserService;
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
import java.util.List;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/api")
public class RecipeController {
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
        return recipeService.getRecipesByCategory(category);
    }

    @GetMapping("/categories")
    public List<Categories> getAllCategories() {
        return recipeService.getAllCategories();
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
        recipeService.updateRecipe(id, recipe);
        return new ResponseEntity<>(HttpStatus.OK);

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
