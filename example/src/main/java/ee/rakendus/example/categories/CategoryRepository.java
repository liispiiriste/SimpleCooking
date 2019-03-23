package ee.rakendus.example.categories;

import ee.rakendus.example.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Categories, Long> {
    List<Recipe> findByCategory(String name);
}
