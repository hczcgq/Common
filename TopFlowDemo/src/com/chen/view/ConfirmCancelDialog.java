package com.chen.view;

import com.chen.R;
import com.chen.util.StringUtils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConfirmCancelDialog extends Dialog {

    public ConfirmCancelDialog(Context context, int theme) {
        super(context, theme);
    }

    public ConfirmCancelDialog(Context context) {
        super(context);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {

        private Context context;

        private String title;

        private String message;

        private String positiveButtonText;

        private String negativeButtonText;

        private View contentView;

        private DialogInterface.OnClickListener positiveButtonClickListener,
                negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Set the Dialog message from String
         * 
         * @param title
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         * 
         * @param title
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         * 
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         * 
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Set a custom content view for the Dialog. If a message is set, the
         * contentView is not added to the Dialog...
         * 
         * @param v
         * @return
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         * 
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the positive button text and it's listener
         * 
         * @param positiveButtonText
         * @param listener
         * @return
         */
        public Builder setPositiveButton(String positiveButtonText,
                DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button resource and it's listener
         * 
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(int negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Set the negative button text and it's listener
         * 
         * @param negativeButtonText
         * @param listener
         * @return
         */
        public Builder setNegativeButton(String negativeButtonText,
                DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        /**
         * Create the custom dialog
         */
        @SuppressWarnings("deprecation")
        public ConfirmCancelDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final ConfirmCancelDialog dialog = new ConfirmCancelDialog(context,
                    R.style.Dialogstyle);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.view_dialog_confirm_cancel,
                    null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

            LinearLayout content_LinearLayout = (LinearLayout) layout
                    .findViewById(R.id.content);
            TextView title_TextView = (TextView) layout
                    .findViewById(R.id.title);
            TextView message_TextView = (TextView) layout
                    .findViewById(R.id.message);
            Button position_Button = (Button) layout
                    .findViewById(R.id.positiveButton);
            Button negative_Button = (Button) layout
                    .findViewById(R.id.negativeButton);

            // set the dialog title
            title_TextView.setText(title);
            if (StringUtils.isEmpty(title)) {
                title_TextView.setVisibility(View.GONE);
            }

            // set the confirm button
            if (positiveButtonText != null) {
                position_Button.setText(positiveButtonText);
                position_Button.setTextColor(0xff1E90FF);
                if (positiveButtonClickListener != null) {
                    position_Button
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                position_Button.setVisibility(View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                negative_Button.setText(negativeButtonText);
                negative_Button.setTextColor(0xff1E90FF);
                if (negativeButtonClickListener != null) {
                    negative_Button
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                negative_Button.setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                message_TextView.setText(message);
                message_TextView.setMovementMethod(ScrollingMovementMethod
                        .getInstance());
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                content_LinearLayout.removeAllViews();
                content_LinearLayout.addView(contentView, new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }

}
