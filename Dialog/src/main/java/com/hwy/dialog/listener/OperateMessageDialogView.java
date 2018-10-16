package com.hwy.dialog.listener;

import android.widget.TextView;

/**
 * 作者: hewenyu
 * 日期: 2018/10/16 13:11
 * 说明: 操作MessageDialog的四个布局控件
 */
public interface OperateMessageDialogView {

    void update(TextView title, TextView message, TextView confirm, TextView cancel);

}
