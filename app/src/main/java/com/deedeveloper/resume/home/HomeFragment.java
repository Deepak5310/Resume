package com.deedeveloper.resume.home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.deedeveloper.resume.R;

public class HomeFragment extends Fragment {

    ImageButton callButton, emailButton, instaButton;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home, container, false);

        callButton = myView.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel: +919521203181"));
                startActivity(intent);
            }
        });

        emailButton = myView.findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setType("message/rfc822")
                        .setData(Uri.parse("mailto: mrdkjangid@gmail.com"))
                        .setPackage("com.google.android.gm");
                startActivity(intent);
            }

//            private PackageManager getPackageManager() {
//                return null;
//            }
        });
        instaButton = myView.findViewById(R.id.instaButton);
        instaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.instagram.android");
//                startActivity(intent);
                Uri uri = Uri.parse("http://instagram.com/mr.deep.z");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/mr.deep.z")));
                }
            }
        });
        return myView;
    }
}
