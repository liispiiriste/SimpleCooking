package ee.rakendus.example;

import ee.rakendus.example.image.ImageService;
import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
    RecipeRepository repository;
    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

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

    private Recipe findById(long id) {
        return repository.findRecipeById(id);
    }

    @GetMapping(value="/recipe/{id}")
    public ResponseEntity<Recipe> getRecipeById(@PathVariable("id") long id) {
        return new ResponseEntity<>(findById(id), HttpStatus.OK);
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
            _recipe.setImage(recipe.getImage());
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

    @PostMapping("/recipe/{id}/image")
    public ResponseEntity<Recipe> handleImagePost(@PathVariable String id, @RequestParam("image") MultipartFile file) {
        imageService.saveImageFile(Long.valueOf(id), file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", findById(Long.valueOf(id)));

        return "testUpload";
    }


    @GetMapping("/recipe/{id}/recipeImage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        Recipe recipe = findById(Long.valueOf(id));
        if (recipe.getImage() != null) {
            byte[] byteArray = new byte[recipe.getImage().length];

            int i = 0;

            for (Byte wrappedByte : recipe.getImage()) byteArray[i++] = wrappedByte;

            response.setContentType("image/jpg");
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            IOUtils.copy(inputStream, response.getOutputStream());
        } else {
            System.out.println("feil");

        }
    }

}
