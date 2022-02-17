package com.example.yashkin.rest;

import com.example.yashkin.model.Role;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.service.impl.Producer;
import com.example.yashkin.service.impl.UserServiceImpl;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@TestPropertySource(locations="classpath:application-test.properties")

/**Для отключения секьюрити преавторайз убрать
 * в секьюрити конфиг @EnableGlobalMethodSecurity(prePostEnabled = true)*/
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureEmbeddedDatabase
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserControllerTest {

    @AutoConfigureMockMvc(addFilters = false)
    @DisplayName("Get all users test")
    @Nested
    class NestedWithoutMocks {

        @AutoConfigureMockMvc(addFilters = false)
        @Test
        void getUsers(@Autowired MockMvc mockMvc) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/api/yashkin/users/"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$", Matchers.hasSize(2)))
                    .andExpect(jsonPath("$[0]", Matchers.is("admin")));
        }

    }

    @AutoConfigureMockMvc(addFilters = false)
    @DisplayName("Get all users test with mock")
    @Nested
    class NestedWithMocks {

        @MockBean
        private UserServiceImpl userService;

        /**
         * Нужно чтобы тест не падал на кафке
         */
        @MockBean
        private Producer producer;

        @AutoConfigureMockMvc(addFilters = false)
        @Test
        void getUsers(@Autowired MockMvc mockMvc) throws Exception {
            when(userService.getUsers()).thenReturn(
                    List.of(new UserResponseDto(0L, "admin", "adminov", "admin", Set.of(Role.ADMIN))
                            , new UserResponseDto(1L, "user", "userov", "user", Set.of(Role.USER))));

            mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/api/yashkin/users/"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$", Matchers.hasSize(2)))
                    .andExpect(jsonPath("$[0].firstName", Matchers.is(
                            new UserResponseDto(0L, "admin", "adminov", "admin", null, Set.of(Role.ADMIN)).getFirstName())));
        }

        @AutoConfigureMockMvc(addFilters = false)
        @Test
        void getById(@Autowired MockMvc mockMvc) throws Exception {

            when(userService.getById(any())).thenReturn(
                    new UserResponseDto(0L, "admin", "adminov", "admin", Set.of(Role.ADMIN)));

            mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/api/yashkin/users/0"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.firstName", Matchers.is(
                            new UserResponseDto(0L, "admin", "adminov", "admin", null, Set.of(Role.ADMIN)).getFirstName())));
        }

    }

/*    PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:9.6.2")
            .withUsername(POSTGRES_USERNAME)
            .withPassword(POSTGRES_PASSWORD);*/

//    @Test
//    public void contextLoads() throws Exception {
//        assertThat(userController).isNotNull();
//    }
//
//    @WithUserDetails("admin")
//    @Test
//    public void test() throws Exception {
//        this.mvc.perform(get("http://localhost/api/yashkin/users/"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("[")));
//    }
//
//    @Test
//    public void accessDeniedTest() throws Exception {
//        this.mvc.perform(get("http://localhost/api/yashkin/users/"))
//                .andDo(print())
//                .andExpect(status().is4xxClientError());
//    }
//
//
//    @WithUserDetails("admin")
//    @Test
//    public void correctLoginTest() throws Exception {
//        this.mvc.perform(get("http://localhost/api/yashkin/users/"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @WithUserDetails("admin")
//    @Test
//    public void authenticatedTest() throws Exception {
//        this.mvc.perform(get("http://localhost/api/yashkin/users/"))
//                .andDo(print())
//                .andExpect(SecurityMockMvcResultMatchers.authenticated());
//    }
//
//    @WithUserDetails("admin")
//    @Test
//    public void getUsers() {
//
//    }
//
//    @WithUserDetails("admin")
//    @Test
//    public void getUserById() {
//    }
//
//    @AfterClass
//    public static void afterClass() throws Exception {
//
//    }
}