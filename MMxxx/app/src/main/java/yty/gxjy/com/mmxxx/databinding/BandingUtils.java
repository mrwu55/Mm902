package yty.gxjy.com.mmxxx.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.List;

import yty.gxjy.com.mmxxx.Bean.PicsBean;
import yty.gxjy.com.mmxxx.Interface.RecyclerItemClick;
import yty.gxjy.com.mmxxx.R;
import yty.gxjy.com.mmxxx.Util.RecyclerItemDecoration;
import yty.gxjy.com.mmxxx.adapter.CollectAdapter;
import yty.gxjy.com.mmxxx.adapter.NewRecyclerAdapter;
import yty.gxjy.com.mmxxx.adapter.VideoRecyclerAdapter;

/**
 * Created by WuJingCheng on 2018/7/19.
 */

public class BandingUtils {
    @BindingAdapter({"data","listener"})
    public static void setData(RecyclerView recyclerView, PicsBean data, RecyclerItemClick listener) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),2));
        recyclerView.setAdapter(new NewRecyclerAdapter(data,listener));
        recyclerView.addItemDecoration(new RecyclerItemDecoration(20,2));
    }
    @BindingAdapter({"videoPlay"})
    public static void setVideoData(RecyclerView recyclerView, List<String> data) {
        Context context = recyclerView.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new VideoRecyclerAdapter(data,context));
    }
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.p1)
                .into(view);
    }
    @BindingAdapter({"videoUrl"})
    public static void loadVideo(VideoView videoView, String videoUrl) {

    }
    @BindingAdapter({"collectData"})
    public static void setCollectData(RecyclerView recyclerView, List<Integer> data) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),4));
        recyclerView.setAdapter(new CollectAdapter(data));
        recyclerView.addItemDecoration(new RecyclerItemDecoration(8,4));
    }

}
