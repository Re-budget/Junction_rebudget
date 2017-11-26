package nordea.junction.rebudget.ui.accountlist;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nordea.junction.rebudget.R;
import nordea.junction.rebudget.model.Account;

/**
 * Adapter that helps to show the Accounts in the list order.
 *
 * Created by Bel on 25/11/2017.
 */

public class AccountListAdapter extends RecyclerView.Adapter<AccountListAdapter.ViewHolder> {

    private Context mContext;
    private List<Account> accountList;
    private OnCopyIbanItemClickListener mOnCopyIbanItemClickListener;

    public interface OnCopyIbanItemClickListener{
        void copyIbanNumber();
    }

    public AccountListAdapter(Context context) {
        this.mContext = context;
        this.accountList = new ArrayList<>();
    }

    public void setOnCopyIbanItemClickListener(OnCopyIbanItemClickListener onCopyIbanItemClickListener){
        mOnCopyIbanItemClickListener = onCopyIbanItemClickListener;
    }

    /**
     * ViewHolder for the Card View of the Advice List.
     * Contains references for the views inside the card.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvAccountName;
        public TextView tvAccountIban;
        public TextView tvCurrency;
        public TextView tvBalance;
        public ImageView ivCopyIcon;


        public ViewHolder(View itemView) {
            super(itemView);
            tvAccountName = itemView.findViewById(R.id.tvAccountName);
            tvAccountIban = itemView.findViewById(R.id.tvAccountIban);
            tvCurrency = itemView.findViewById(R.id.tvCurrency);
            tvBalance = itemView.findViewById(R.id.tvBalance);
            ivCopyIcon = itemView.findViewById(R.id.ivCopyIcon);
        }
    }

    @Override
    public AccountListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View root = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_account_card, parent, false);

        final AccountListAdapter.ViewHolder viewHolder = new AccountListAdapter.ViewHolder(root);
        viewHolder.ivCopyIcon.setOnClickListener((view)-> {
           if (mOnCopyIbanItemClickListener != null) mOnCopyIbanItemClickListener.copyIbanNumber();
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AccountListAdapter.ViewHolder holder, int position) {
        final Account advice = accountList.get(position);

        final Drawable drawableCopy = ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.ic_content_copy_black_24dp, null);
        drawableCopy.setColorFilter(ContextCompat.getColor(mContext, R.color.colorOppositePrimary), PorterDuff.Mode.SRC_ATOP);

        holder.ivCopyIcon.setImageDrawable(drawableCopy);

        holder.tvAccountName.setText(advice.getAccountName());
        holder.tvAccountIban.setText(advice.getAccountIban());
        holder.tvBalance.setText(advice.getAccountBalance());
        holder.tvCurrency.setText(advice.getCurrency());


    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
        notifyDataSetChanged();
    }
}
