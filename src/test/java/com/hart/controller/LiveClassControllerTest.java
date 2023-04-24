package com.hart.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hart.controller.liveClass.LiveClassController;
import com.hart.domain.liveClass.LiveClassDetailInfoDTO;
import com.hart.domain.liveClass.LiveClassListDTO;
import com.hart.domain.liveClass.LiveClassRegisterDTO;
import com.hart.domain.liveClass.LiveClassVideoDTO;
import com.hart.service.liveClass.LiveClassService;

@RunWith(MockitoJUnitRunner.class)
public class LiveClassControllerTest {
    
    @Mock
    private LiveClassService service;
    
    @InjectMocks
    private LiveClassController controller;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @Test
    public void testGetLiveClassList() throws Exception {
        List<LiveClassListDTO> list = new ArrayList<>();
        when(service.getList()).thenReturn(list);
        
        mockMvc.perform(get("/class"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("liveClassList", list))
            .andExpect(model().attribute("status", 1))
            .andExpect(view().name("liveClass/liveClassList"));
        
        verify(service, times(1)).getList();
    }
    
    @Test
    public void testGetLiveClassListDetail() throws Exception {
        String lcid = "skarns23@yu.ac.kr";
        LiveClassDetailInfoDTO dto = new LiveClassDetailInfoDTO();
        when(service.getClassDetail(lcid)).thenReturn(dto);
        
        mockMvc.perform(get("/class/detail/" + lcid))
            .andExpect(status().isOk())
            .andExpect(model().attribute("liveClass", dto))
            .andExpect(view().name("liveClass/liveClassDetail"));
        
        verify(service, times(1)).getClassDetail(lcid);
    }
    
    @Test
    public void testGetVideoDetail() throws Exception {
        String lcid = "skarns23@yu.ac.kr";
        LiveClassDetailInfoDTO dto = new LiveClassDetailInfoDTO();
        LiveClassVideoDTO videoDto = new LiveClassVideoDTO();
        when(service.getClassDetail(lcid)).thenReturn(dto);
        when(service.getClassVideo(lcid)).thenReturn(videoDto);
        
        mockMvc.perform(get("/class/video/" + lcid))
            .andExpect(status().isOk())
            .andExpect(model().attribute("liveClass", dto))
            .andExpect(model().attribute("videoInfo", videoDto))
            .andExpect(view().name("liveClass/liveClassVideo"));

        verify(service, times(1)).getClassDetail(lcid);
        verify(service, times(1)).getClassVideo(lcid);
    }
    
    @Test
    public void testRegisterVOD() throws Exception {
        LiveClassRegisterDTO dto = new LiveClassRegisterDTO();
        
        mockMvc.perform(post("/class/register")
                .flashAttr("dto", dto))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/admin/class"));
        
        verify(service, times(1)).registerVOD(dto);
    }
    
}

