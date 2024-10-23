package academy.learnprogramming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Component
public class GameImpl implements GameInterface{

    // constants
    /*
     * Commented out since we're now using Lombok with @Slf4j

        private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

     */


    // fields
    /*
     * Code cleanup
     * Remove @Autowired annotation
     * Make the fields final
     * Add a constructor
     * In my case, will just comment out

    @Autowired
    private NumberGeneratorInterface numberGeneratorInterface;

    @Autowired
    @GuessCount
    private int guessCount;

    */

    /*
     * Getter for numberGeneratorInterface was not required
     * Hence we had to annotate this field with @Getter(AccessLevel.NONE) to indicate that the getter
     *  for this field isn't required
     */
    @Getter(AccessLevel.NONE)
    private final NumberGeneratorInterface numberGeneratorInterface;

    private final int guessCount;

    private int number;


    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    /*
     * Add the Setter annotation below guess field
     * Indicate that it's only applying to the guess field and not the rest
     */

    @Setter
    private int guess;

    /* Constructor based dependency injection
     * commented to use setter based
        public GameImpl(NumberGenerator numberGenerator) {
            this.numberGenerator = numberGenerator;
        }
    */

    /*
     * Code cleanup
     * Make Autowired fields final
     * Add a constructor injection
     * Autowired the constructor
     */

    @Autowired
    public GameImpl(NumberGeneratorInterface numberGeneratorInterface, @GuessCount int guessCount) {
        this.numberGeneratorInterface = numberGeneratorInterface;
        this.guessCount = guessCount;
    }
    // Methods
    /* reset() - set numbers to default at the start of the game
     * will be called at the start of the game
     *
     * init()
     *
     * Annotate with @PostConstruct
     */

   // @PostConstruct
    @Override
    public void reset() {

        //smallest = 0;
        //guess = 0;

        // change both guess and smallest to use minNumber instead
        smallest = numberGeneratorInterface.getMinNumber();
        guess = numberGeneratorInterface.getMinNumber();

        remainingGuesses = guessCount;
        biggest = numberGeneratorInterface.getMaxNumber();
        number = numberGeneratorInterface.next();
        log.debug("the number is {}",number);
    }

    //@PreDestroy
    public void preDestroy(){
        log.info("in Game preDestroy()");
    }

    /*
        setter based injection - then change the beans to use setter based

        public void setNumberGenerator(NumberGeneratorInterface numberGeneratorInterface){
            this.numberGeneratorInterface = numberGeneratorInterface;
        }
    */

    /*
     * Comment out on all Getters, since we have added @Getter annotation using Lombok
     * Comment out on isValidNumberRange()
     *


    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }
    *
    *
    * Comment out on isValidNumberRange()

        @Override
        public boolean isValidNumberRange() {
            return validNumberRange;
        }
    */



    /*
     * Comment out on the Setter - setGuess()
     * Add @Setter at the variable level - only field guess is affected
     *
        @Override
        public void setGuess(int guess) {
            this.guess = guess;
        }
     */



    /*
     * Adjusting the range for the player to guess
     * Then reduce the number of remaining guesses
     */

    @Override
    public void check() {
        checkValidNumberRange();
        if (validNumberRange){
            if (guess > number) {
                biggest = guess - 1;
            }

            if (guess < number) {
                smallest = guess + 1;
            }
        }
        remainingGuesses--;
    }



    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    // private methods
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
