package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText numberField1;
    private EditText numberField2;
    private TextView resultTextView;
    private Button calculateButton;
    private Spinner operationSpinner;
    private String selectedOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberField1 = findViewById(R.id.numberField1);
        numberField2 = findViewById(R.id.numberField2);
        resultTextView = findViewById(R.id.resultTextView);
        calculateButton = findViewById(R.id.calculateButton);
        operationSpinner = findViewById(R.id.operationSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operationSpinner.setAdapter(adapter);

        operationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOperation = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedOperation = "";
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        // Получаем числа из текстовых полей
        String number1Str = numberField1.getText().toString();
        String number2Str = numberField2.getText().toString();

        // Проверяем, что оба поля были заполнены
        if (number1Str.isEmpty() || number2Str.isEmpty()) {
            resultTextView.setText("Введите оба числа");
            return;
        }

        // Преобразуем строки в числа
        double number1 = Double.parseDouble(number1Str);
        double number2 = Double.parseDouble(number2Str);

        // Выполняем вычисления в зависимости от выбранной операции
        double result = 0;
        switch (selectedOperation) {
            case "Сложение":
                result = number1 + number2;
                break;
            case "Вычитание":
                result = number1 - number2;
                break;
            case "Умножение":
                result = number1 * number2;
                break;
            case "Деление":
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    resultTextView.setText("Деление на ноль невозможно");
                    return;
                }
                break;
        }

        // Выводим результат
        resultTextView.setText(String.valueOf(result));
    }
}
