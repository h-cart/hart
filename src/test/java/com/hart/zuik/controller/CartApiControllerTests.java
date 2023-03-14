package com.hart.zuik.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.google.gson.Gson;


@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class CartApiControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void insertCarts() throws Exception  {
    
    Map<String, List<String>> requestBody = new HashMap<>();
    List<String> pids = new ArrayList<>();
    List<String> pamounts = new ArrayList<>();
    pids.add("S02203100153");
    pids.add("S02103036855");
    pamounts.add("2");
    pamounts.add("2");
    requestBody.put("pids", pids);
    requestBody.put("pamounts", pamounts);
    String json = new Gson().toJson(requestBody);
    mockMvc.perform(post("/capi/insert")
    		.with(csrf())
    		.content(json)
    		.contentType(MediaType.APPLICATION_JSON_VALUE))
    .andExpect(status().isOk());
  }
  
  
  @Test
  public void getCarts() throws Exception  {
    
    Map<String, List<String>> requestBody = new HashMap<>();

    String json = new Gson().toJson(requestBody);
    MvcResult result = mockMvc.perform(post("/capi/get")
    		.with(csrf())
    		.contentType(MediaType.APPLICATION_JSON_VALUE))
    .andExpect(status().isOk()).andReturn();
    String responseJson = result.getResponse().getContentAsString();
    System.out.println(responseJson);
  }
  
}




