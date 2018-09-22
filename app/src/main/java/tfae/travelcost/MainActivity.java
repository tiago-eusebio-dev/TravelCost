package tfae.travelcost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edtDistance    = (EditText) findViewById(R.id.edtDistance);
        final EditText edtPrice       = (EditText) findViewById(R.id.edtPrice);
        final EditText edtConsumption = (EditText) findViewById(R.id.edtConsumption);
        final EditText edtPeopleNr    = (EditText) findViewById(R.id.edtPeopleNr);
        final EditText edtTolls       = (EditText) findViewById(R.id.edtTolls);
        final EditText edtTimes       = (EditText) findViewById(R.id.edtTimes);
        final Button btnClear         = (Button) findViewById(R.id.btnClear);
        final Button btnCalculate     = (Button) findViewById(R.id.btnCalculate);
        final TextView txtResultLabel = (TextView) findViewById(R.id.txtResultLabel);
        final TextView txtResultValue = (TextView) findViewById(R.id.txtResultValue);

        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                edtDistance.setText("");
                edtPrice.setText("");
                edtConsumption.setText("");
                edtPeopleNr.setText("");
                edtTolls.setText("");
                edtTimes.setText("");

                txtResultLabel.setText("");
                txtResultValue.setText("");
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Float distance    = getFloatValue(edtDistance, 0);
                Float price       = getFloatValue(edtPrice, 0);
                Float consumption = getFloatValue(edtConsumption, 0);
                Integer people    = getIntegerValue(edtPeopleNr, 1);
                Float tolls       = getFloatValue(edtTolls, 0);
                Integer times     = getIntegerValue(edtTimes, 1);

                edtDistance.setText(distance.toString());
                edtPrice.setText(price.toString());
                edtConsumption.setText(consumption.toString());
                edtPeopleNr.setText(people.toString());
                edtTolls.setText(tolls.toString());
                edtTimes.setText(times.toString());

                txtResultLabel.setText(getResources().getString(R.string.total_per_person));
                txtResultValue.setText(calculate(distance, price, consumption, people, tolls, times));
            }
        });

    }

    private String calculate(Float distance, Float price, Float consumption, Integer people, Float tolls, Integer times) {
        Float gas  = (distance * consumption)/100;
        Float cost = (gas * price) + tolls;
        return round((cost * times) / people) + getResources().getString(R.string.cur_symbol);
    }

    private Float getFloatValue(EditText edt, Integer default_value) {
        return TextUtils.isEmpty(edt.getText()) ? default_value : Float.parseFloat(edt.getText().toString());
    }

    private Integer getIntegerValue(EditText edt, Integer default_value) {
        return TextUtils.isEmpty(edt.getText()) ? default_value : Integer.parseInt(edt.getText().toString());
    }

    private String round(Float value) {
        return String.format("%.2f", value);
    }
}
