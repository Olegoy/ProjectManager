package com.example.yashkin.rest;

import com.example.yashkin.entity.UserEntity;
import com.example.yashkin.model.Role;
import com.example.yashkin.repository.UserRepository;
import com.example.yashkin.rest.dto.AuthenticationRequestDto;
import com.example.yashkin.rest.dto.UserResponseDto;
import com.example.yashkin.security.JwtTokenProvider;
import com.example.yashkin.service.impl.Producer;
import com.example.yashkin.service.impl.UserServiceImpl;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@TestPropertySource(locations="classpath:application-test.properties")

/**?????? ???????????????????? ?????????????????? ?????????????????????? ????????????
 * ?? ?????????????????? ???????????? @EnableGlobalMethodSecurity(prePostEnabled = true)*/
@AutoConfigureMockMvc(addFilters = false)
@AutoConfigureEmbeddedDatabase
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserControllerTest {

    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    protected static class TestConfiguration {
        @Bean
        @Primary
        public UserRepository gatUserRepository(){
            return Mockito.mock(UserRepository.class);
        }
    }

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
         * ?????????? ?????????? ???????? ???? ?????????? ???? ??????????
         */
        @MockBean
        private Producer producer;

        @MockBean
        private AuthenticationRestControllerV1 authenticationRestControllerV1;

        @Autowired
        UserRepository userRepository;

        @Autowired
        JwtTokenProvider jwtTokenProvider;

        Map<Object, Object> response = new HashMap<>();

        @Before
        void login() {

//            AuthenticationRequestDto dto = new AuthenticationRequestDto("admin", "admin");
//            UserEntity user = new UserEntity(0L, "admin", "adminov", "admin", "admin", Set.of(Role.ADMIN));
//
//            when(userRepository.getUserByLogin(dto.getLogin())).thenReturn(java.util.Optional.ofNullable(user));
//            String token = jwtTokenProvider.createToken(dto.getLogin(), user.getRoles().stream().findFirst().get().name());
//
//
//            response.put("login", dto.getLogin());
//            response.put("token", token);
//            final var result = authenticationRestControllerV1.authenticate(dto);

            MockitoAnnotations.initMocks(this);
        }

        @WithMockUser(authorities={"users:write"})
        @AutoConfigureMockMvc(addFilters = false)
        @Test
         void getUsers(@Autowired MockMvc mockMvc) throws Exception {
            AuthenticationRequestDto dto = new AuthenticationRequestDto("admin", "admin");
            UserEntity user = new UserEntity(0L, "admin", "adminov", "admin", "admin", Set.of(Role.ADMIN));

            when(userRepository.getUserByLogin(dto.getLogin())).thenReturn(java.util.Optional.ofNullable(user));
            when(userService.getUsers()).thenReturn(
                    List.of(new UserResponseDto(0L, "admin", "adminov", "admin", Set.of(Role.ADMIN))
                            , new UserResponseDto(1L, "user", "userov", "user", Set.of(Role.USER))));

            mockMvc.perform(MockMvcRequestBuilders.get("http://localhost/api/yashkin/users/")
                            .header("Authorization", response.get("token")))
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