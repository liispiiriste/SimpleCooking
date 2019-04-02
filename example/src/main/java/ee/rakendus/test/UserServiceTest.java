package ee.rakendus.test;

import ee.rakendus.example.user.User;
import ee.rakendus.example.user.UserRepository;
import ee.rakendus.example.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test

    public void ReturnUser(){
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@gmail.com");
        assertEquals("test", user.getUsername());
        assertEquals("test@gmail.com", user.getEmail());
    }
}



