package nordea.junction.rebudget.ui.main;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import nordea.junction.rebudget.App;
import nordea.junction.rebudget.R;
import nordea.junction.rebudget.api.RestApi;
import nordea.junction.rebudget.preferences.LocalSharedPreferences;
import nordea.junction.rebudget.ui.graph.GraphFragment;

import static nordea.junction.rebudget.utils.Constants.ICON_ADD_GOAL;
import static nordea.junction.rebudget.utils.Constants.PREF_GOAL_VALUE;

/**
 * MainActivity for UI to show the data.
 *
 * Created by Bel on 24/11/2017.
 * */

public class MainActivity extends MvpAppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";

    @InjectPresenter
    MainPresenter mMainPresenter;

    @Inject
    RestApi api;

    @Inject
    LocalSharedPreferences mSharedPreferences;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inject Dependency Injection
        App.getApplicationComponent().inject(this);

        getSupportActionBar().hide();

        // Init Fab
        fab = findViewById(R.id.fabAddGoal);

        final Drawable drawableAddGoal = getResources().getDrawable(ICON_ADD_GOAL);
        drawableAddGoal.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
        fab.setBackgroundDrawable(drawableAddGoal);

        fab.setOnClickListener((v) -> {

            MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                    .title("Set up a goal")
                    .titleColor(Color.WHITE)
                    .customView(R.layout.dialog_view, false)
                    .backgroundColorRes(R.color.green)
                    .negativeText("Cancel")
                    .negativeColor(Color.WHITE)
                    .positiveText("OK")
                    .positiveColor(Color.WHITE)
                    .onPositive((dialog, which) -> {
                        final EditText etGoal = dialog.getCustomView().findViewById(R.id.etGoalValue);

                        final int iGoalValue = Integer.parseInt(etGoal.getText().toString());
                        mSharedPreferences.putData(PREF_GOAL_VALUE, iGoalValue);
                        Log.d(TAG, "PREF_GOAL_VALUE " + (iGoalValue));
                    });

            int goalValue = mSharedPreferences.getData(PREF_GOAL_VALUE);

            MaterialDialog materialDialog = builder.build();
            final EditText etGoal = materialDialog.getCustomView().findViewById(R.id.etGoalValue);
            if(goalValue != 0){
                etGoal.setText(String.valueOf(goalValue));
            }

            materialDialog.show();
        });

        Log.i(TAG, "onCreate: GOAL is " + mSharedPreferences.getData("GOAL"));

    }

    @Override
    public void openProfilePage() {

    }

    @Override
    public void refreshGraph(){
        Log.d(TAG, "refreshGraph() ");
        ((GraphFragment) getSupportFragmentManager().findFragmentById(R.id.savingGraphFragment)).showGraph();

    }

    @Override
    public void showAdviceList() {
    }



}
