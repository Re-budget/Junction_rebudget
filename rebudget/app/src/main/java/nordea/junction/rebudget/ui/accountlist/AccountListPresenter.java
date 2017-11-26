package nordea.junction.rebudget.ui.accountlist;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import nordea.junction.rebudget.model.Account;

/**
 * Created by Bel on 25/11/2017.
 */

@InjectViewState
public class AccountListPresenter extends MvpPresenter<AccountListView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showAccountList(createAccountList());
    }

    private List<Account> createAccountList(){
        List<Account> accounts = new ArrayList<>();
        final Account account = new Account("Debit", "FI88 8888 8888 8888", "2890", "€");
        final Account account1 = new Account("Credit", "FI99 5555 9999 5555", "888", "€");
        final Account account2 = new Account("ASP account", "FI77 7777 7777 7777", "2500", "€");

        accounts.add(account);
        accounts.add(account1);
        accounts.add(account2);
        return accounts;
    }
}
