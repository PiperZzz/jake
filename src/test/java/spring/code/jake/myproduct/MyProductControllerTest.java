package spring.code.jake.myproduct;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MyProductController.class)
public class MyProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyProductService myProductService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser // 添加这行以模拟一个已认证用户
    void testGetUserPassword() throws Exception {
        mockMvc.perform(get("/v1/api/user")
                .param("userName", "testUser")
                .param("password", "testPass"))
                .andExpect(status().isOk())
                .andExpect(content().string("testUsertestPassOK"));
    }

    @Test
    @WithMockUser // 添加这行以模拟一个已认证用户
    void testGetProducts() throws Exception {
        when(myProductService.getProductsByName(anyString(), anyInt(), anyInt())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/v1/api/products")
                .param("keyword", "test")
                .param("pageNumber", "1")
                .param("pageSize", "10"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser // 添加这行以模拟一个已认证用户
    void testCreateUser() throws Exception {
        String userJson = "{\"userName\":\"testUser\", \"password\":\"testPass\"}";

        mockMvc.perform(post("/v1/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(content().string("User created successfully"));
    }
}
