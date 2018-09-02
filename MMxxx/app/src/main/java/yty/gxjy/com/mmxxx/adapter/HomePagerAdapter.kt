package yty.gxjy.com.mmxxx.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by WuJingCheng on 2018/7/18.
 */

class HomePagerAdapter(fragmentList:List<Fragment>, fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    var fragmentList = fragmentList
    var titleList = arrayOf("最新","热门")
    override fun getItem(position: Int): android.support.v4.app.Fragment {
        return fragmentList.get(position)
    }
    override fun getCount(): Int {
        return titleList.size
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }
}
