package com.bestretail.ecommerce;

import com.bestretail.ecommerce.app.category.Category;
import com.bestretail.ecommerce.app.category.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EcommerceApplicationTests {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void initTest() {
        Category category = new Category();
        category.setName("teste");
        Category persisted = repository.save(category);

        Category fetched = repository.findById(persisted.getId()).get();
        Assert.assertEquals(persisted.getName(), fetched.getName());
    }

}
