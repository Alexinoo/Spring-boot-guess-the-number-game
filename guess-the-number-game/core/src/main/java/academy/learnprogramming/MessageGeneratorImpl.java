package academy.learnprogramming;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGeneratorInterface{

    //constants
    private static final String MAIN_MESSAGE = "game.main.message";
    private String mainMessage = "getMainMessage() called";
    private String resultMessage = "getResultMessage() called";

    /* Logger constants
    *
    * Commented out since we've added @Slf4j annotation
    *
        public static final Logger log = LoggerFactory.getLogger(MessageGeneratorImpl.class);
    */


    /* fields */
    /*
     * Code cleanup
     * Make this field final
     *
        @Autowired
        private GameInterface gameInterface;
    */
     private final GameInterface gameInterface;

     /*
      * Message Source
      */
     private MessageSource messageSource;

     /*
      * constructor injection
      * Autowired
      */

    @Autowired
    public MessageGeneratorImpl(GameInterface gameInterface,
                                MessageSource messageSource) {
        this.gameInterface = gameInterface;
        this.messageSource = messageSource;
    }

    /* Removed  - later */
    //private int guessCount = 10;

    /* init methods */
    @PostConstruct
    public void init(){
        log.info("Game = {}", gameInterface);
    }


    /* public methods */
    @Override
    public String getMainMessage() {
       /* return mainMessage; */
      /*  return "Number is between "+
                gameInterface.getSmallest() +
                " and " +
                gameInterface.getBiggest() +
                ". Can you guess it ?"; */
      /* Internationalization in action */
      return getMessage(MAIN_MESSAGE,gameInterface.getSmallest(),gameInterface.getBiggest());
    }

    @Override
    public String getResultMessage() {
       /* return resultMessage; */
        if (gameInterface.isGameWon()){
            return "You guessed it! The number was "+gameInterface.getNumber();
        } else if (gameInterface.isGameLost()) {
            return "You lost. The number was "+ gameInterface.getNumber();
        }else if (!gameInterface.isValidNumberRange()){
            return "Invalid number range!";
        } else if (gameInterface.getRemainingGuesses() == gameInterface.getGuessCount()) {
            return "What is your first guess ?";
        }else {
            String direction = "Lower";
            if (gameInterface.getGuess() < gameInterface.getNumber()){
                direction = "Higher";
            }
            return direction +"! You have "+gameInterface.getRemainingGuesses() + " guesses left";
        }
    }


    // private methods
    private String getMessage(String code , Object... args){
        return messageSource.getMessage(code , args, LocaleContextHolder.getLocale());
    }
}
