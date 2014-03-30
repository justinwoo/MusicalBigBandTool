import util.Transcriber;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxwoo
 * Date: 3/26/14
 * Time: 12:47 PM
 * just because i wrote this code don't mean it works
 * cross your fingers and run the tests!
 */
public class Application {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
//        Time to alt-tab into Skullgirls
        Thread.sleep(1000);

//        Play the notes in specified file or notes.csv
        String file = "notes.csv";
        if (args.length == 1) {
            file = args[0];
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;

//        initialize keypress buffers
        List<Integer> previousKeys1P = null;
        List<Integer> previousKeys2P = null;
        List<Integer> nextKeys1P = null;
        List<Integer> nextKeys2P = null;
        String rest;

//        Make Big Band pick up his trumpet to JAM!!!
        initTrumpet(robot, nextKeys1P, previousKeys1P, nextKeys2P, previousKeys2P);

        while ((line = br.readLine()) != null) {
//            C1,200 gets split into C1 and 200
            String split[] = line.split(",");
            if (split.length == 2) { // If line only contains one note
                String note = split[0];
                rest = split[1];
//            Transcribe the note into the keystrokes needed
                nextKeys1P = Transcriber.transcribe(note);
                if (!nextKeys1P.isEmpty() && previousKeys1P != null) {
                    releaseKeys(robot, previousKeys1P);
                }
                pressKeys(robot, nextKeys1P);
                previousKeys1P = nextKeys1P;
            } else if (split.length == 3) { // If line only contains two notes
                String note1 = split[0];
                String note2 = split[1];
                rest = split[2];
                nextKeys1P = Transcriber.transcribe(note1);
                nextKeys2P = Transcriber.translateTo2P(Transcriber.transcribe(note2));

                if (!nextKeys1P.isEmpty() && previousKeys1P != null) {
                    releaseKeys(robot, previousKeys1P);
                }
                if (!nextKeys2P.isEmpty() && previousKeys2P != null) {
                    releaseKeys(robot, previousKeys2P);
                }

                pressKeys(robot, nextKeys1P);
                pressKeys(robot, nextKeys2P);
                previousKeys1P = nextKeys1P;
                previousKeys2P = nextKeys2P;
            } else {
                throw new IllegalArgumentException("Line contains more than three elements.");
            }

//            Rest after the note has been played.
            Thread.sleep(Long.parseLong(rest));
        }

//        Release keys one last time
        if (previousKeys1P != null) {
            releaseKeys(robot, previousKeys1P);
        }
        if (previousKeys2P != null) {
            releaseKeys(robot, previousKeys2P);
        }

        System.out.println("All done");
    }

    private static void initTrumpet(Robot robot, List<Integer> nextKeys1P, List<Integer> previousKeys1P, List<Integer> nextKeys2P, List<Integer> previousKeys2P) throws InterruptedException {
//        pick up the trumpet
        robot.keyPress(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_I);
        Thread.sleep(100);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_I);

//        play a blank note and then queue up the release
        nextKeys1P = new LinkedList<>();
        nextKeys2P = new LinkedList<>();
        nextKeys1P.add(KeyEvent.VK_Z);
        nextKeys1P.add(KeyEvent.VK_X);
        nextKeys1P.add(KeyEvent.VK_C);
        nextKeys2P.add(KeyEvent.VK_J);
        nextKeys2P.add(KeyEvent.VK_K);
        nextKeys2P.add(KeyEvent.VK_L);
        pressKeys(robot, nextKeys1P);
        pressKeys(robot, nextKeys2P);
        previousKeys1P = nextKeys1P;
        previousKeys2P = nextKeys2P;

        Thread.sleep(1000);
    }

    private static void pressKeys(Robot robot, List<Integer> keys) {
        for (Integer key : keys) {
            if (key != null) {
                robot.keyPress(key);
            }
        }
    }

    private static void releaseKeys(Robot robot, List<Integer> keys) {
        for (Integer key: keys) {
            robot.keyRelease(key);
        }
    }
}
