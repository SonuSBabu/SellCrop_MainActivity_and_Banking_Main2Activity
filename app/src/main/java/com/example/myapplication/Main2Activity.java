package com.example.myapplication;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText amtET;
    private Button amtBtn;
    private ListView amtLst;

    private ArrayList<String> amts;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        amtET = findViewById(R.id.amt_edit_text);
        amtBtn = findViewById(R.id.amt_btn);
        amtLst = findViewById(R.id.amt_list);

        amts = BankHelper.readData(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, amts);
        amtLst.setAdapter(adapter);

        amtBtn.setOnClickListener(this);
        amtLst.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.amt_btn:
                String itemEntered = amtET.getText().toString();
                adapter.add(itemEntered);
                amtET.setText("");
                BankHelper.writeData(amts, this);
                Toast.makeText(this, "Requested specified amount", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        amts.remove(position);
        adapter.notifyDataSetChanged();
        BankHelper.writeData(amts, this);
        Toast.makeText(this, " Deleted from History", Toast.LENGTH_SHORT).show();

    }
}