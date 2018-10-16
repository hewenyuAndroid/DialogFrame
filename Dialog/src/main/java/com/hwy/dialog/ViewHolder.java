package com.hwy.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.hwy.dialog.listener.OnDialogClickListener;

import java.lang.ref.WeakReference;

/**
 * 作者: hewenyu
 * 日期: 2018/10/15 14:10
 * 说明:
 */
class ViewHolder {

    private View mContentView = null;

    private SparseArray<WeakReference<View>> mViews;

    public ViewHolder(Context context, int layoutId, Window window) {
        this();
        mContentView = LayoutInflater.from(context).inflate(layoutId, (ViewGroup) window.getDecorView(), false);
    }

    public ViewHolder() {
        mViews = new SparseArray<>();
    }

    /**
     * 设置布局View
     *
     * @param contentView
     */
    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        if (tv != null && text != null) {
            tv.setText(text);
        }
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     * @param dialog
     */
    public void setOnClickListener(int viewId, final OnDialogClickListener listener, final AlertDialog dialog) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(dialog, v);
                }
            });
        }
    }

    /**
     * 获取 contentView
     *
     * @return
     */
    public View getContentView() {
        return mContentView;
    }

    /**
     * 获取 View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        WeakReference<View> viewReference = mViews.get(viewId);
        // 防止内存溢出
        View view = null;
        if (viewReference != null) {
            view = viewReference.get();
        }
        if (view == null) {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<View>(view));
            }
        }

        return (T) view;
    }

}
