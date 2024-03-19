package filrouge.app;

import android.app.Application;
import android.content.Context;

/*
 * classe qui permet de créer un objet Application
 */

public class CarsApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
