package coder.aihui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import coder.aihui.ui.main.DownView;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest2 {


   @Mock
   DownView mDownView;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
       // Mockito.verify()
       // Mockito.when(mDownView.).thenReturn(true);
    }

    @Test
    public void addition_isCorrect() throws Exception {
/*
        //Context appContext = InstrumentationRegistry.getTargetContext;
        Context appContext = InstrumentationRegistry.getTargetContext
        mDaoSession = MyApplication.getIntstance().getDaoSession();
        DownPresenter downPresenter=  new DownPresenter(mDownView);*/

    /*   DownPresenter downPresenter=  new DownPresenter(mDownView);

        downPresenter.gotoDown(1);
        Mockito.verify(mDownView).showProgress(2,1);
        Mockito.verify(mDownView).showSuccess(1);*/
    }


}