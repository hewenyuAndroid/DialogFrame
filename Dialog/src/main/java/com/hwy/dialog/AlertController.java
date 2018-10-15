package com.hwy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 作者: hewenyu
 * 日期: 2018/10/15 14:10
 * 说明:
 */
class AlertController {

    private Dialog mDialog;
    private Window mWindow;

    private ViewHolder mViewHolder;

    public AlertController(Dialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public Dialog getDialog() {
        return mDialog;
    }

    public Window getWindow() {
        return mWindow;
    }

    public ViewHolder getViewHolder() {
        return mViewHolder;
    }

    public void setViewHolder(ViewHolder viewHolder) {
        this.mViewHolder = viewHolder;
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mViewHolder.setText(viewId, text);
    }

    /**
     * 获取View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        return mViewHolder.getView(viewId);
    }

    /**
     * 设置View的点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mViewHolder.setOnClickListener(viewId, listener);
    }

    public static class AlertParams {

        public Context mContext;
        public int mThemeResId;

        // 点击空白是否能够取消
        public boolean mCancelable = true;

        // Dialog cancel 监听
        public DialogInterface.OnCancelListener mOnCancelListener;

        // Dialog dismiss 监听
        public DialogInterface.OnDismissListener mOnDismissListener;

        // Dialog Key 监听
        public DialogInterface.OnKeyListener mOnKeyListener;

        // 布局View
        public View mView;

        // 布局文件的资源Id
        public int mLayoutId;

        // 存放字体的修改
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();

        // 存放点击事件
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();

        // 宽度
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 高度
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 动画
        public int mAnimations = 0;

        // 显示的位置
        public int mGravity = Gravity.CENTER;

        // dialog 的宽度和屏幕宽度的百分比
        public float mWidthPercent = 0.6f;

        // dialog 背景色
        public int mBackgroundColor = -1;

        // dialog 背景圆角
        public float mCorner = 0f;

        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        /**
         * 绑定和设置参数
         *
         * @param mAlert
         */
        public void apply(AlertController mAlert) {
            // 完善这个地方 设置参数

            // 1. 设置Dialog布局  DialogViewHelper
            ViewHolder viewHolder = null;
            if (mLayoutId != 0) {
                viewHolder = new ViewHolder(mContext, mLayoutId, mAlert.getWindow());
            }

            if (mView != null) {
                viewHolder = new ViewHolder();
                viewHolder.setContentView(mView);
            }

            if (viewHolder == null) {
                throw new IllegalArgumentException("请设置布局setContentView()");
            }

            // 给Dialog 设置布局
            mAlert.getDialog().setContentView(viewHolder.getContentView());

            // 设置 Controller的辅助类
            mAlert.setViewHolder(viewHolder);

            // 2.设置文本
            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                mAlert.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            // 3.设置点击
            int clickArraySize = mClickArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                mAlert.setOnclickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            // 4.配置自定义的效果  全屏  从底部弹出    默认动画
            Window window = mAlert.getWindow();
            // 设置位置
            window.setGravity(mGravity);

            // 设置背景色
            if (mBackgroundColor != -1) {
                viewHolder.getContentView().setBackgroundColor(mBackgroundColor);
            }

            // 设置圆角
            if (mCorner > 0f) {
                setDialogBackground(mCorner, viewHolder.getContentView());
            }

            // 设置动画
            if (mAnimations != 0) {
                window.setWindowAnimations(mAnimations);
            }

            // 设置dialog的宽
            if (mWidthPercent < 0f) {
                mWidthPercent = 0.6f;
            } else if (mWidthPercent > 1.0f) {
                mWidthPercent = 1.0f;
            }
            mWidth = (int) (getScreenWidth(mAlert.getWindow()) * mWidthPercent);

            mHeight = mHeight > getScreenHeight(mAlert.getWindow()) ? getScreenHeight(mAlert.getWindow()) : mHeight;

            // 设置宽高
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);
        }

        /**
         * 设置弹窗的背景
         *
         * @param corner
         * @param contentView
         */
        private void setDialogBackground(float corner, View contentView) {

            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.RECTANGLE);
            gradientDrawable.setCornerRadius(corner);
            gradientDrawable.setColor(Color.WHITE);

            Drawable bgDrawable = contentView.getBackground();
            if (bgDrawable == null) {
                contentView.setBackgroundDrawable(gradientDrawable);
                return;
            }

            if (bgDrawable instanceof ColorDrawable) {
                gradientDrawable.setColor(((ColorDrawable) bgDrawable).getColor());
                contentView.setBackgroundDrawable(gradientDrawable);
                return;
            }

            if (bgDrawable instanceof GradientDrawable) {
                ((GradientDrawable) bgDrawable).setCornerRadius(corner);
                contentView.setBackgroundDrawable(bgDrawable);
                return;
            }


        }


        /**
         * 获取屏幕的高度
         *
         * @param window
         * @return
         */
        private int getScreenWidth(Window window) {
            WindowManager wm = window.getWindowManager();
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.widthPixels;
        }

        /**
         * 获取屏幕的高度
         *
         * @param window
         * @return
         */
        private int getScreenHeight(Window window) {
            WindowManager wm = window.getWindowManager();
            DisplayMetrics dm = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(dm);
            return dm.heightPixels;
        }

    }


}
