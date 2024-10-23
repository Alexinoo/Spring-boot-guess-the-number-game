package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainUsingBean {

    private static final Logger log = LoggerFactory.getLogger(MainUsingBean.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        log.info("Guess The Number Game");

        // create IoC container
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

        //get number generator bean from context (IOC container)
       // NumberGeneratorInterface numberGeneratorInterface = context.getBean("numberGenerator", NumberGeneratorInterface.class);

        // get bean by type - using bean as a component and not by String
        NumberGeneratorInterface numberGeneratorInterface = context.getBean(NumberGeneratorInterface.class);

        //call next() to get the random number
        int number = numberGeneratorInterface.next();

        //log generated number
        log.info("number = {}",number);

        //get game bean from context - IOC container
        GameInterface game = context.getBean(GameInterface.class);

        // call reset()
       // game.reset();

        // close IoC container
        context.close();
    }
}
