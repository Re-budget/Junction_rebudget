package nordea.junction.rebudget.ui.advicelist;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import nordea.junction.rebudget.model.Advice;

/**
 * View that tells to the AdviceList UI Activity what it can do and show.
 *
 * Created by Bel on 24/11/2017.
 */

public interface AdviceListView extends MvpView {

    void showAdviceList(List<Advice> adviceList);
    void showLoading();
    void showEmpty();
}
