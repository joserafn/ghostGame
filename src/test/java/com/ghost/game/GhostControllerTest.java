package com.ghost.game;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GhostControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private GhostGame gg;

    @Test
    public void ghostGameShouldReturnMessageFromService() throws Exception {
    	
    	// Mocks
    	when(gg.getWordFragment()).thenReturn("");
    	when(gg.getScorePlayerHuman()).thenReturn(0);
    	when(gg.getScorePlayerMachine()).thenReturn(0);
    	when(gg.getMessage()).thenReturn("");		
    	
        this.mvc.perform(get("/ghost")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("<html>")));
    }
    
    @Test
    public void newRoundShouldReturnMessageFromService() throws Exception {
    	
    	// Mocks
    	when(gg.getWordFragment()).thenReturn("ABC");
    	when(gg.getScorePlayerHuman()).thenReturn(5);
    	when(gg.getScorePlayerMachine()).thenReturn(1);
    	when(gg.getMessage()).thenReturn("Machine wins");		
    	
        this.mvc.perform(get("/ghost/newRound")).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ghost"));
    }
    
    @Test
    public void newGameShouldReturnMessageFromService() throws Exception {
    	
    	// Mocks
    	when(gg.getWordFragment()).thenReturn("ABC");
    	when(gg.getScorePlayerHuman()).thenReturn(5);
    	when(gg.getScorePlayerMachine()).thenReturn(1);
    	when(gg.getMessage()).thenReturn("Machine wins");		
    	
        this.mvc.perform(get("/ghost/newGame")).andDo(print()).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ghost"));
    }
    
}