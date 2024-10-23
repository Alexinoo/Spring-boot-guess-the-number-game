package academy.learnprogramming.console;

import academy.learnprogramming.config.AppConfig;
import academy.learnprogramming.MessageGeneratorInterface;
import academy.learnprogramming.NumberGeneratorInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MessageChallengeOld {

    public static final Logger log = LoggerFactory.getLogger(MessageChallengeOld.class);

    public static void main(String[] args) {
        log.info("Guess the number Game");

        /* Create context (IoC) */
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


        /* Get number generator Bean by Type from IoC */
        NumberGeneratorInterface numberGeneratorInterface = context.getBean(NumberGeneratorInterface.class);

        int number = numberGeneratorInterface.next();

        /* log generated number */
        log.info("number = {}",number);

        /* Get message generator Bean by Type from IoC */
        MessageGeneratorInterface messageGeneratorInterface = context.getBean(MessageGeneratorInterface.class);

        /* log some messages */
        log.info("getMainMessage = {}",messageGeneratorInterface.getMainMessage());
        log.info("getResultMessage = {}",messageGeneratorInterface.getResultMessage());

        /* close context (IoC) */
        context.close();
    }
}
