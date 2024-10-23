package academy.learnprogramming.config;

import academy.learnprogramming.GuessCount;
import academy.learnprogramming.MaxNumber;
import academy.learnprogramming.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
@PropertySource("classpath:config/game.properties")
public class GameConfig {

    /* fields */
    //@Value("${game.maxNumber:20}") // -- - default values if game.properties - commented the values
    @Value("${game.maxNumber}")
    private int maxNumber;

    //@Value("${game.guessCount:5}") //- default values if game.properties - commented the values
    @Value("${game.guessCount}")
    private int guessCount;

    @Value("${game.minNumber:5}")
    private int minNumber;

    /* Bean methods */
    @Bean
    @MaxNumber
    public int maxNumber(){
        return maxNumber;
    }
    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }

    @Bean
    @MinNumber
    public int getMinNumber(){
        return minNumber;
    }
}
