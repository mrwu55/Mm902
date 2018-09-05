package yty.gxjy.com.mmxxx.Activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_lol_pic.view.*
import okhttp3.FormBody
import yty.gxjy.com.mmxxx.Bean.BaseBean
import yty.gxjy.com.mmxxx.Bean.PicDetailBean
import yty.gxjy.com.mmxxx.Constans
import yty.gxjy.com.mmxxx.Interface.MmClickListener
import yty.gxjy.com.mmxxx.R
import yty.gxjy.com.mmxxx.Util.OkHttpUtils
import yty.gxjy.com.mmxxx.Util.SystemBarTintManager
import yty.gxjy.com.mmxxx.Util.Utils
import yty.gxjy.com.mmxxx.adapter.PagerPicsAdapter
import yty.gxjy.com.mmxxx.databinding.PicClass

class LolPicActivity : AppCompatActivity(),MmClickListener,ViewPager.OnPageChangeListener {
    private var picsDetailBean: PicDetailBean? = null
    private var mTvChoose:TextView? = null
    private var viewPager:ViewPager? = null
    private var  pdId:String? = null
    private var isCollect:Boolean = false
    private var handler : Handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            if(msg?.what == 1){
                picsDetailBean = msg.obj as PicDetailBean
                val code:Int =  picsDetailBean?.code!!
                if(code==0){
                    mTvChoose?.text ="1/"+picsDetailBean?.data!!.size
                    viewPager?.adapter =PagerPicsAdapter(picsDetailBean?.data,this@LolPicActivity)
                }else{
                    val errorMsg:String =picsDetailBean?.msg!!
                    Utils.toast(this@LolPicActivity,errorMsg)
                }
            }else if(msg?.what==2){//收藏
                val baseBean: BaseBean = msg.obj as BaseBean
                val code = baseBean.code
                if(code==0||code==1){
                    isCollect = true
                    binding?.isCollect = true
                }
                val msg:String =baseBean.msg
                Utils.toast(this@LolPicActivity,msg)
            }else if(msg?.what==3){
                val baseBean: BaseBean = msg.obj as BaseBean
                val code = baseBean.code
                if(code==0||code==1){
                    isCollect = false
                    binding?.isCollect = false
                }

                val msg:String =baseBean.msg
                Utils.toast(this@LolPicActivity,msg)
            }
        }
    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.img_pic_back ->{
                finish()
            }
            R.id.img_lol_collect->{
                if(!isCollect){
                    OkHttpUtils.getInstance().getDataWithHandCode(this, Constans.collectionPic,
                            FormBody.Builder().add("pdId",pdId).
                                    build(), BaseBean().javaClass,handler,2)
                }else{
                    OkHttpUtils.getInstance().getDataWithHandCode(this, Constans.cancelCollectionPic,
                            FormBody.Builder().add("pdId",pdId).
                                    build(), BaseBean().javaClass,handler,3)
                }

            }
            R.id.re_pic_pics ->{
                val intent =Intent(this,PicturesActivity().javaClass)
                intent.putExtra("picturesBean",picsDetailBean)
                startActivity(intent)
            }
        }
    }
    private var binding : PicClass? = null
    private var tintManager: SystemBarTintManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true)
        }
        tintManager = SystemBarTintManager(this)
        tintManager?.setStatusBarTintEnabled(true)
        tintManager?.setNavigationBarTintEnabled(true)
        tintManager?.setTintResource(R.color.black)
        binding = DataBindingUtil.
                setContentView(this, R.layout.activity_lol_pic)
        binding?.listener = this
        initData()
    }

    private fun initData() {
        mTvChoose = binding?.root!!.tv_lol_num
        viewPager = binding?.root!!.lol_pager
        val intent:Intent = intent
        pdId = intent.getStringExtra("pdId")
        val title = intent.getStringExtra("title")
        binding?.root!!.tv_lol_title.text = title
        val collect = intent.getStringExtra("collectNum")
        binding?.root!!.tv_lol_collect.text = collect
        viewPager?.setOnPageChangeListener(this)
        if(pdId!=null){
        OkHttpUtils.getInstance().getDataWithHandCode(this, Constans.getPicsDetails,
                FormBody.Builder().add("pdId",pdId).
                        build(), PicDetailBean().javaClass,handler,1)
        }
    }
    @TargetApi(19)
    private fun setTranslucentStatus(on: Boolean) {
        val win = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mTvChoose?.text =(position+1).toString()+"/"+picsDetailBean?.data!!.size
    }

}
