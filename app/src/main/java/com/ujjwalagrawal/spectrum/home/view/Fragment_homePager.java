package com.ujjwalagrawal.spectrum.home.view;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ujjwalagrawal.spectrum.R;

/**
 * Created by hp-p on 19-Jan-18.
 */

public class Fragment_homePager extends Fragment{
    private static final String TAG = Fragment_homePager.class.getSimpleName();
    private ImageView highlightsView;
    private ImageView placeholderView;
    Context context;
//    public String name;

    public Fragment_homePager() {}

    public static Fragment_homePager newInstance(int image_resid) {

        Bundle args = new Bundle();
        args.putInt("IMAGERES",image_resid);
//        args.putString("name",name);

        Fragment_homePager fragment = new Fragment_homePager();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int image_resid = getArguments().getInt("IMAGERES");
//        name = getArguments().getString("name");

        View v = inflater.inflate(R.layout.home_image_view, container, false);
        context=getContext();
        highlightsView = (ImageView) v.findViewById(R.id.large_image);
        placeholderView = (ImageView) v.findViewById(R.id.placeholder_view);

        highlightsView.setVisibility(View.VISIBLE);
        placeholderView.setVisibility(View.INVISIBLE);
        highlightsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
                builder.setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                builder.setExitAnimations(context, android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse("https://www.facebook.com/spectrum.iitism/"));

            }
        });
//        Log.d(TAG,"Name: " + name);
        Log.d(TAG,"ImageRes ID: " + image_resid);

        Log.d(TAG, "onCreateView() called with: inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");

        highlightsView.setImageResource(image_resid);


        return v;
    }

    public void reset() {
        if(highlightsView!=null) {

            highlightsView.setVisibility(View.VISIBLE);
            placeholderView.setVisibility(View.INVISIBLE);

            Log.d(TAG, "reset() called");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(highlightsView!=null) {
            highlightsView.setVisibility(View.VISIBLE);
            placeholderView.setVisibility(View.INVISIBLE);

            Log.d(TAG, "onResume: called");
        }
    }


}
