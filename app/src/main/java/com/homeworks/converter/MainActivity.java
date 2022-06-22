package com.homeworks.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.homeworks.converter.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // <Подключаем binding>
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // <Установка изначальных значений>
        String[] items = {"Tons", "Kilograms", "Grams"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setSelection(0);
        binding.resTons.setText("0");
        binding.resKilograms.setText("0");
        binding.resGrams.setText("0");

        // <Установка значений при смене объекта в спиннере>
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                convert();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // <Установка значений при нажатии на кнопки*>
    // <*кроме backspace** на пк клавиатуре>
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        convert();
        return super.onKeyUp(keyCode, event);
    }

    // <Метод для конвертации>
    public void convert() {
      if (binding.userInput.getText().toString().equals("")) {
          binding.resTons.setText("0");
          binding.resKilograms.setText("0");
          binding.resGrams.setText("0");
      } else {
            long num = Long.parseLong(binding.userInput.getText().toString());
            switch (binding.spinner.getSelectedItem().toString()) {
                case "Tons":
                    binding.resTons.setText(String.valueOf(num));
                    binding.resKilograms.setText(String.valueOf(num * 1000));
                    binding.resGrams.setText(String.valueOf(num * 1000 * 1000));
                    break;
                case "Kilograms":
                    binding.resKilograms.setText(String.valueOf(num));
                    binding.resGrams.setText(String.valueOf(num * 1000));
                    binding.resTons.setText(String.valueOf(num * 0.001));
                    break;
                case "Grams":
                    binding.resGrams.setText(String.valueOf(num));
                    binding.resKilograms.setText(String.valueOf(num * 0.001));
                    binding.resTons.setText(String.valueOf(num * 0.001 * 0.001));
                    break;
                }
            }
    }

}


