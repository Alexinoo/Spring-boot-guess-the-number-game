package academy.learnprogramming.console;

import academy.learnprogramming.GameInterface;
import academy.learnprogramming.MessageGeneratorInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/* One way - Implement ApplicationListener interface - override onApplicationEvent
@Component
public class ConsoleNumberGuess implements ApplicationListener<ContextRefreshedEvent> {
    public static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("IOC container ready for use.");
    }
} */



/* Second way - Using @EventListener annotation on the method itself */
@Slf4j
@Component
public class ConsoleNumberGuess {

    /* Logger constant
    *
    * Commented out since we have replaced that with Lombok annotations: @Slf4j
    * Becomes available automatically

         public static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);
    */

    /* fields */
    /*
     * Code cleanup
     * Remove @Autowired annotation
     * Make the fields final
     * Add a constructor
     * In my case, will just comment out
     *
    @Autowired
    private GameInterface gameInterface;

    @Autowired
    private MessageGeneratorInterface messageGeneratorInterface;
     */
    private final GameInterface gameInterface;
    private final MessageGeneratorInterface messageGeneratorInterface;

    /*
     * Constructor injection
     * Autowired the constructor
     */
    @Autowired
    public ConsoleNumberGuess(GameInterface gameInterface, MessageGeneratorInterface messageGeneratorInterface) {
        this.gameInterface = gameInterface;
        this.messageGeneratorInterface = messageGeneratorInterface;
    }



    /* events */
    @EventListener(ContextRefreshedEvent.class)
    public void start() {
        log.info("start() -->IOC container ready for use.");

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println(messageGeneratorInterface.getMainMessage());
            System.out.println(messageGeneratorInterface.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            gameInterface.setGuess(guess);
            gameInterface.check();

            if (gameInterface.isGameWon() || gameInterface.isGameLost()){
                System.out.println(messageGeneratorInterface.getResultMessage());
                System.out.println("Play again y/n ?");

                String playAgainString = scanner.nextLine().trim();
                if (!playAgainString.equalsIgnoreCase("y"))
                    break;

                gameInterface.reset();
            }
        }
    }
}