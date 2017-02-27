package com.guaju.autoruntextview.view;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by guaju on 2017/2/27.
 */


public class AutoRunTextView extends TextView {
    /*
    根据开发情况,我这里只遇到了这两种数据类型,如果有其他需要可以发邮箱给我
     */
    TextView mTv=this;
    public static final int INT_NUM=1;//数字是整形的
    public static final int FLOAT_NUM=2;//数字是浮点型的
    public static int timesquare=1; //数字切换的毫秒值
    public static int intTemp=0;//定义int类型数据的临时变量
    public static float floatTemp=0.00f;//定义float类型数据的临时变量
    DecimalFormat format=new DecimalFormat("##0.00");
    public static int addScale=1;
    Object num;
    public Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.obj!=null){
                num=msg.obj;
            }
            switch (msg.what){
                case  INT_NUM:
                    if (intTemp<=(int)num){
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mTv.setText(intTemp+"");
                            intTemp=intTemp+1*addScale;
                            mHandler.sendEmptyMessage(INT_NUM);
                        }
                    },timesquare);
                    }
                    break;
                case FLOAT_NUM:
                    if (floatTemp<=(float)num){
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                floatTemp=floatTemp+0.01f*addScale;
                                String reault=format.format(floatTemp);
                                mTv.setText(reault);

                                mHandler.sendEmptyMessage(FLOAT_NUM);
                            }
                        },timesquare);
                    }
                    break;
            }
        }
    };
    public AutoRunTextView(Context context) {
        super(context);
    }

    public AutoRunTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoRunTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //定义一个方法,让textview自动变化显示的数值
    public void setAutoRunNum(int iNum){
         Message msg=Message.obtain();
         msg.what=INT_NUM;
         msg.obj=iNum;
         mHandler.sendMessage(msg);
    }
    public void setAutoRunNum(float fNum){
        Message msg=Message.obtain();
        msg.what=FLOAT_NUM;
        msg.obj=fNum;
        mHandler.sendMessage(msg);
    }

    //设定添加文字的倍数,默认是以一个单位形式进行添加,如需加快需要加大addScale倍数
}
