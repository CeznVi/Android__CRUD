package com.itstep.mymvvm.repositories;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.itstep.mymvvm.R;

public class TechnologyCursorAdapter extends CursorAdapter {

    public TechnologyCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.technology_listview_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Получите данные из курсора и отобразите их в элементах интерфейса
        TextView txtName = view.findViewById(R.id.technologyList_name);
        TextView txtAge = view.findViewById(R.id.technologyList_age);

        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));

        txtName.setText(name);
        txtAge.setText(String.valueOf(age));
    }
}
