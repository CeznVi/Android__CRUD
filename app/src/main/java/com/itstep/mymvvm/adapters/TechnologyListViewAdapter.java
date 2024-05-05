package com.itstep.mymvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.itstep.mymvvm.R;
import com.itstep.mymvvm.models.TechnologyModel;

import java.util.ArrayList;

public class TechnologyListViewAdapter extends ArrayAdapter<TechnologyModel>
{
    private final ArrayList<TechnologyModel> models;
    private final int elementLayoutId;
    private final LayoutInflater layoutInflater;
    public TechnologyListViewAdapter(
            Context activity,
            int elementLayoutId,
            ArrayList<TechnologyModel> models
            ){
        super(activity, elementLayoutId, models);
        this.models = models;
        this.elementLayoutId = elementLayoutId;
        this.layoutInflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(elementLayoutId, parent, false);
        }

        TextView txtName = convertView.findViewById(R.id.technologyList_name);
        TextView txtAge = convertView.findViewById(R.id.technologyList_age);

        TechnologyModel currentModel = models.get(position);

        txtName.setText(" " + currentModel.getName());
        txtAge.setText(" " + currentModel.getAge());

        Button btnDel = convertView.findViewById(R.id.technologyList_btn_del);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                models.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
