package nordea.junction.rebudget.ui.goldenvalue;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import nordea.junction.rebudget.R;
import nordea.junction.rebudget.ui.profile.ProfileActivity;

import static nordea.junction.rebudget.utils.Constants.ICON_MENU_ACCOUNT_LIST;

/**
 * The UI of the Golden Value screen. How much the user can save each week.
 * The goals can be launched for the users
 * Pretendings for the future, called Golden Value,
 *
 * Created by Bel on 26/11/2017.
 */

public class GoldenValueFragment extends MvpAppCompatFragment implements GoldenValueView{

    @InjectPresenter
    GoldenValuePresenter mGoldenValuePresenter;

    ImageView ivWalletIcon;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_header, container, false);
        ivWalletIcon = root.findViewById(R.id.ivWalletIcon);

        /* Changing colors programmatically, saving apk size by decreasing the amount of icons with different color, optimized */
        final Drawable drawableWallet = getResources().getDrawable(ICON_MENU_ACCOUNT_LIST);
        drawableWallet.setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_ATOP);
        // inserting the green wallet as an icon
        ivWalletIcon.setImageDrawable(drawableWallet);

        ivWalletIcon.setOnClickListener((v)->{
            final Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        });

        return root;
    }




}
