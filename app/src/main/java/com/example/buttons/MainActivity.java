package com.example.buttons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4, btn5, btn6,
            btn7, btn8, btn9, btn0, btnProcent, btnPlus,
            btnMinus, btnDelenie, btnRavno, btnDEL, btnX;
    private TextView tv_San;

    private double num1 = 0, num2 = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_San = findViewById(R.id.tv_San);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnProcent = findViewById(R.id.btnProcent);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnX = findViewById(R.id.btnX);
        btnDelenie = findViewById(R.id.btnDelenie);
        btnRavno = findViewById(R.id.btnRavno);
        btnDEL = findViewById(R.id.btnDEL);

        View.OnClickListener numberClickListener = v -> {
            Button clickedButton = (Button) v;
            tv_San.append(clickedButton.getText().toString());
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);

        btnPlus.setOnClickListener(v -> setOperator("+"));
        btnMinus.setOnClickListener(v -> setOperator("-"));
        btnX.setOnClickListener(v -> setOperator("*"));
        btnDelenie.setOnClickListener(v -> setOperator("/"));
        btnProcent.setOnClickListener(v -> setOperator("%"));

        btnRavno.setOnClickListener(v -> calculateResult());

        btnDEL.setOnClickListener(v -> {
            tv_San.setText("");
            num1 = 0;
            num2 = 0;
            operator = "";
        });
    }

    private void setOperator(String op) {
        if (!tv_San.getText().toString().isEmpty()) {
            num1 = Double.parseDouble(tv_San.getText().toString());
            operator = op;
            tv_San.setText("");
        }
    }

    private void calculateResult() {
        if (!tv_San.getText().toString().isEmpty() && !operator.isEmpty()) {
            num2 = Double.parseDouble(tv_San.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        tv_San.setText("Error");
                        return;
                    }
                    break;
                case "%":
                    result = num1%num2;
                    //чел попозже вернись сюда и сделай норм калькик что за позорище
            }

            tv_San.setText(String.valueOf(result));
            operator = "";
        }
    }

}
