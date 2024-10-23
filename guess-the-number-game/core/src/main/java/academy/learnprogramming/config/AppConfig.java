package academy.learnprogramming.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 * Commented below because after commenting on the Bean methods, this component is now no longer needed
 * Enabled due to reliance from MessageChallengeOld class
 */
@Configuration
@Import(GameConfig.class)
@ComponentScan(basePackages = "academy.learnprogramming")

public class AppConfig {

    // bean methods

    /*
     * Commented out - for clean up
     *  - Running this will result to an error
     * Need to annotate the NumberGeneratorImpl class as a component
    @Bean
    public NumberGeneratorInterface numberGeneratorInterface(){
        return new NumberGeneratorImpl();
    }
    */

    /*
     *
     * Code cleanup challenge
     * Remove all bean methods from AppConfig class (GameImpl & MessageGeneratorImpl)
     *  - Then add @Component annotations on classes that were produced by these methods


    @Bean
    public GameInterface gameInterface(){
        return new GameImpl();
    }

    @Bean
    public MessageGeneratorInterface messageGeneratorInterface(){
        return new MessageGeneratorImpl();
    }

    * */
}
