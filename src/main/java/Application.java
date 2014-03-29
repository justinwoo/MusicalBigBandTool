import util.Transcriber;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
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

//        Make Big Band pick up his trumpet to JAM!!!
        initTrumpet(robot);

//        Play the notes in notes.csv (needs to be changed later)
        FileReader fr = new FileReader("notes.csv");
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<Integer> keys = null;
        String rest;

        while ((line = br.readLine()) != null) {
//            release the previous note's keys
            if (keys != null) {
                releaseKeys(robot, keys);
            }

//            C1,200 gets split into C1 and 200
            String split[] = line.split(",");
            if (split.length == 2) { // If line only contains one note
                String note = split[0];
                rest = split[1];
//            Transcribe the note into the keystrokes needed
                keys = Transcriber.transcribe(note);
            } else if (split.length == 3) { // If line only contains two notes
                String note1 = split[0];
                String note2 = split[1];
                rest = split[2];
                keys = Transcriber.transcribe(note1);
                List<Integer> keys2 = Transcriber.transcribe(note2);
                keys.addAll(Transcriber.translateTo2P(keys2));
            } else {
                throw new IllegalArgumentException("Line contains more than three elements.");
            }


//            Play the note and then release the keys
            for (Integer key : keys) {
                if (key != null) {
                    robot.keyPress(key);
                }
            }
//            Rest after the note has been played.
            Thread.sleep(Long.parseLong(rest));
        }

//        Release keys one last time
        releaseKeys(robot, keys);

        System.out.println("All done");
    }

    private static void releaseKeys(Robot robot, List<Integer> keys) {
        for (Integer key: keys) {
            robot.keyRelease(key);
        }
    }

    private static void initTrumpet(Robot robot) throws InterruptedException {
        robot.keyPress(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_I);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_I);

        robot.keyPress(KeyEvent.VK_Z);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyPress(KeyEvent.VK_J);
        robot.keyPress(KeyEvent.VK_K);
        robot.keyPress(KeyEvent.VK_L);
        Thread.sleep(40);
        robot.keyRelease(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_J);
        robot.keyRelease(KeyEvent.VK_K);
        robot.keyRelease(KeyEvent.VK_L);
        Thread.sleep(200);
    }
}
