package ee.rakendus.example.categories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Categories, Long> {
    List<Categories> findByName(String name);
}

