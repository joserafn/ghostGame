package com.ghost.game.controller;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ghost.game.GhostException;
import com.ghost.game.GhostGame;

@Controller
public class GhostController {	
	
	private static final Logger logger = LogManager.getLogger(GhostController.class);
	
	private GhostGame ghostGame;

	@PostConstruct
    public void init() {
		try {
			ghostGame = new GhostGame();
		} catch (GhostException e) {
			logger.error(e);
			e.printStackTrace();
		}
    }
	
	@PostMapping(value = "/ghost")
    public String addLetter(String letter) {
	
		ghostGame.addLetter(letter.charAt(0));
		
        return "redirect:/ghost";
    }
	
	@GetMapping("/ghost")
	public String ghostGame(Model model){
		
		model.addAttribute("wordFragment", ghostGame.getWordFragment());
		model.addAttribute("p1Score", ghostGame.getScorePlayerHuman());
		model.addAttribute("p2Score", ghostGame.getScorePlayerMachine());
		model.addAttribute("message", ghostGame.getMessage());
		
		return "ghost-view";
	}
	
	@GetMapping(value = "/ghost/newRound")
    public String newRound(String letter) {
	
		try {
			ghostGame.newGhostGameRound();
		} catch (GhostException e) {
			logger.error(e);
			e.printStackTrace();
		}
		
        return "redirect:/ghost";
    }
	
	@GetMapping(value = "/ghost/newGame")
    public String newGame(String letter) {
	
		try {
			ghostGame.newGhostGame();
		} catch (GhostException e) {
			logger.error(e);
			e.printStackTrace();
		}
		
        return "redirect:/ghost";
    }
	
}