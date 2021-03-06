package com.chen.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {


    /**
     * 短消息
     * @param context
     * @param message
     */
    public static void showToastShort(Context context,String string) {
        Toast toast = Toast.makeText(context, string,
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    
    /**
     * 长消息
     * @param context
     * @param message
     */
    public static void showToastLong(Context context,String string) {
        Toast toast = Toast.makeText(context, string,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
