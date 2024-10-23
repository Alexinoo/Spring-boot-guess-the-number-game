package academy.learnprogramming.service;

import academy.learnprogramming.GameInterface;
import academy.learnprogramming.MessageGeneratorInterface;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameServiceImpl implements GameServiceInterface{

    private final GameInterface gameInterface;
    private final MessageGeneratorInterface messageGeneratorInterface;

    @Autowired
    public GameServiceImpl(GameInterface gameInterface, MessageGeneratorInterface messageGeneratorInterface) {
        this.gameInterface = gameInterface;
        this.messageGeneratorInterface = messageGeneratorInterface;
    }


    // init
    @PostConstruct
    public void init(){
        log.info("number = {}",gameInterface.getNumber());
        log.info("mainMessage = {}",messageGeneratorInterface.getMainMessage());
    }

    @Override
    public boolean isGameOver() {
        return gameInterface.isGameLost() || gameInterface.isGameWon();
    }

    @Override
    public String getMainMessage() {
        return messageGeneratorInterface.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGeneratorInterface.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {
        gameInterface.setGuess(guess);
        gameInterface.check();
    }

    @Override
    public void reset() {
        gameInterface.reset();
    }
}
