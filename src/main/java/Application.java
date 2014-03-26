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
        while ((line = br.readLine()) != null) {
//            C1,200 gets split into C1 and 200
            String split[] = line.split(",");
            String note = split[0];
            String rest = split[1];

//            Transcribe the note into the keystrokes needed
            List<Integer> keys = Transcriber.transcribe(note);
//            Play the note and then release the keys
            for (Integer key : keys) {
                robot.keyPress(key);
            }
            Thread.sleep(40);
            for (Integer key: keys) {
                robot.keyRelease(key);
            }

//            Rest after the note has been played.
            Thread.sleep(Long.parseLong(rest));
        }

////        Test keystrokes
//        List<Integer> keys = Transcriber.transcribe("1C");
//        for (Integer key : keys) {
//            robot.keyPress(key);
//        }

        System.out.println("All done");
    }

    private static void initTrumpet(Robot robot) throws InterruptedException {
        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(200);
        robot.keyRelease(KeyEvent.VK_S);

        robot.keyPress(KeyEvent.VK_Z);
        robot.keyPress(KeyEvent.VK_X);
        robot.keyPress(KeyEvent.VK_C);
        Thread.sleep(40);
        robot.keyRelease(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_X);
        robot.keyRelease(KeyEvent.VK_C);
        Thread.sleep(200);
    }
}
