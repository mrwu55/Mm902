package yty.gxjy.com.mmxxx.databinding;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import yty.gxjy.com.mmxxx.App;
import yty.gxjy.com.mmxxx.Bean.PicDetailBean;
import yty.gxjy.com.mmxxx.Bean.PicsBean;
import yty.gxjy.com.mmxxx.Bean.SearchBean;
import yty.gxjy.com.mmxxx.Bean.VideoBean;
import yty.gxjy.com.mmxxx.Interface.RecyclerItemClick;
import yty.gxjy.com.mmxxx.R;
import yty.gxjy.com.mmxxx.Util.RecyclerItemDecoration;
import yty.gxjy.com.mmxxx.adapter.CollectAdapter;
import yty.gxjy.com.mmxxx.adapter.NewRecyclerAdapter;
import yty.gxjy.com.mmxxx.adapter.PicturesAdapter;
import yty.gxjy.com.mmxxx.adapter.SearchRecyclerAdapter;
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
    @BindingAdapter({"dataSearch","listener"})
    public static void setDataSearch(RecyclerView recyclerView, SearchBean data, RecyclerItemClick listener) {
//        Context context = recyclerView.getContext();
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(new SearchRecyclerAdapter(data,listener));
    }
    @BindingAdapter({"dataPics","listener"})
    public static void setPics(RecyclerView recyclerView, PicDetailBean data, RecyclerItemClick listener) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),2));
        recyclerView.setAdapter(new PicturesAdapter(data,listener));
//        recyclerView.addItemDecoration(new RecyclerItemDecoration(20,2));
    }
    @BindingAdapter({"videoPlay"})
    public static void setVideoData(RecyclerView recyclerView, VideoBean data) {
        if(data==null) return;
//        Context context = recyclerView.getContext();
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(new VideoRecyclerAdapter(data,context));
    }
    @BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.p1)
                .into(view);
    }
    @BindingAdapter({"imagePic"})
    public static void loadImagePic(final ImageView view, String imageUrl) {
        int width =0;
        int height = 0;
        Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Log.e("source",App.getApp().getwidth()+"==="+source.getWidth());
                int targetWidth = view.getWidth();
                if (source.getWidth() == 0) {
                    return source;
                }
                //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);

                if (targetHeight != 0 && targetWidth != 0) {
                    Log.e("裁剪",targetWidth+"==="+aspectRatio);
                    Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                    if (result != source) {
                        source.recycle();
                    }
                    return result;
                } else {
                    return source;
                }

            }
            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        Picasso.with(view.getContext())
                .load(imageUrl)
                .transform(transformation)
                .into(view);
    }
    @BindingAdapter({"videoUrl"})
    public static void loadVideo(VideoView videoView, String videoUrl) {

    }
    @BindingAdapter({"collectData"})
    public static void setCollectData(RecyclerView recyclerView, List<Integer> data) {
//        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),4));
//        recyclerView.setAdapter(new CollectAdapter(data));
//        recyclerView.addItemDecoration(new RecyclerItemDecoration(8,4));
    }

}
