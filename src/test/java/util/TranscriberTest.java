package util;

import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jxwoo
 * Date: 3/26/14
 * Time: 1:11 PM
 * just because i wrote this code don't mean it works
 * cross your fingers and run the tests!
 */
public class TranscriberTest {
    @Test
    public void testTranscribe() throws Exception {
        List<Integer> keys = Transcriber.transcribe("D1");
        assert(keys.get(0) == KeyEvent.VK_DOWN);
        assert(keys.get(1) == KeyEvent.VK_D);
    }
}
