package nordea.junction.rebudget.ui.profile;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;

import nordea.junction.rebudget.R;

/**
 * UI page to represent Profile page.
 *
 * Created by Bel on 25/11/2017.
 */

public class ProfileActivity extends MvpAppCompatActivity implements ProfileView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        upArrow.setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    @Override
    public void showAccountList() {

    }
}
