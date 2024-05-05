package com.itstep.mymvvm.viewmodels;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

//        ArrayAdapter<TechnologyModel> adapter =  new ArrayAdapter<>(
//                activity,
//                android.R.layout.simple_list_item_1,
//                TechnologyRepository.getInstance().getTechnologies()
//        );
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
                TechnologyModel newModel = TechnologyRepository.createModel();
                newModel.setAge(0);
                newModel.setName("Test");
                TechnologyRepository.getInstance().getTechnologies().add(newModel);
                adapter.notifyDataSetChanged();
            }
        });


    }

}
