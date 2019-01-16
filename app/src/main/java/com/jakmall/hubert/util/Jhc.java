package com.jakmall.hubert.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jakmall.hubert.R;


public class Jhc {
    public static View getView(Activity root, int id) {
        return (View) root.findViewById(id);
    }

    public static View getView(View root, int id) {
        return (View) root.findViewById(id);
    }

    public static TextView getTextView(Activity root, int id) {
        return (TextView) root.findViewById(id);
    }

    public static TextView getTextView(View root, int id) {
        return (TextView) root.findViewById(id);
    }

    public static Button getButton(Activity root, int id) {
        return (Button) root.findViewById(id);
    }

    public static Button getButton(View root, int id) {
        return (Button) root.findViewById(id);
    }

    public static Dialog createLoadingDialog(Activity root) {
        Dialog dialog = new Dialog(root);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_progress);

        dialog.show();
        return dialog;
    }

    public static void createDialog(Activity root, String message, String btnNeutral) {
        AlertDialog.Builder builder = new AlertDialog.Builder(root);
        builder.setMessage(message);

        builder.setNeutralButton(btnNeutral, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    public static void createDialog(
        Activity root,
        String message,
        String btnNeutral,
        final Runnable function
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(root);
        builder.setMessage(message);

        builder.setNeutralButton(btnNeutral, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                function.run();
            }
        });

        Dialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    public static void createDialog(
        Activity root,
        String message,
        String btnPositive,
        String btnNegative,
        final Runnable function
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(root);
        builder.setMessage(message);

        builder.setPositiveButton(btnPositive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                function.run();
            }
        });

        builder.setNegativeButton(btnNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    public static void createDialog(
        Activity root,
        String message,
        String btnPositive,
        String btnNegative,
        final Runnable funcPositive,
        final Runnable funcNegative
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(root);
        builder.setMessage(message);

        builder.setPositiveButton(btnPositive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                funcPositive.run();
            }
        });

        builder.setNegativeButton(btnNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                funcNegative.run();
            }
        });

        Dialog dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    public static void openActivity(Activity root, Class activity) {
        Intent intent = new Intent(root, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        root.startActivity(intent);
    }

    public static void openActivity(Activity root, Class activity, Bundle bundle) {
        Intent intent = new Intent(root, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        root.startActivity(intent);
    }

    public static void changeActivity(Activity root, Class activity) {
        Intent intent = new Intent(root, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        root.startActivity(intent);
        root.finish();
    }

    public static void changeActivity(Activity root, Class activity, Bundle bundle) {
        Intent intent = new Intent(root, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        root.startActivity(intent);
        root.finish();
    }
}
