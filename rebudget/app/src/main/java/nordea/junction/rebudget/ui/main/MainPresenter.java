package nordea.junction.rebudget.ui.main;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.MvpViewState;

import java.util.Set;

/**
 * Presenter class for the Main screen from Moxy library.
 *
 * Created by Bel on 24/11/2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private static final String TAG = "MainPresenter";

    public MainPresenter() {
    }

    @Override
    public void attachView(MainView view) {
        super.attachView(view);
        Log.i(TAG, "attachView()" + view.toString());

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Log.i(TAG, "onFirstViewAttach: ");
    }

    @Override
    public void detachView(MainView view) {
        super.detachView(view);
        Log.i(TAG, "detachView: " + view.toString());
    }

    @Override
    public void destroyView(MainView view) {
        super.destroyView(view);
        Log.i(TAG, "destroyView: " + view.toString());
    }

    @Override
    public Set<MainView> getAttachedViews() {
        Log.i(TAG, "getAttachedViews: ");
        return super.getAttachedViews();
    }

    @Override
    public MainView getViewState() {
        Log.i(TAG, "getViewState: ");
        return super.getViewState();
    }

    @Override
    public boolean isInRestoreState(MainView view) {
        Log.i(TAG, "isInRestoreState: " + view.toString());
        return super.isInRestoreState(view);
    }

    @Override
    public void setViewState(MvpViewState<MainView> viewState) {
        super.setViewState(viewState);
        Log.i(TAG, "setViewState: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

}
