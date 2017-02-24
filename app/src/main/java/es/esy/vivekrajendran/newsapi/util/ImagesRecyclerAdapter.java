package es.esy.vivekrajendran.newsapi.util;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import es.esy.vivekrajendran.newsapi.R;
import es.esy.vivekrajendran.newsapi.data.NewsContract;
import es.esy.vivekrajendran.newsapi.model.ImageNews;


public class ImagesRecyclerAdapter extends RecyclerView.Adapter<ImagesRecyclerAdapter.NewsHolder> {


    private Context context;
    private ArrayList<ImageNews> imageNewsArrayList;

    public ImagesRecyclerAdapter(Context context, ArrayList<ImageNews> imageNewsArrayList) {
        this.context = context;
        this.imageNewsArrayList = imageNewsArrayList;
    }


    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_image, parent, false);
        return new ImagesRecyclerAdapter.NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        final ImageNews feedItem = imageNewsArrayList.get(position);

        if (!TextUtils.isEmpty(feedItem.getImageUrl())) {
            Glide.with(context).load(feedItem.getImageUrl())
                    .error(R.drawable.ic_account_circle_black_24px)
                    .placeholder(R.drawable.ic_account_circle_black_24px)
                    .into(holder.newsImage);
        }

        holder.headline.setText(Html.fromHtml(feedItem.getTitle()));
    }


    @Override
    public int getItemCount() {
        if (imageNewsArrayList == null) {
            return 0;
        }
        return imageNewsArrayList.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        public ImageView newsImage;
        public TextView headline;

        NewsHolder(View itemView) {
            super(itemView);
            newsImage = (ImageView) itemView.findViewById(R.id.iv_item_image);
            headline = (TextView) itemView.findViewById(R.id.tv_item_image);
        }
    }

}
