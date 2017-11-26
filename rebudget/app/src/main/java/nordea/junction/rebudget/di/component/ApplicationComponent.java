package nordea.junction.rebudget.di.component;

import javax.inject.Singleton;

import dagger.Component;
import nordea.junction.rebudget.ui.graph.GraphFragment;
import nordea.junction.rebudget.ui.main.MainActivity;
import nordea.junction.rebudget.di.module.AppModule;

/**
 * Application Component of Dagger2.
 *
 * Created by Bel on 24/11/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(GraphFragment graphFragment);

}
