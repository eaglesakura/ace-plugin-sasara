package org.andriders.talk.sasara.ui.widget;

import com.eaglesakura.material.widget.DialogBuilder;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import org.andriders.talk.sasara.util.AppUtil;

/**
 * アプリ用のDialog生成
 */
public class AppDialogBuilder extends DialogBuilder {
    public AppDialogBuilder(AlertDialog.Builder builder) {
        super(builder);
    }

    public static AppDialogBuilder newError(Context context, Throwable error) {
        AppDialogBuilder builder = new AppDialogBuilder(new AlertDialog.Builder(context));
        builder.mBuilder.setTitle(AppUtil.getErrorTitle(context, error));
        builder.mBuilder.setMessage(AppUtil.getErrorMessage(context, error));
        return builder;
    }
}
