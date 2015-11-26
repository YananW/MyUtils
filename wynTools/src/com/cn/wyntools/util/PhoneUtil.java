package com.cn.wyntools.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 手机组件调用工具类
 *
 */
public final class PhoneUtil {

    /**
     * Don't let anyone instantiate this class.
     */
    private PhoneUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 调用系统发短信界面
     *
     * @param activity    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context activity, String phoneNumber,
                                   String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsContent);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);

    }

    /**
     * 调用系统打电话界面
     *
     * @param context     上下文
     * @param phoneNumber 手机号码
     */
    public static void callPhones(Context context, String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 1) {
            return;
        }
        Uri uri = Uri.parse("tel:" + phoneNumber);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
