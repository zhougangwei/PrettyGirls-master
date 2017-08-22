package coder.aihui;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import coder.aihui.util.GsonUtil;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest6 {




    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addition_isCorrect() throws Exception {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("1", "111");
        jsonObject.put("2", "222");
        jsonObject.put("3", "333");

        JSONArray jsonElements = new JSONArray();
        jsonElements.put(jsonObject);

        ArrayList<JSONObject> objects = new ArrayList<>();


        System.out.println(jsonElements.toString());
        System.out.println(GsonUtil.parseListToJson(objects));


    }

    private void gotoChange(int finalI) {
        for (int i = 0; i < 30; i++) {
            System.out.println(finalI + Thread.currentThread().getName());
        }
    }


}