package za.co.wethinkcode.examples.hangman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


class PlayerTest {
    @Test
    public void shouldStartWith5Chances() {
        Player player = new Player();
        Assertions.assertEquals(5, player.getChances());
    }
    @Test
    public void canLoseAChance() {
        Player player = new Player();
        int chances = player.getChances();
        player.lostChance();
        Assertions.assertEquals(chances - 1, player.getChances());
    }

    @Test
    public void noMoreChances() {
        Player player = new Player();
        int chances = player.getChances();
        for (int i = 0; i < chances; i++) {
            Assertions.assertFalse(player.hasNoChances());
            player.lostChance();
        }
        Assertions.assertTrue(player.hasNoChances());
    }


    @Test
    public void useDefaultWordFile() {
        byte[] inputStreamData = "\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputStreamData);

        Player player = new Player(inputStream);
        Assertions.assertEquals("short_words.txt", player.getWordsFile());
    }

    @Test
    public void cannotLoseChanceIfNoneAvailable() {
        Player player = new Player();
        int chances = player.getChances();
        for (int i = 0; i < chances + 1; i++) {
            player.lostChance();
        }
        Assertions.assertEquals(0, player.getChances());
    }

    @Test
    public void shouldProvideWordFile() {
        byte[] inputStreamData = "flowers.txt\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputStreamData);

        Player player = new Player(inputStream);
        Assertions.assertEquals("flowers.txt", player.getWordsFile());
    }

    @Test
    public void takeAGuess() {
        byte[] inputStreamData = "e\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputStreamData);

        Player player = new Player(inputStream);
        Assertions.assertEquals("e", player.getGuess());
    }


    @Test
    public void quitWithQuit() {
        byte[] inputStreamData = "quit\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputStreamData);

        Player player = new Player(inputStream);
        Assertions.assertEquals("quit", player.getGuess());
        Assertions.assertTrue(player.wantsToQuit());
    }

    @Test
    public void quitWithExit() {
        byte[] inputStreamData = "exit\n".getBytes();
        InputStream inputStream = new ByteArrayInputStream(inputStreamData);

        Player player = new Player(inputStream);
        Assertions.assertEquals("exit", player.getGuess());
        Assertions.assertTrue(player.wantsToQuit());
    }
}