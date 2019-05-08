package ee.rakendus.example.repository;

import ee.rakendus.example.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {


    List<Recipe> findByName(String searchStr);

    List<Recipe> findByCategory(String category);
    List<Recipe> findAllByUserId(long id);
    Set<Recipe> findAllFavByUserId(long id);
    Recipe findRecipeById(long id);

}

