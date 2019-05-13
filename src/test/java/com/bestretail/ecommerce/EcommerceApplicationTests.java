package com.bestretail.ecommerce;

import com.bestretail.ecommerce.user.entites.User;
import com.bestretail.ecommerce.user.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EcommerceApplicationTests {

    @Autowired
    private UserRepository repository;

    @Test
    public void initTest() {
        User user = repository.save(new User("example@exc.com"));
        Optional<User> persistedUser = repository.findById(user.getId());

        Assert.assertTrue(persistedUser.isPresent());
        Assert.assertEquals("example@exc.com", persistedUser.get().getEmail());
    }

}
