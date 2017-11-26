package nordea.junction.rebudget.ui.graph;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

import javax.inject.Inject;

import nordea.junction.rebudget.App;
import nordea.junction.rebudget.R;
import nordea.junction.rebudget.preferences.LocalSharedPreferences;

import static nordea.junction.rebudget.utils.Constants.PREF_GOAL_VALUE;

/**
 * Basic UI Fragment for Graph.
 *
 * Created by Bel on 24/11/2017.
 */

public class GraphFragment extends MvpAppCompatFragment implements GraphView{

    private static final String TAG = "GraphFragment";

    @Inject
    LocalSharedPreferences mSharedPreferences;

    @InjectPresenter
    GraphPresenter mGraphPresenter;

    private ProgressBar mProgressBar;
    private LineChart mChart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_graph, container, false);
        mProgressBar = root.findViewById(R.id.progressBar);
        mChart = root.findViewById(R.id.lineChart);
        App.getApplicationComponent().inject(this);
        return root;
    }

    @Override
    public void showGraph() {
        Log.i(TAG, "showGraph: ");
        mProgressBar.setVisibility(View.GONE);
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(false);

        // enable scaling and dragging
        mChart.setDragEnabled(false);
        mChart.setScaleEnabled(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);


        Log.d(TAG, "mSharedPreferences.getData : " + mSharedPreferences.getData(PREF_GOAL_VALUE));

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        addGoalLine(leftAxis);
        leftAxis.setAxisMaximum(mSharedPreferences.getData(PREF_GOAL_VALUE) + 35f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        // add data
        setData(30);

        mChart.animateX(2500);

        // refreshing the drawing
         mChart.invalidate();
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void updateData() {
        mChart.invalidate();
    }

    /**
     * Setting the graph on the with Mock data for now.
     * This data is coming from backend. Does not saved to the database.
     * @param count of weeks to create progressive data set
     */
    private void setData(int count) {

        int goalValue = mSharedPreferences.getData(PREF_GOAL_VALUE);
        Log.e(TAG, "setData: " + goalValue);

        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 0));
        for (int i = 1; i < count; i++) {

            float val = (float) (Math.random() * 10) + values.get(i-1).getY();
            Log.e(TAG, "val 1: " + val);

            if(val >= goalValue) val = goalValue;
            Log.e(TAG, "val 2: " + val);

            values.add(new Entry(i, val));
        }

        LineDataSet lineDataSet;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            lineDataSet = (LineDataSet)mChart.getData().getDataSetByIndex(0);
            lineDataSet.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            lineDataSet = new LineDataSet(values, "");

            lineDataSet.setColor(ContextCompat.getColor(getContext(), R.color.green));
            lineDataSet.setCircleColor(ContextCompat.getColor(getContext(), R.color.green));
            lineDataSet.setDrawIcons(false);
            lineDataSet.setLineWidth(2f);
            lineDataSet.setCircleRadius(1f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setDrawFilled(true);
            lineDataSet.disableDashedLine();

            mChart.setDescription(new Description());    // Hide the description
            mChart.getAxisLeft().setDrawGridLines(false);
            mChart.getAxisLeft().setDrawLabels(false);
            mChart.getAxisRight().setDrawGridLines(false);
            mChart.getAxisRight().setDrawLabels(false);
            mChart.getXAxis().setDrawGridLines(false);
            mChart.getXAxis().setDrawLabels(false);
            mChart.getLegend().setEnabled(false);

            lineDataSet.setDrawValues(false);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                final Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_add_black_24dp);
                lineDataSet.setFillDrawable(drawable);
            }
            else {
                lineDataSet.setFillColor(Color.BLACK);
            }


            ArrayList<Entry> values2 = new ArrayList<>();
            values2.add(new Entry(values.get(20).getX(), values.get(20).getY()));
            for (int i = 29; i >= 20; i--) {

                float val = (float) (Math.random() * 10) + values.get(i).getY();

                Log.d(TAG, "Golden Values : " + val);
                values2.add(new Entry(values.get(20).getX() + i, val));
            }

            LineDataSet lineDataSet2;

            lineDataSet2 = new LineDataSet(values2, "Predicted");

            lineDataSet2.setColor(ContextCompat.getColor(getContext(), R.color.colorOppositePrimary));
            lineDataSet2.setCircleColor(ContextCompat.getColor(getContext(), R.color.colorOppositePrimary));
            lineDataSet2.setDrawIcons(false);
            lineDataSet2.setLineWidth(2f);
            lineDataSet2.setCircleRadius(1f);
            lineDataSet2.setDrawCircleHole(false);
            lineDataSet2.setDrawFilled(true);
            lineDataSet2.disableDashedLine();

            lineDataSet2.setDrawValues(false);

            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                final Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_add_black_24dp);
                lineDataSet2.setFillDrawable(drawable);
            }
            else {
                lineDataSet2.setFillColor(Color.BLACK);
            }

            final ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(lineDataSet); // add the datasets
//            dataSets.add(lineDataSet2);

            // create a data object with the datasets
            final LineData data = new LineData(lineDataSet/*, lineDataSet2*/);

            // set data
            mChart.setData(data);

//            final ArrayList<ILineDataSet> dataSets2 = new ArrayList<>();
//            dataSets.add(lineDataSet2); // add the datasets

            // create a data object with the datasets
//            final LineData data2 = new LineData(dataSets2);
//            mChart.setData(data2);
        }
    }

    private void addGoalLine(YAxis leftAxis){
        int goalValue = mSharedPreferences.getData(PREF_GOAL_VALUE);
        if(goalValue != 0) {
            LimitLine goalLine = new LimitLine(150f, "Goal");
            goalLine.setLineWidth(2f);
            goalLine.setLineColor(ContextCompat.getColor(getContext(), R.color.colorOppositePrimary));
            goalLine.enableDashedLine(10f, 10f, 0f);
            goalLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            goalLine.setTextSize(10f);
            leftAxis.addLimitLine(goalLine);
        }
    }
}
