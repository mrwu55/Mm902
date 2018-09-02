package yty.gxjy.com.mmxxx.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import yty.gxjy.com.mmxxx.rollpager.LoopPagerAdapter
import yty.gxjy.com.mmxxx.rollpager.RollPagerView

/**
 * Created by WuJingCheng on 2018/7/20.
 */

class VideoPagerAdapter(viewPager: RollPagerView,listImg:List<Int>) : LoopPagerAdapter(viewPager) {
    private var listImg = listImg
    override fun getView(container: ViewGroup, position: Int): View? {
        val view = ImageView(container.context)
        view.scaleType = ImageView.ScaleType.FIT_XY
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        view.setImageResource(listImg[position])
        return view
    }

    override fun getRealCount(): Int {
        return listImg.size
    }
}
