package com.hwy.dialog.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者: hewenyu
 * 日期: 2018/11/26 10:14
 * 说明:
 */
public class KeyBoardUtil {

    public static void showKeyBoard(EditText editText) {
        showKeyBoard(editText, 200);
    }

    /**
     * 显示键盘
     *
     * @param editText 需要显示弹窗的编辑框
     * @param delay    键盘弹起的延迟
     */
    public static void showKeyBoard(final EditText editText, int delay) {

        if (editText == null) {
            return;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (editText != null) {
                    // 设置可获取焦点
                    editText.setFocusable(true);
                    editText.setFocusableInTouchMode(true);
                    editText.requestFocus();
                    InputMethodManager im = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.showSoftInput(editText, 0);
                }

            }
        }, delay);


    }


}
