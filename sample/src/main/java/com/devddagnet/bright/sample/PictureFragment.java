package com.devddagnet.bright.sample;

import com.devddagnet.bright.lib.Bright;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static com.devddagnet.bright.lib.Bright.Config;

public class PictureFragment extends Fragment implements Transformation, Callback {

    private static final String TAG = "BRIGHT";

    TextView[] labels     = new TextView[5];
    int[] luminances = new int[5];
    ImageView mPicture;
    Bright.Luminance bright = Bright.setup(Config.PERCEIVED | Config.PERFORMANCE);

    public PictureFragment() {
    }

    public static PictureFragment newInstance(String url) {
        PictureFragment fragment = new PictureFragment();
        Bundle bundle = new Bundle();

        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_picture, container, false);

        labels[0] = (TextView) rootView.findViewById(R.id.label);
        labels[1] = (TextView) rootView.findViewById(R.id.label2);
        labels[2] = (TextView) rootView.findViewById(R.id.label3);
        labels[3] = (TextView) rootView.findViewById(R.id.label4);
        labels[4] = (TextView) rootView.findViewById(R.id.label5);
        mPicture = (ImageView) rootView.findViewById(R.id.picture);

        final String url = getArguments().getString("url");

        Picasso.with(getActivity()).load(url).config(Bitmap.Config.RGB_565)
                .fit().centerCrop()
                .transform(this)
                .into(mPicture, this);
        return rootView;
    }

    @Override
    public void onSuccess() {
        Log.d(TAG, "Success");

        for (int i = 0; i < labels.length; ++i) {
            int luminance = luminances[i];
            TextView label = labels[i];
            final int textColor = bright.isBright(luminance) ? Color.BLACK
                    : Color.WHITE;

            label.setTextColor(textColor);
            label.setText("Bright (" + (int) luminance + ")");
        }
    }

    @Override
    public void onError() {
        Log.d(TAG, "Error");
    }

    @Override
    public Bitmap transform(Bitmap source) {
        for (int i = 0; i < labels.length; ++i) {
            TextView label = labels[i];

            int x = (int) label.getX();
            int y = (int) label.getY();
            int width = label.getWidth();
            int height = label.getHeight();

            Bitmap dest = Bitmap.createBitmap(source, x, y, width, height);
            luminances[i] = bright.brightness(dest);
            dest.recycle();
        }
        return source;
    }

    @Override
    public String key() {
        return TAG;
    }
}
