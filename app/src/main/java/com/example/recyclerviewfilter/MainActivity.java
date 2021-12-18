package com.example.recyclerviewfilter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    RecyclerView recyclerView;
    EditText editText;
    RecyclerViewAdapter adapter;

    ArrayList<list> items = new ArrayList<list>();
    list data = new list();
    list data1 = new list();
    list data2 = new list();
    list data3 = new list();
    list data4 = new list();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recylcerview);
        editText = (EditText)findViewById(R.id.edittext);
        editText.addTextChangedListener(this);

        data.name = "박씨";
        data.friend = "교회친구";
        items.add(0, data);

        data1.name = "김씨";
        data1.friend = "친한친구";

        items.add(1, data1);

        data2.name = "이씨";
        data2.friend = "반친구";

        items.add(2, data2);

        data3.name = "정씨";
        data3.friend = "학원친구";
        items.add(3, data3);

        data4.name = "오씨";
        data4.friend = "친한친구";
        items.add(4, data4);

        adapter = new RecyclerViewAdapter(getApplicationContext(), items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        adapter.getFilter().filter(charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
