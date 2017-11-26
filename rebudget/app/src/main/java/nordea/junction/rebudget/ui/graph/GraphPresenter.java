package nordea.junction.rebudget.ui.graph;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 * GraphPresenter to compute what and when the UI should show.
 *
 * Created by Bel on 24/11/2017.
 */

@InjectViewState
public class GraphPresenter extends MvpPresenter<GraphView> {

    private static final String TAG = "GraphPresenter";

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showGraph();
    }


}
