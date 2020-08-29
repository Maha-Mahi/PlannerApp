package com.example.plannerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialogue extends DialogFragment {

    private EditText time,date,phone;
    private DialogueListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder( getActivity() );
        LayoutInflater inflate22=getActivity().getLayoutInflater();
        View view=inflate22.inflate(R.layout.layout_dialogue,null);

        builder.setView( view ).setTitle( "Book Meeting" ).setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        } ).setPositiveButton( "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    String time1=time.getText().toString().trim();
                    String date1=date.getText().toString().trim();
                    listener.applytext( time1,date1 );
            }
        } );

        time=(EditText)view.findViewById( R.id.timedialogue );
        date=(EditText)view.findViewById( R.id.datedialogue );
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach( context );
        try {
            listener=(DialogueListener) context;
        } catch (ClassCastException e) {
    throw new ClassCastException( context.toString()+ "Musst implement dialogue class" );

        }
    }

    public interface DialogueListener{
        void applytext(String timemeet,String datemeet);
    }
}
