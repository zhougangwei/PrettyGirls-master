package coder.aihui.util;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ThreadUtils {

        //使用到线程池

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private static Executor sExecutor = Executors.newSingleThreadExecutor();


    //运行在子线程
    public static void runOnSubThread(Runnable runnable){
        sExecutor.execute(runnable);
    }


    //运行在主线程(UI线程中)
    public static void runOnMainThread(Runnable runnable){
        sHandler.post(runnable);

    }




}
