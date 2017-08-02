package coder.aihui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import coder.aihui.data.bean.gen.DaoSession;
import coder.aihui.ui.main.DownPresenter;
import coder.aihui.ui.main.DownView;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest2 {


    @Mock
    DownView downView;
    @Mock
    DaoSession mDaoSession;


    private DownPresenter mDownPresenter;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mDownPresenter = new DownPresenter(downView, mDaoSession);
    }
    @Test
    public void addition_isCorrect() throws Exception {


        mDownPresenter.gotoDown(7);

     /*   RemoteMyDataSource  mRemoteMyDataSource = new RemoteMyDataSource(downView, mDaoSession);


        mRemoteMyDataSource.saveHttpDatas();*/
    }



}