package com.example.dailyxp;

import android.content.Context;
import android.view.*;
import android.widget.*;
import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<String> {

    public TaskAdapter(Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.task_item, parent, false);
        }

        TextView taskText = convertView.findViewById(R.id.taskText);
        taskText.setText(getItem(position));

        return convertView;
    }
}
