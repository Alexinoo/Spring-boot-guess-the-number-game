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
    private static final String WIN = "game.win";
    private static final String LOSE = "game.lose";
    private static final String INVALID_RANGE = "game.invalid.range";
    private static final String FIRST_GUESS = "game.first.guess";
    private static final String HIGHER = "game.higher";
    private static final String LOWER = "game.lower";
    private static final String REMAINING = "game.remaining";

    //fields
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
            //return "You guessed it! The number was "+gameInterface.getNumber();
            return getMessage(WIN,gameInterface.getNumber());
        } else if (gameInterface.isGameLost()) {
            //return "You lost. The number was "+ gameInterface.getNumber();
            return getMessage(LOSE,gameInterface.getNumber());
        }else if (!gameInterface.isValidNumberRange()){
            //return "Invalid number range!";
            return getMessage(INVALID_RANGE);
        } else if (gameInterface.getRemainingGuesses() == gameInterface.getGuessCount()) {
            //return "What is your first guess ?";
            return getMessage(FIRST_GUESS);
        }else {
            //String direction = "Lower";
            String direction = getMessage(LOWER);
            if (gameInterface.getGuess() < gameInterface.getNumber()){
                //direction = "Higher";
                direction = getMessage(HIGHER);
            }
            //return direction +"! You have "+gameInterface.getRemainingGuesses() + " guesses left";
            return getMessage(REMAINING,direction,gameInterface.getRemainingGuesses());
        }
    }


    // private methods
    private String getMessage(String code , Object... args){
        return messageSource.getMessage(code , args, LocaleContextHolder.getLocale());
    }
}
