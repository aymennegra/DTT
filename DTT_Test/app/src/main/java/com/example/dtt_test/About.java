package com.example.dtt_test;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class About extends Fragment {
    TextView text,linkddt;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_about,container,false);
        text=rootView.findViewById(R.id.textt);
        linkddt=rootView.findViewById(R.id.linkdtt);

        text.setText("Lorem ipsum dolor sit amet , consecteur adipiscing elit" +
                ", sed do eiusmod tempor incididunt ut labore et dolore magna" +
                "aliqua. Ut enim ad minim veniam,quis nostrud exercitation ullamco laboris" +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in dolor in reprehenderit in" +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint " +
                "occaecat cupidatat non proident, sunt in culpa qui officia" +
                "deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet," +
                "consecteur adipiscing elit, sed do eiusmod tempor incididunt ut labore" +
                "et dolore magna aliqua. Ut enim ad minin veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure" +
                "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla" +
                "pariatur. Exepteur sint occaecat cupidatat non priodent. ");
        linkddt.setMovementMethod(LinkMovementMethod.getInstance());

        return rootView;
    }
}