package yty.gxjy.com.mmxxx.Activity
import android.databinding.DataBindingUtil
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import yty.gxjy.com.mmxxx.Interface.HomeClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.databinding.MainClass
import yty.gxjy.com.mmxxx.fragment.CollectFragment
import yty.gxjy.com.mmxxx.fragment.HomeFragment
import yty.gxjy.com.mmxxx.fragment.MineFragment
import yty.gxjy.com.mmxxx.fragment.VideoFragment

class MainActivity : BaseActivity(),HomeClickListener{
    private var binding : MainClass? = null
    private var homeFragment:HomeFragment? = null
    private var videoFragment:VideoFragment? = null
    private var collectFragment:CollectFragment? = null
    private var mineFragment: MineFragment? = null
    private var mFragmentManager: FragmentManager? = null
    private var selectIndex:Int = 1
    private var fragmentHide:Fragment? = null
    private var isExit = false
    override fun initView() {
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_main)
        binding?.setChoosedHome(true)
        binding?.setListener(this)
    }
    override fun initData() {
        mFragmentManager= supportFragmentManager
        homeFragment = HomeFragment()
        mFragmentManager?.inTransaction {add(R.id.frame_base, homeFragment)}
    }
    /**
     * 点击事件
     */
    override fun onClick(view: View) {
      hideChoosed(selectIndex)
        when(view.id){
            R.id.ll_home -> {
                selectIndex =1
                binding?.setChoosedHome(true)
            }
            R.id.ll_video ->{
                selectIndex =2
                binding?.setChoosedVideo(true)
            }
            R.id.ll_collect -> {
                selectIndex =3
                binding?.setChoosedCollect(true)
            }
            R.id.ll_mine -> {
                selectIndex =4
                binding?.setChoosedMine(true)
            }
        }
        setChooseing(selectIndex)
    }
    fun hideChoosed(select:Int){
        when(select){
            1 ->{
                binding?.setChoosedHome(false)
                fragmentHide = homeFragment
            }
            2 ->{
                binding?.setChoosedVideo(false)
                fragmentHide = videoFragment
            }
            3 ->{
                binding?.setChoosedCollect(false)
                fragmentHide = collectFragment
            }
            4 ->{
                binding?.setChoosedMine(false)
                fragmentHide = mineFragment
            }
        }
    }
    fun setChooseing(chooseIndex:Int){
        var flag = false
        var showFragment:Fragment? = null
        when(chooseIndex){
            1 ->{
                flag = false
                showFragment = homeFragment
            }
            2 ->{
                if (videoFragment == null) {
                    flag = true
                    videoFragment = VideoFragment()
                } else {
                    flag = false
                }
                showFragment = videoFragment
            }
            3 ->{
                if (collectFragment == null) {
                    flag = true
                    collectFragment = CollectFragment()
                } else {
                    flag = false
                }
                showFragment = collectFragment
            }
            4 ->{
                if (mineFragment == null) {
                    flag = true
                    mineFragment = MineFragment()
                } else {
                    flag = false
                }
                showFragment = mineFragment
            }
        }
        addFragment(flag,showFragment,fragmentHide)
    }
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
    fun addFragment(isadd:Boolean,fragmentShow: Fragment?,fragmentHide: Fragment?){
        if(fragmentShow == fragmentHide) return
        if(isadd){
            mFragmentManager?.inTransaction { add(R.id.frame_base, fragmentShow).hide(fragmentHide) }
        }else{
            mFragmentManager?.inTransaction { show(fragmentShow).hide(fragmentHide) }
        }
    }

    override fun onBackPressed() {
        if(!isExit){
            Utils.toast(this,"再按一次退出程序")
            isExit = true
            Handler().postDelayed(Runnable {
                isExit = false
            },1000)
        }else{
            super.onBackPressed()
        }
    }
}
