package com.example.plannerapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class Multichoce extends DialogFragment {

    ArrayList<String>  selecteditemList=new ArrayList<>();

    public interface onMultiChoiceListener{
        void onPositiveButtonClicked(String[] list,ArrayList<String> selecteditemsList);
        void onNegativeButtonClicked();
    }

    onMultiChoiceListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener=(onMultiChoiceListener)context;
        }catch (Exception e){
            throw new ClassCastException(getActivity().toString()+"onMultiCoiceListener must implemnt");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        final String[] list=getActivity().getResources().getStringArray(R.array.Choicer);

        builder.setTitle("Select Your Choice").setMultiChoiceItems(list, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                if (isChecked){
                        selecteditemList.add(list[i]);
                }else {
                    selecteditemList.remove(list[i]);
                }
                }
        })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPositiveButtonClicked(list,selecteditemList);

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
