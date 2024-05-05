package com.itstep.mymvvm.repositories;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "technologies.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создаем таблицу для хранения технологий
        db.execSQL("CREATE TABLE technologies (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Выполняем обновление базы данных при изменении схемы
        db.execSQL("DROP TABLE IF EXISTS technologies");
        onCreate(db);
    }
}
