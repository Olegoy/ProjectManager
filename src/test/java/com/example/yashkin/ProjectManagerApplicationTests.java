package com.example.yashkin;

import com.example.yashkin.rest.UserController;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureEmbeddedDatabase
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProjectManagerApplicationTests {

//    @Autowired
//    private UserController userController;

    @DisplayName("App context load test")
    @Test
    void contextLoads(@Autowired UserController userController) {
        assertNotNull(userController);
    }

}
