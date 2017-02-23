package es.esy.vivekrajendran.newsapi.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import es.esy.vivekrajendran.newsapi.R;


public class DevDialog extends DialogFragment {
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.layout_circular, container);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getDialog().getWindow() != null) {
            Log.i("TAG", "onViewCreated: ");
            getDialog().getWindow().setBackgroundDrawableResource(R.drawable.frame);
        }
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void initViews(View view) {
        ImageView profilePic = (ImageView) view.findViewById(R.id.circular_profile_pic);
        Glide.with(context)
                .load("https://avatars2.githubusercontent.com/u/14991281?v=3&s=460")
                .centerCrop()
                .placeholder(R.drawable.ic_account_circle_black_24px)
                .into(profilePic);
    }
}
