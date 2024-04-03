package filrouge.app.cars;

import android.app.Application;
import android.content.Context;

/*
* auteur : TORRI Clara et ESSALAH Sabra
* modifié par : clara
* classe qui permet de récupérer le contexte de l'application
*/

public class CarsApp extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    /*retourne le contexte de l'application*/
    public static Context getContext() {
        return context;
    }
}
