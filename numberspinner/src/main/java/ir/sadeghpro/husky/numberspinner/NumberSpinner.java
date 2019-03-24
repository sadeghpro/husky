package ir.sadeghpro.husky.numberspinner;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.button.MaterialButton;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberSpinner extends LinearLayout {

    int number = 1;

    public NumberSpinner(Context context) {
        super(context);
        init(context);
    }

    public NumberSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumberSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NumberSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        Resources res = context.getResources();
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        //<editor-fold desc="Color setup">
        int[][] backgroundStates = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
        };

        int[] backgroundColors = new int[] {
                res.getColor(R.color.btnAddBackground),
                res.getColor(R.color.btnAddBackgroundDisable)
        };

        ColorStateList background = new ColorStateList(backgroundStates, backgroundColors);

        int[][] iconStates = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
        };

        int[] iconColors = new int[] {
                res.getColor(R.color.btnAddIconTint),
                res.getColor(R.color.btnAddBackgroundDisable)
        };

        ColorStateList icon = new ColorStateList(iconStates, iconColors);
        //</editor-fold>

        //<editor-fold desc="LayoutParams">
        LayoutParams btnParams = new LayoutParams(res.getDimensionPixelOffset(R.dimen.btnWidth), res.getDimensionPixelOffset(R.dimen.btnHeight));
        LayoutParams txtParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        //</editor-fold>

        //<editor-fold desc="Button sub settings">
        final MaterialButton btnSub = new MaterialButton(context);
        btnSub.setIconResource(R.drawable.ic_sub);
        btnSub.setIconGravity(MaterialButton.ICON_GRAVITY_TEXT_START);
        btnSub.setIconPadding(0);
        btnSub.setCornerRadius(0);
        btnSub.setIconTint(icon);
        btnSub.setBackgroundTintList(background);
        btnSub.setIconSize(res.getDimensionPixelSize(R.dimen.btnIconSize));
        btnSub.setLayoutParams(btnParams);
        btnSub.setEnabled(false);
        //</editor-fold>

        //<editor-fold desc="TextView settings">
        final TextView txtNumber = new TextView(context);
        txtNumber.setLayoutParams(txtParams);
        txtNumber.setText(R.string.defaultNumber);
        txtNumber.setTextSize(res.getDimensionPixelSize(R.dimen.txtFontSize));
        txtNumber.setTextColor(res.getColorStateList(R.color.txtNumber));
        txtNumber.setGravity(Gravity.CENTER);
        //</editor-fold>

        //<editor-fold desc="Button add settings">
        MaterialButton btnAdd = new MaterialButton(context);
        btnAdd.setIconResource(R.drawable.ic_add);
        btnAdd.setIconGravity(MaterialButton.ICON_GRAVITY_TEXT_START);
        btnAdd.setIconPadding(0);
        btnAdd.setCornerRadius(0);
        btnAdd.setIconTint(icon);
        btnAdd.setBackgroundTintList(background);
        btnAdd.setIconSize(res.getDimensionPixelSize(R.dimen.btnIconSize));
        btnAdd.setLayoutParams(btnParams);
        //</editor-fold>

        //<editor-fold desc="Add views to linear layout">
        addView(btnSub);
        addView(txtNumber);
        addView(btnAdd);
        //</editor-fold>

        //<editor-fold desc="Buttons listener">
        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                number++;
                txtNumber.setText(String.valueOf(number));
                if (number > 1) {
                    btnSub.setEnabled(true);
                }
            }
        });

        btnSub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                number--;
                txtNumber.setText(String.valueOf(number));
                if (number > 1) {
                    btnSub.setEnabled(true);
                } else {
                    btnSub.setEnabled(false);
                }
            }
        });
        //</editor-fold>
    }

    public int getNumber() {
        return number;
    }
}
