package custom.lx.com.customview.base;

import android.app.Application;


/**
 * Created by lx on 2018/1/24.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
//        CrashReport.initCrashReport(getApplicationContext(), "40cb5aabf3", true);
    }
}
