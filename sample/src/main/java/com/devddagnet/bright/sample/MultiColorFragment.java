package com.devddagnet.bright.sample;

import com.devddagnet.bright.lib.Bright;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.devddagnet.bright.lib.Bright.Config;

public class MultiColorFragment extends Fragment {

    public MultiColorFragment() {
    }

    public static MultiColorFragment newInstance() {
        return new MultiColorFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_multicolor, container, false);
        int childCount = rootView.getChildCount();

        for (int i = 0; i < childCount; i++) {
            TextView textView = (TextView) rootView.getChildAt(i);

            final int color = ((ColorDrawable) textView.getBackground()).getColor();
            int luminance = Bright.setup(Config.RELATIVE).brightness(color);
            boolean isBright = Bright.getInstance().isBright(luminance);

            textView.setText("Bright (" + luminance + ")");
            textView.setTextColor(isBright ? Color.BLACK : Color.WHITE);
        }
        return rootView;
    }
}
