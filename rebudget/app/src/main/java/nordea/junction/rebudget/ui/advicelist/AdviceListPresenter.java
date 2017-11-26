package nordea.junction.rebudget.ui.advicelist;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.viewstate.MvpViewState;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nordea.junction.rebudget.model.Advice;

/**
 * Presenter to tell what to do and when.
 *
 * Created by Bel on 24/11/2017.
 */

@InjectViewState
public class AdviceListPresenter extends MvpPresenter<AdviceListView> {

    private static final String TAG = "AdviceListPresenter";

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        Log.i(TAG, "onFirstViewAttach: ");
        getViewState().showLoading();
        getViewState().showAdviceList(createAdviceList());
    }

    @Override
    public void attachView(AdviceListView view) {
        super.attachView(view);
        Log.i(TAG, "attachView()" + view.toString());
    }

    @Override
    public void detachView(AdviceListView view) {
        super.detachView(view);
        Log.i(TAG, "detachView: " + view.toString());
    }

    @Override
    public void destroyView(AdviceListView view) {
        super.destroyView(view);
        Log.i(TAG, "destroyView: " + view.toString());
    }

    @Override
    public Set<AdviceListView> getAttachedViews() {
        Log.i(TAG, "getAttachedViews: ");
        return super.getAttachedViews();
    }

    @Override
    public AdviceListView getViewState() {
        Log.i(TAG, "getViewState: ");
        return super.getViewState();
    }

    @Override
    public boolean isInRestoreState(AdviceListView view) {
        Log.i(TAG, "isInRestoreState: " + view.toString());
        return super.isInRestoreState(view);
    }

    @Override
    public void setViewState(MvpViewState<AdviceListView> viewState) {
        super.setViewState(viewState);
        Log.i(TAG, "setViewState: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    private List<Advice> createAdviceList(){
        List<Advice> adviceList = new ArrayList<>();
        for(int i = 1; i <=7; i++){
            Advice advice = new Advice("" + i);
            advice.setId(i);
            adviceList.add(advice);
        }
        return adviceList;
    }

}
