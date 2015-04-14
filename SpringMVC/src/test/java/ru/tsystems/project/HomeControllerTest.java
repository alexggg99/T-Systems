/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.tsystems.project;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import org.springframework.web.context.WebApplicationContext;
import ru.tsystems.project.controllers.HomeController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class HomeControllerTest {
    
    @Autowired private WebApplicationContext ctx;
 
    private MockMvc mockMvc;
    
    @Before public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController())
                                 .setViewResolvers(viewResolver)
                                 .build();
    }
    
    @Test public void testHome() throws Exception {
        mockMvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("serverTime"));
    }
    
    @Test public void testLogin() throws Exception {
        mockMvc.perform(get("/login").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("passenger"));
    }
    
    @Test public void testEmployeeMain() throws Exception {
        mockMvc.perform(get("/employee_main").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }
    
    @Configuration
    public static class TestConfiguration {
 
        @Bean public HomeController homeController() {
            return new HomeController();
        }
 
    }
    
}
