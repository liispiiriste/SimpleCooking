package ee.rakendus.example.repository;

import ee.rakendus.example.entity.Categories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Categories, Long> {
    List<Categories> findByName(String name);
}

