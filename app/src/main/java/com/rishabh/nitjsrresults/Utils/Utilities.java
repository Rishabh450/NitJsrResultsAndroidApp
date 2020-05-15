package com.rishabh.nitjsrresults.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.rishabh.nitjsrresults.R;

public class Utilities {
  public static    String BASE_URL = "https://nit-jsr-results.herokuapp.com/";
  public static String HOST_URL = "https://nilekrator.pythonanywhere.com";
  public static Dialog createAlertDialog(Context context, String head, String msg, String b1, String b2){
    Dialog dialog = new Dialog(context);
    dialog.setContentView(R.layout.dialog_alert);
    TextView headtv = dialog.findViewById(R.id.dialog_head);
    TextView msgtv = dialog.findViewById(R.id.dialog_msg);
    Button cancelbtn = dialog.findViewById(R.id.dialog_cancel);
    Button contbtn = dialog.findViewById(R.id.dialog_continue);
    if(b1.compareTo("")==0)
      cancelbtn.setVisibility(View.GONE);
    headtv.setText(head);
    msgtv.setText(msg);
    cancelbtn.setText(b1);
    contbtn.setText(b2);
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    lp.copyFrom(dialog.getWindow().getAttributes());
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    dialog.show();
    dialog.getWindow().setAttributes(lp);
    dialog.show();
    return dialog;
  }
}
