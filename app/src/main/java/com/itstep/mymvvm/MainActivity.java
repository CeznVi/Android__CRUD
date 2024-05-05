package com.itstep.mymvvm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itstep.mymvvm.models.TechnologyModel;
import com.itstep.mymvvm.repositories.DBHelper;
import com.itstep.mymvvm.repositories.TechnologyCursorAdapter;
import com.itstep.mymvvm.repositories.TechnologyRepository;
import com.itstep.mymvvm.viewmodels.TechnologyViewModel;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    TechnologyViewModel technologyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        technologyViewModel = new TechnologyViewModel(this);
        technologyViewModel.fromModelToView();
        dbHelper = new DBHelper(this);

        // Загрузите данные из базы данных и отобразите их в ListView
        loadDataFromDatabase();

    }

    private void loadDataFromDatabase() {
        // Загрузите данные из базы данных
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM technologies", null);

        // Создайте адаптер и установите его для ListView
        TechnologyCursorAdapter adapter = new TechnologyCursorAdapter(this, cursor);
        ListView listView = findViewById(R.id.technologyList);
        listView.setAdapter(adapter);
    }

    // Добавьте методы для добавления, обновления и удаления данных в базе данных

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Закройте базу данных при уничтожении активности
        dbHelper.close();
    }
}