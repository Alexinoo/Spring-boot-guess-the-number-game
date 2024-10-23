package academy.learnprogramming;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

//@Component("generator")
@Component
public class NumberGeneratorImpl implements NumberGeneratorInterface {
    // fields
    private final Random random = new Random();

    /* Commented during clean up
    @Autowired
    @MaxNumber
    private int maxNumber;

    @Autowired
    @MinNumber
    private int minNumber;

    */

    /* Made immutable */
    @Getter
    private final int maxNumber;
    @Getter
    private final int minNumber;

    /* constructor */
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    // methods
    @Override
    public int next() {
        //return random.nextInt(maxNumber);

        // change logic to below
        // example; min=5 max=20 -> max-min=15 -> range 0-15 + min -> 5-20
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }

    /*
    * Commented out since we have annotated each fields with @Getter lombok annotation
    * Another alternative is to add @Getter at the class level
    *   - Then annotate random field variable with a @Getter(ACCESS_LEVEL.NONE)
    *   - So that it doesn't apply to this field but the rest
    *

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    */
}
