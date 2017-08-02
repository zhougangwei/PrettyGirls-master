package coder.aihui;

import android.content.Context;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {


    @Mock
    Context    mContext;    @Mock
    TextView    mTextView;




    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void addition_isCorrect() throws Exception {

        mTextView.getText();


        mTextView.getText().toString();


    }



}