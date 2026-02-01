package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayList<String> dataList;
    ArrayAdapter<String> cityAdapter;
    EditText cityInput;
    Button addCity;
    Button deleteCity;
    Button confirm;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addCity = findViewById(R.id.add_city_button);
        deleteCity = findViewById(R.id.delete_city_button);
        confirm = findViewById(R.id.confirm_button);
        cityInput = findViewById(R.id.new_city_input);

        String []cities = {"New York", "London", "Lahore"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityInput.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String cityToAdd = cityInput.getText().toString();
                if (!cityToAdd.isEmpty())
                {
                    dataList.add(cityToAdd);
                    cityAdapter.notifyDataSetChanged();
                    cityInput.setText("");
                    cityInput.setVisibility(View.GONE);
                    confirm.setVisibility(View.GONE);
                }
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
            }
        });

        deleteCity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (selectedIndex != -1)
                {
                    dataList.remove(selectedIndex);
                    cityAdapter.notifyDataSetChanged();
                    selectedIndex = -1;
                }

            }
        });
    }
}