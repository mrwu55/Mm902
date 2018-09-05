package yty.gxjy.com.mmxxx.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

import yty.gxjy.com.mmxxx.Bean.PicDetailBean;
import yty.gxjy.com.mmxxx.R;

/**
 * Created by WuJingCheng
 * on 2018/9/2.
 */
public class PagerPicsAdapter  extends PagerAdapter {
    private List<PicDetailBean.DataBean> imgList;
    private Context mContext;
    public PagerPicsAdapter(List<PicDetailBean.DataBean> imgList, Context mContext) {
        this.imgList = imgList;
        this.mContext = mContext;
    }
    @Override
    public int getCount() {
        return imgList.size();
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imagelayout = LayoutInflater.from(mContext).inflate(
                R.layout.pic_detail_lay, null);
        final ImageView imageview =  imagelayout
                .findViewById(R.id.img_pager_item);

        Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {

                int targetWidth = imageview.getWidth();
                if (source.getWidth() == 0) {
                    return source;
                }
                //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                if (targetHeight != 0 && targetWidth != 0) {
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
        Picasso.with(mContext)
                .load(imgList.get(position).getPicUrl())
                .transform(transformation)
                 .into(imageview);
        container.addView(imagelayout, 0);
        return imagelayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         container.removeView((View) object);
    }
}
