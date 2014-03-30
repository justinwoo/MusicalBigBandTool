import util.Transcriber;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
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

//        Play the notes in specified file or notes.csv
        String file = "notes.csv";
        if (args.length == 1) {
            file = args[0];
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        List<Integer> prevKeys1P = new ArrayList<>();
        List<Integer> prevKeys2P = new ArrayList<>();
        List<Integer> keys1P = new ArrayList<>();
        List<Integer> keys2P = new ArrayList<>();
        String rest = null;

        prevKeys1P.add(KeyEvent.VK_Z);
        prevKeys1P.add(KeyEvent.VK_X);
        prevKeys1P.add(KeyEvent.VK_C);
        prevKeys2P.add(KeyEvent.VK_J);
        prevKeys2P.add(KeyEvent.VK_K);
        prevKeys2P.add(KeyEvent.VK_L);

        while ((line = br.readLine()) != null) {
//            C1,200 gets split into C1 and 200
            String split[] = line.split(",");
            if (split.length == 2) { // If line only contains one note
                String note = split[0];
                rest = split[1];
//            Transcribe the note into the keystrokes needed
                keys1P = Transcriber.transcribe(note);
            } else if (split.length == 3) { // If line only contains two notes
                String note1 = split[0];
                String note2 = split[1];
                rest = split[2];
                keys1P = Transcriber.transcribe(note1);
                keys2P = Transcriber.translateTo2P(Transcriber.transcribe(note2));
            } else {
                throw new IllegalArgumentException("Line contains more than three elements.");
            }

//            Release keys if needed
//            Set the next release keys if triggered
            if (!prevKeys1P.isEmpty() && !keys1P.isEmpty()) {
                releaseKeys(robot, prevKeys1P);
                prevKeys1P.clear();
                prevKeys1P.addAll(keys1P);
            }
            if (!prevKeys2P.isEmpty() && !keys2P.isEmpty()) {
                releaseKeys(robot, prevKeys2P);
                prevKeys2P.clear();
                prevKeys2P.addAll(keys2P);
            }
//            releaseKeys(robot, keyReleases);
            Thread.sleep(40);

//            Play the note and then release the keys
            pressKeys(robot, keys1P);
            pressKeys(robot, keys2P);

            Thread.sleep(Long.parseLong(rest) - 40);
        }

//        Release keys one last time
        releaseKeys(robot, keys1P);
        releaseKeys(robot, keys2P);

        System.out.println("All done");
    }

    private static void pressKeys(Robot robot, List<Integer> keys) {
        if (keys == null) {
            return;
        }
        for (Integer key : keys) {
            if (key != null) {
                robot.keyPress(key);
            }
        }
    }

    private static void releaseKeys(Robot robot, List<Integer> keys) {
        if (keys == null) {
            return;
        }
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
        Thread.sleep(100);
    }
}
