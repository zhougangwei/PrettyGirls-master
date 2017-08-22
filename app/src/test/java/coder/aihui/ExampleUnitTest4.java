package coder.aihui;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest4 {

    RxPresenter rxPresenter;

    @Before
    public void setUp() throws Exception {
        rxPresenter = new RxPresenter();
        RxTools.asyncToSync();
    }

    @Test
    public void addition_isCorrect() throws Exception {

        ArrayList<String> arr = new ArrayList();
        arr.add("1");
        arr.add("2");
        arr.add("3");
        arr.add("4");
        arr.add("5");
        arr.add("6");
        arr.add("7");
        arr.add("8");

        rxPresenter.testRxJava(arr);
    }


}