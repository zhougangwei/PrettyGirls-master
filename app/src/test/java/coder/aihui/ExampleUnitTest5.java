package coder.aihui;

import org.junit.Before;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest5 {


    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addition_isCorrect() throws Exception {

    }

    private  void gotoChange(int finalI) {
        for (int i = 0; i <30 ; i++) {
            System.out.println(finalI + Thread.currentThread().getName());
        }
    }


}