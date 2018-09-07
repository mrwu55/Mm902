package yty.gxjy.com.mmxxx.Interface

import yty.gxjy.com.mmxxx.Bean.SearchBean

/**
 * Created by WuJingCheng
 * on 2018/9/8.
 */
interface SearchClickListener{
    fun onClick(dataBean: SearchBean.DataBean, position: Int)
}