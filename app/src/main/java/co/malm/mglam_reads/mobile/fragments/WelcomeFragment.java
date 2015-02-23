package co.malm.mglam_reads.mobile.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import co.malm.mglam_reads.mobile.activities.R;

/**
 * Created by marlonlom on 22/02/15.
 */
public class WelcomeFragment extends Fragment {
    private ImageView welcomeImage;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);

        this.welcomeImage = (ImageView) rootView.findViewById(R.id.welcome_image);
        welcomeImage.setImageResource(R.drawable.img_fashion_500);

        return rootView;
    }
}
