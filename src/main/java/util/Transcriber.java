package util;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxwoo
 * Date: 3/26/14
 * Time: 12:50 PM
 * just because i wrote this code don't mean it works
 * cross your fingers and run the tests!
 */
public class Transcriber {
    public static List<Integer> transcribe(String note) throws InvalidArgumentException {
        List<Integer> keys = new LinkedList<>();
        switch(note) {
            case "C1": {
                keys.add(KeyEvent.VK_DOWN);
                keys.add(KeyEvent.VK_D);
                return keys;
            }
            case "D1": {
                keys.add(KeyEvent.VK_DOWN);
                keys.add(KeyEvent.VK_A);
                keys.add(KeyEvent.VK_D);
                return keys;
            }
            case "E1": {
                keys.add(KeyEvent.VK_DOWN);
                keys.add(KeyEvent.VK_A);
                keys.add(KeyEvent.VK_S);
                return keys;
            }
            case "F1": {
                keys.add(KeyEvent.VK_DOWN);
                keys.add(KeyEvent.VK_A);
                return keys;
            }
            case "G1": {
                keys.add(KeyEvent.VK_DOWN);
                keys.add(KeyEvent.VK_C);
                return keys;
            }
            case "A1": {
                keys.add(KeyEvent.VK_DOWN);
                keys.add(KeyEvent.VK_Z);
                keys.add(KeyEvent.VK_X);
                return keys;
            }
            case "B1": {
                keys.add(KeyEvent.VK_DOWN);
                keys.add(KeyEvent.VK_X);
                return keys;
            }
            case "C2": {
                keys.add(KeyEvent.VK_D);
                return keys;
            }
            default: {
                System.err.println(note + "is an invalid note.");
            }
        }
        return null;
    }
}
