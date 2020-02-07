package com.mobisem.engineering.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.ProgressBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnDismissListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

public class CommonUtils {



    public static ProgressDialog showDialog(Context context, @StringRes int titleId, @StringRes int messageId) {
        ProgressDialog progressDialog = new ProgressDialog(context, ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(context.getString(messageId));
        progressDialog.setTitle(context.getString(titleId));

        progressDialog.show();
        return progressDialog;
    }

    public static DialogPlus createDialog(Context context, @LayoutRes int layout, int gravity) {
        return DialogPlus.newDialog(context).setContentHolder(new ViewHolder(layout))
                .setGravity(gravity).setCancelable(true).create();

    }
    public static DialogPlus createDialogWithListener(Context context, @LayoutRes int layout, int gravity,
                                                      boolean cancelable, OnDismissListener listener) {
        DialogPlusBuilder dialogPlusBuilder = DialogPlus.newDialog(context).setContentHolder(new ViewHolder(layout))
                .setGravity(gravity).setCancelable(false);
        if (listener != null) {
            dialogPlusBuilder.setOnDismissListener(listener); }
        return dialogPlusBuilder
                .create();
    }








}
