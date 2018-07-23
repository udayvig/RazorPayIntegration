package com.example.android.razorpayintegration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener{

    EditText editText;
    Button button;
    int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.amountEditText);
        button = findViewById(R.id.payButton);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPayment();
            }
        });
    }

    private void startPayment() {
        amount = Integer.parseInt(editText.getText().toString());

        Checkout checkout = new Checkout();
        checkout.setImage(R.mipmap.ic_launcher);

        final Activity activity = this;

        try{
            JSONObject options = new JSONObject();
            options.put("description", "Test Payment");
            options.put("currency", "INR");
            options.put("amount", amount*100);
            checkout.open(activity, options);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "Success! " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Failed.", Toast.LENGTH_SHORT).show();
    }
}
