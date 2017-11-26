package nordea.junction.rebudget;

import android.app.Application;

import nordea.junction.rebudget.di.component.ApplicationComponent;
import nordea.junction.rebudget.di.component.DaggerApplicationComponent;
import nordea.junction.rebudget.di.module.AppModule;

/**
 * Basic Application class for the app.
 *
 * Created by Bel on 24/11/2017.
 */

public class App extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

}
