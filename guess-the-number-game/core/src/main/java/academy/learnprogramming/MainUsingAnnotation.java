package academy.learnprogramming;

import academy.learnprogramming.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainUsingAnnotation {

    public static final Logger log = LoggerFactory.getLogger(MainUsingAnnotation.class);

    public static void main(String[] args) {

        log.info("Guess the Number Game");

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        NumberGeneratorInterface numberGeneratorInterface = context.getBean(NumberGeneratorInterface.class);

        int number = numberGeneratorInterface.next();

        log.info("number = {}",number);

        GameInterface gameInterface = context.getBean(GameInterface.class);

        context.close();
    }
}
