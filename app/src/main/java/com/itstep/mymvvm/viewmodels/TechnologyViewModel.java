package com.itstep.mymvvm.viewmodels;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.itstep.mymvvm.MainActivity;
import com.itstep.mymvvm.R;
import com.itstep.mymvvm.adapters.TechnologyListViewAdapter;
import com.itstep.mymvvm.models.TechnologyModel;
import com.itstep.mymvvm.repositories.TechnologyRepository;

public class TechnologyViewModel {
    MainActivity activity;

    public TechnologyViewModel(MainActivity activity) {
        this.activity = activity;
    }

    public void fromModelToView(){

        ListView list = activity.findViewById(R.id.technologyList);

        ArrayAdapter<TechnologyModel> adapter =  new TechnologyListViewAdapter(
                activity,
                R.layout.technology_listview_item,
                TechnologyRepository.getInstance().getTechnologies()
        );

        list.setAdapter(adapter);

        Button btnAdd = activity.findViewById(R.id.technologyList_btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Create Technology");
                View dialogView = LayoutInflater.from(activity).inflate(R.layout.edit_technology_dialog, null);
                EditText editName = dialogView.findViewById(R.id.edit_technology_name);
                EditText editAge = dialogView.findViewById(R.id.edit_technology_age);
                builder.setView(dialogView);

                // Установить слушатель для кнопок "Save" и "Cancel"
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newName = editName.getText().toString();
                        int newAge = Integer.parseInt(editAge.getText().toString());
                        // Создать новую модель с введенными данными
                        TechnologyModel newModel = new TechnologyModel(newName, newAge);
                        // Добавить новую модель в список и обновить адаптер

                        TechnologyRepository repo = TechnologyRepository.getInstance();
                       repo.getTechnologies().add(newModel);
                       adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", null);

                // Отобразить диалоговое окно
                builder.create().show();
            }
        });


    }

    private void addTechnology(String name, int age) {
        SQLiteOpenHelper dbHelper = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        db.insert("technologies", null, values);
        db.close();
    }

}
