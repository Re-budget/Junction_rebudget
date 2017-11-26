package nordea.junction.rebudget.ui.main;

import com.arellomobile.mvp.MvpView;

/**
 * Interface for the MVP View from Moxy library.
 * Tells what an Activity can do.
 *
 * Created by Bel on 24/11/2017.
 */

public interface MainView extends MvpView {

    void refreshGraph();
    void showAdviceList();
    void openProfilePage();
}
