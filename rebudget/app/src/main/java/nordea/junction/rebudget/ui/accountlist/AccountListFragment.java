package nordea.junction.rebudget.ui.accountlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import nordea.junction.rebudget.R;
import nordea.junction.rebudget.model.Account;

/**
 *
 * Created by Bel on 25/11/2017.
 */

public class AccountListFragment extends MvpAppCompatFragment implements AccountListView {

    @InjectPresenter
    AccountListPresenter mPresenter;

    private AccountListAdapter mAccountListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_account_list, container, false);
        final RecyclerView mRecyclerView = root.findViewById(R.id.accountList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAccountListAdapter = new AccountListAdapter(getContext());
        mAccountListAdapter.setOnCopyIbanItemClickListener(new AccountListAdapter.OnCopyIbanItemClickListener() {
            @Override
            public void copyIbanNumber() {
                Toast.makeText(getContext(), "IBAN name copied", Toast.LENGTH_LONG).show();
            }
        });

        mRecyclerView.setAdapter(mAccountListAdapter);

        return root;
    }

    @Override
    public void showAccountList(List<Account> accountList) {
        mAccountListAdapter.setAccountList(accountList);
    }
}
