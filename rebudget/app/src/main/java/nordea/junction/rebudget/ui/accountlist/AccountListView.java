package nordea.junction.rebudget.ui.accountlist;

import com.arellomobile.mvp.MvpView;

import java.util.List;

import nordea.junction.rebudget.model.Account;

/**
 * Created by Bel on 25/11/2017.
 */

public interface AccountListView extends MvpView {

    void showAccountList(List<Account> accountList);
}
