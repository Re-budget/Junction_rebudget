package nordea.junction.rebudget.ui.advicelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import nordea.junction.rebudget.R;
import nordea.junction.rebudget.model.Advice;

/**
 * Basic UI Fragment for List of Advices.
 *
 * Created by Bel on 24/11/2017.
 */

public class AdviceListFragment extends MvpAppCompatFragment implements AdviceListView {

    private static final String TAG = "AdviceListFragment";

    @InjectPresenter
    AdviceListPresenter mAdviceListPresenter;

    private ProgressBar mProgressBar;
    private AdviceListAdapter mAdviceListAdapter;

    /**
     * Creating a new instance of the AdviceListFragment with some additional options/filters
     * that should be shown on the UI.
     * */
    public static AdviceListFragment newInstance() {

        final Bundle args = new Bundle();

        final AdviceListFragment fragment = new AdviceListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_advice_list, container, false);
        mProgressBar = root.findViewById(R.id.progressBar);

        final RecyclerView mRecyclerView = root.findViewById(R.id.adviceList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdviceListAdapter = new AdviceListAdapter(getContext());
        mRecyclerView.setAdapter(mAdviceListAdapter);

        return root;
    }

    @Override
    public void showAdviceList(List<Advice> adviceList) {
        Log.i(TAG, "showAdviceList: ");
        mProgressBar.setVisibility(View.GONE);
        mAdviceListAdapter.setAdviceList(adviceList);
    }

    @Override
    public void showLoading() {
        Log.i(TAG, "showLoading: ");
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {
        Log.i(TAG, "showEmpty: ");
    }
}
