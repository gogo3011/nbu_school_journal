package com.example.schooljournal.web.endpoints;

import static org.mockito.Mockito.when;

import com.example.schooljournal.services.ParentService;
import com.example.schooljournal.services.UserService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ParentEndpoint.class})
@ExtendWith(SpringExtension.class)
class ParentEndpointTest {
    @Autowired
    private ParentEndpoint parentEndpoint;

    @MockBean
    private ParentService parentService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link ParentEndpoint#list(Integer, Integer, String)}
     */
    @Test
    void testList() throws Exception {
        // Arrange
        when(parentService.getAll(Mockito.<Pageable>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/parent/list");
        MockHttpServletRequestBuilder paramResult = getResult.param("pageNo", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("pageSize", String.valueOf(1))
                .param("sortBy", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(parentEndpoint)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
