package nordea.junction.rebudget.ui.advicelist;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import nordea.junction.rebudget.model.Advice;

import static nordea.junction.rebudget.utils.Constants.CATEGORY_COFFEE;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_COMMUNICATION;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_EDUCATION;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_FINANCIAL_EXPENSES;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_FOOD;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_HOUSING;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_COFFEE;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_COMMUNICATION;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_EDUCATION;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_FINANCIAL_EXPENSES;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_FOOD;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_HOUSING;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_LIFE;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_RESTAURANTS;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_TRANSPORTATION;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_ICON_TRAVEL;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_LIFE;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_RESTAURANTS;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_TRANSPORTATION;
import static nordea.junction.rebudget.utils.Constants.CATEGORY_TRAVEL;

/**
 * Adapter to represent the List of Advice Cards.
 *
 * Created by Bel on 24/11/2017.
 */

public class AdviceListAdapter extends RecyclerView.Adapter<AdviceListAdapter.ViewHolder> {

    private Context mContext;
    private List<Advice> adviceList;

    public AdviceListAdapter(Context context) {
        this.mContext = context;
        this.adviceList = new ArrayList<>();
    }

    /**
     * ViewHolder for the Card View of the Advice List.
     * Contains references for the views inside the card.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTvYouSpend;
        public TextView mTvIn;
        public TextView mTvPlace;
        public TextView mTvPercentageIndicator;
        public ImageView ivCategoryIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvYouSpend = itemView.findViewById(R.id.tvYouSpend);
            mTvIn = itemView.findViewById(R.id.tvIn);
            mTvPlace = itemView.findViewById(R.id.tvPlace);
            mTvPercentageIndicator = itemView.findViewById(R.id.tvPercentageIndicator);
            ivCategoryIcon = itemView.findViewById(R.id.categoryIcon);
        }
    }

    @Override
    public AdviceListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_advice_card, parent, false);

        final ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdviceListAdapter.ViewHolder holder, int position) {
        final Advice advice = adviceList.get(position);

        final Drawable drawable = ResourcesCompat.getDrawable(mContext.getResources(), getCategoryIconId(advice.getId()), null);
        holder.ivCategoryIcon.setImageDrawable(drawable);

        int val = (int) (Math.random() * 10) + 10;

        holder.mTvPercentageIndicator.setText(val + "%");
    }

    @Override
    public int getItemCount() {
        return adviceList.size();
    }

    public void setAdviceList(List<Advice> adviceList) {
        this.adviceList = adviceList;
        notifyDataSetChanged();
    }

    public int getCategoryIconId(int id){
        switch (id){
            case CATEGORY_FOOD:
                return CATEGORY_ICON_FOOD;
            case CATEGORY_HOUSING:
                return CATEGORY_ICON_HOUSING;
            case CATEGORY_TRANSPORTATION:
                return CATEGORY_ICON_TRANSPORTATION;
            case CATEGORY_TRAVEL:
                return CATEGORY_ICON_TRAVEL;
            case CATEGORY_LIFE:
                return CATEGORY_ICON_LIFE;
            case CATEGORY_COMMUNICATION:
                return CATEGORY_ICON_COMMUNICATION;
            case CATEGORY_FINANCIAL_EXPENSES:
                return CATEGORY_ICON_FINANCIAL_EXPENSES;
            case CATEGORY_EDUCATION:
                return CATEGORY_ICON_EDUCATION;
            case CATEGORY_RESTAURANTS:
                return CATEGORY_ICON_RESTAURANTS;
            case CATEGORY_COFFEE:
                return CATEGORY_ICON_COFFEE;
            default:
                return CATEGORY_ICON_COFFEE;
        }
    }
}
