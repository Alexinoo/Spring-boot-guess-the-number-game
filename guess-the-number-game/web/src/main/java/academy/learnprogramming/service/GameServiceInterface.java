package academy.learnprogramming.service;

public interface GameServiceInterface {

    boolean isGameOver();
    String getMainMessage();
    String getResultMessage();
    void checkGuess(int guess);

    void reset();

}
