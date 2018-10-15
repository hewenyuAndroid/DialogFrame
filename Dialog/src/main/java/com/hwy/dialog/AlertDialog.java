package com.hwy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;

/**
 * 作者: hewenyu
 * 日期: 2018/10/15 14:01
 * 说明: dialog 构建
 */
public class AlertDialog extends Dialog {

    private AlertController mAlert;

    public AlertDialog(Context context) {
        super(context);
        mAlert = new AlertController(this, getWindow());
    }

    public AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }

    /**
     * 获取View
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        return mAlert.getView(viewId);
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        mAlert.setOnclickListener(viewId, listener);
    }

    public static class Builder {

        private final AlertController.AlertParams P;

        // region -------------------------- 构造器 ---------------------------------------

        public Builder(Context context) {
            this(context, R.style.dialog);
        }

        public Builder(Context context, int themeResId) {
            P = new AlertController.AlertParams(context, themeResId);
        }

        // endregion ----------------------------------------------------------------------

        public Builder setContentView(View view) {
            P.mView = view;
            P.mLayoutId = 0;
            return this;
        }

        // 设置布局内容的layoutId
        public Builder setContentView(int layoutId) {
            P.mView = null;
            P.mLayoutId = layoutId;
            return this;
        }

        // 设置文本
        public Builder setText(int viewId, CharSequence text) {
            P.mTextArray.put(viewId, text);
            return this;
        }

        // 设置点击事件
        public Builder setOnClickListener(int view, View.OnClickListener listener) {
            P.mClickArray.put(view, listener);
            return this;
        }

        /**
         * 从底部弹出
         *
         * @param isAnimation 是否有动画
         * @return
         */
        public Builder fromBottom(boolean isAnimation) {
            if (isAnimation) {
                P.mAnimations = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }


        /**
         * 设置Dialog的宽高
         *
         * @param width
         * @param height
         * @return
         */
        public Builder setWidthAndHeight(int width, int height) {
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        /**
         * 设置dialog 的宽度和屏幕宽度的百分比
         *
         * @param percent 0.0f - 1.0f
         * @return
         */
        public Builder setWidthPercent(float percent) {
            P.mWidthPercent = percent;
            return this;
        }

        /**
         * 添加默认动画
         *
         * @return
         */
        public Builder addDefaultAnimation() {
            P.mAnimations = R.style.dialog_scale_anim;
            return this;
        }

        /**
         * 添加动画
         *
         * @param animStyle
         * @return
         */
        public Builder addAnimation(int animStyle) {
            P.mAnimations = animStyle;
            return this;
        }

        /**
         * 设置动画
         *
         * @param styleAnimation
         * @return
         */
        public Builder setAnimations(int styleAnimation) {
            P.mAnimations = styleAnimation;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        /**
         * 设置弹窗背景的圆角
         *
         * @param corner
         * @return
         */
        public Builder setCorner(float corner) {
            P.mCorner = corner;
            return this;
        }

        /**
         * 设置背景色
         *
         * @param backgroundColor
         * @return
         */
        public Builder setBackgroundColor(int backgroundColor) {
            P.mBackgroundColor = backgroundColor;
            return this;
        }

        /**
         * 构建 dialog 对象
         *
         * @return
         */
        public AlertDialog create() {
            final AlertDialog dialog = new AlertDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        /**
         * 显示 dialog
         *
         * @return
         */
        public AlertDialog show() {
            final AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }

}
