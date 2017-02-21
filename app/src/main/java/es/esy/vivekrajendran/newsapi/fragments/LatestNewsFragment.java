package es.esy.vivekrajendran.newsapi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.esy.vivekrajendran.newsapi.R;


public class LatestNewsFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, true);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyl_frag_latest);
        return mRecyclerView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CursorLoader cursorLoader = new CursorLoader(getContext());
        //cursorLoader.set
    }
}
