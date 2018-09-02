package yty.gxjy.com.mmxxx.fragment

import android.databinding.DataBindingUtil
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.adapter.HomePagerAdapter
import yty.gxjy.com.mmxxx.databinding.HomeBinding

class HomeFragment : BaseFragment() {
    var binding: HomeBinding? = null
    var mTabLayout:TabLayout? = null
    var mViewPager:ViewPager? = null
    override fun setLayout(inflater: LayoutInflater?, container: ViewGroup?): View?{
            binding = DataBindingUtil.inflate(inflater,
                    R.layout.fragment_home, container, false)
        return binding?.root
    }
    override fun initView() {
        mTabLayout = binding?.homeTablayout
        mViewPager = binding?.homePager
        mTabLayout?.setTabMode(TabLayout.MODE_FIXED)
        mTabLayout?.setTabGravity(TabLayout.GRAVITY_CENTER)
        var fragmentList = listOf<android.support.v4.app.Fragment>(NewFragment(),HotFragment())
        mViewPager?.setAdapter(HomePagerAdapter(fragmentList,fragmentManager))
        mTabLayout?.setupWithViewPager(mViewPager)
    }

    override fun initData() {
    }
}
