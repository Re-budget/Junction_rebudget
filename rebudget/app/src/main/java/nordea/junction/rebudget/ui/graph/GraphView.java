package nordea.junction.rebudget.ui.graph;

import com.arellomobile.mvp.MvpView;

/**
 * View that tells to the Graph UI Activity what it can do and what to show.
 *
 * Created by Bel on 24/11/2017.
 */

public interface GraphView extends MvpView {

    void showGraph();
    void showLoading();
    void showEmpty();
    void updateData();
}
