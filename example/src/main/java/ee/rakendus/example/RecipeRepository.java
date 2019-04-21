package ee.rakendus.example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {


    List<Recipe> findByName(String searchStr);

    List<Recipe> findByCategory(String name);
    List<Recipe> findAllByUserId(long id);

}

