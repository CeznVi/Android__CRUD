package com.itstep.mymvvm;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itstep.mymvvm.models.TechnologyModel;
import com.itstep.mymvvm.repositories.TechnologyRepository;
import com.itstep.mymvvm.viewmodels.TechnologyViewModel;

public class MainActivity extends AppCompatActivity {

    TechnologyViewModel technologyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        technologyViewModel = new TechnologyViewModel(this);
        technologyViewModel.fromModelToView();


    }
}