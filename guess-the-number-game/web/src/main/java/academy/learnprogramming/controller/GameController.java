package academy.learnprogramming.controller;

import academy.learnprogramming.service.GameServiceInterface;
import academy.learnprogramming.util.AttributeNames;
import academy.learnprogramming.util.GameMappings;
import academy.learnprogramming.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class GameController {

    private final GameServiceInterface gameServiceInterface;

    @Autowired
    public GameController(GameServiceInterface gameServiceInterface) {
        this.gameServiceInterface = gameServiceInterface;
    }

    // request methods
    @GetMapping(GameMappings.PLAY)
    public String play(Model model){
      model.addAttribute(AttributeNames.MAIN_MESSAGE,gameServiceInterface.getMainMessage());
      model.addAttribute(AttributeNames.RESULT_MESSAGE,gameServiceInterface.getResultMessage());
      log.info("model = {}",model);
      if (gameServiceInterface.isGameOver())
          return ViewNames.GAME_OVER;
      return ViewNames.PLAY;
    }

    @PostMapping(GameMappings.PLAY)
    public String processMessage(@RequestParam int guess){
        log.info("guess = {}",guess);
        gameServiceInterface.checkGuess(guess);
        return GameMappings.REDIRECT_PLAY;
    }
}
