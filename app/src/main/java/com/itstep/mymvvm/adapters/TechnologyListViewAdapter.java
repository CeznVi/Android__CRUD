package com.itstep.mymvvm.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
        Button btnEdt = convertView.findViewById(R.id.technologyList_btn_edt);


        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                models.remove(position);
                notifyDataSetChanged();
            }
        });

        btnEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 2. Получаем текущий объект TechnologyModel для этой позиции
                TechnologyModel model = models.get(position);

                // 3. Создаем диалоговое окно для редактирования данных
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Technology");
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.edit_technology_dialog, null);
                EditText editName = dialogView.findViewById(R.id.edit_technology_name);
                EditText editAge = dialogView.findViewById(R.id.edit_technology_age);
                editName.setText(model.getName());
                editAge.setText(String.valueOf(model.getAge()));
                builder.setView(dialogView);

                // 4. Устанавливаем слушатель для кнопок "Save" и "Cancel"
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = editName.getText().toString();
                        int newAge = Integer.parseInt(editAge.getText().toString());
                        // Обновляем модель с новыми данными
                        model.setName(newName);
                        model.setAge(newAge);
                        // Обновляем элемент в списке через адаптер
                        updateItem(position, model);
                    }
                });
                builder.setNegativeButton("Cancel", null);

                // 5. Отображаем диалоговое окно
                builder.create().show();

            }
        });

        return convertView;
    }

    public void updateItem(int position, TechnologyModel updatedModel) {
        models.set(position, updatedModel);
        notifyDataSetChanged();
    }

}
