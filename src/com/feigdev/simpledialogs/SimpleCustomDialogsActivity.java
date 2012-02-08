package com.feigdev.simpledialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SimpleCustomDialogsActivity extends Activity {
	private static final int DIALOG_REPLY = 1231;
	private String messageText = "";
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
	           @Override
	           public void onClick(View view) {
	               contextMenu();
	           }
	    });
    }
    
    public void toastText(String message){
    	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
	

	
	private void contextMenu(){
		try {
			final CharSequence[] items = {"Context","Enter Text"};

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setItems(items, new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int item) {
			    	switch (item){
			    	case 0:
			    		toastText("Context clicked");
			    		break;
			    	case 1:
			    		showDialog(DIALOG_REPLY);
	        			break;
			    	}
			        
			    }
			});
			builder.create().show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    
	@Override
	protected Dialog onCreateDialog(int id){
	    Dialog dialog = null;
	    switch (id) {
	    case DIALOG_REPLY:
	        dialog = buildEnterTextDialog();
	        break;
	    default:
	        dialog = null;
	    }
	    return dialog;
	}
	
	public Dialog buildEnterTextDialog(){
	    final Dialog dialog = new Dialog(this);

	    dialog.setContentView(R.layout.enter_text);
	    dialog.setTitle(R.string.enter_text_title);
	    final EditText et =  (EditText)dialog.findViewById(R.id.enter_text_content);
	    et.setText(messageText);
	    dialog.findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
	    	@Override
	        public void onClick(View view) {
	    		messageText = et.getText().toString();
	    		customDisplayDialog("Text you entered","Ok","Exit app",messageText);
	    		messageText= "";
	    		et.setText(messageText);
	    		dialog.dismiss();
	        }
		});
	    dialog.findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
			@Override
	        public void onClick(View view) {
				toastText("message cleared");
				messageText = "";
	        	et.setText(messageText);
	        }
		});
	    dialog.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
	    	@Override
	        public void onClick(View view) {
	    		messageText = et.getText().toString();
	        	dialog.dismiss();
	        }
		});
	    return dialog;
	}
	
	

	public void customDisplayDialog(String title, String positive, String negative, String message){
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(title);
		builder.setPositiveButton(positive, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		if (negative.equals("") || negative == null){
			builder.setCancelable(false);
		}
		else {
			builder.setCancelable(true);
			builder.setNegativeButton(negative, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();
			    }
			});	
		}
			    
		builder.setMessage(message);
		builder.create().show();
		
	}
}