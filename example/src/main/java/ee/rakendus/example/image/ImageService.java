package ee.rakendus.example.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ImageService {
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    Environment environment;
    @PersistenceContext
    public EntityManager entityManager;

    public static String UPLOAD_ROOT = "images/";

    public Resource findOneImage(String filename) {
        return resourceLoader.getResource("file:" + UPLOAD_ROOT + filename);
    }

}
