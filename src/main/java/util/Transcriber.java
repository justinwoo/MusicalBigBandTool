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
        if (note.isEmpty()) {
            keys.add(KeyEvent.VK_Z);
            keys.add(KeyEvent.VK_X);
            keys.add(KeyEvent.VK_C);
            return keys;
        } else {
            char[] chars = note.toCharArray();
            switch(chars[0]) {
                case 'C': {
                    keys.add(KeyEvent.VK_D);
                    break;
                }
                case 'D': {
                    keys.add(KeyEvent.VK_A);
                    keys.add(KeyEvent.VK_D);
                    break;
                }
                case 'E': {
                    keys.add(KeyEvent.VK_A);
                    keys.add(KeyEvent.VK_S);
                    break;
                }
                case 'F': {
                    keys.add(KeyEvent.VK_A);
                    break;
                }
                case 'G': {
                    keys.add(KeyEvent.VK_C);
                    break;
                }
                case 'A': {
                    keys.add(KeyEvent.VK_Z);
                    keys.add(KeyEvent.VK_X);
                    break;
                }
                case 'B': {
                    keys.add(KeyEvent.VK_X);
                    break;
                }
            }
            switch(chars[1]) {
                case '1':
                    keys.add(KeyEvent.VK_DOWN);
                    break;
                case '3':
                    keys.add(KeyEvent.VK_UP);
                    break;
            }
            return keys;
        }
    }

    public static List<Integer> translateTo2P(List<Integer> keys) {
        for (int i = 0; i < keys.size(); i++) {
            Integer key = keys.get(i);
            switch(key) {
                case KeyEvent.VK_A:
                    keys.set(i, KeyEvent.VK_U);
                    break;
                case KeyEvent.VK_S:
                    keys.set(i, KeyEvent.VK_I);
                    break;
                case KeyEvent.VK_D:
                    keys.set(i, KeyEvent.VK_O);
                    break;
                case KeyEvent.VK_Z:
                    keys.set(i, KeyEvent.VK_J);
                    break;
                case KeyEvent.VK_X:
                    keys.set(i, KeyEvent.VK_K);
                    break;
                case KeyEvent.VK_C:
                    keys.set(i, KeyEvent.VK_L);
                    break;
                case KeyEvent.VK_UP:
                    keys.set(i, KeyEvent.VK_NUMPAD8);
                    break;
                case KeyEvent.VK_LEFT:
                    keys.set(i, KeyEvent.VK_NUMPAD4);
                    break;
                case KeyEvent.VK_DOWN:
                    keys.set(i, KeyEvent.VK_NUMPAD5);
                    break;
                case KeyEvent.VK_RIGHT:
                    keys.set(i, KeyEvent.VK_NUMPAD6);
                    break;
            }
        }
        return keys;
    }
}
