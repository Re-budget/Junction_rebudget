package nordea.junction.rebudget.ui.profile;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

/**
 *
 *
 * Created by Bel on 25/11/2017.
 */
@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showAccountList();
    }
}
