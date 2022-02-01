package com.example.mastermind;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class EasyLevelAdapter extends ArrayAdapter<easylevel> {
    public EasyLevelAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
