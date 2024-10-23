package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MessageChallenge {

   /*
    * Commented since we added @Slf4j
    *
     public static final Logger log = LoggerFactory.getLogger(MessageChallenge.class);
    */

    public static void main(String[] args) {
        log.info("Guess The Number Game");

    /*
     * Now that AppConfig is an empty class create a container using GameConfig class

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

     */

    /*
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);
        context.close();

     */
        SpringApplication.run(MessageChallenge.class, args);
    }

}
