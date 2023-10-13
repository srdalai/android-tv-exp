package com.muvi.androidtvexp;

import static androidx.annotation.Dimension.SP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler, recycler2;
    LinearLayoutManager linearLayoutManager;
    RecyclerAdapter adapter;
    List<String> numList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*recycler = findViewById(R.id.recycler);
        recycler2 = findViewById(R.id.recycler2);

        numList.add("");
        numList.add("");
        for (int i = 0; i < 24; i++) {
            numList.add(String.valueOf(i + 1));
        }
        numList.add("");
        numList.add("");

        linearLayoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new RecyclerAdapter(numList);
        recycler.setAdapter(adapter);

        recycler2.setLayoutManager(new LinearLayoutManager(this));
        recycler2.setAdapter(new RecyclerAdapter(numList));


        new LinearSnapHelper().attachToRecyclerView(recycler);

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int firstItem = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                int lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();

                if (lastItem == 23) {
                    adapter.setSelectedItem(21);
                } else {
                    adapter.setSelectedItem(firstItem + 2);
                }
            }
        });*/

        Button btnGetTime = findViewById(R.id.btnGetTime);
        CustomTimePicker customTimePicker = findViewById(R.id.customTimePicker);

        btnGetTime.setOnClickListener(view -> {
            String alarmTime = "Alarm will be set for " + customTimePicker.getSelectedHour() + ":" +  customTimePicker.getSelectedMinute();
            Toast.makeText(MainActivity.this, alarmTime, Toast.LENGTH_SHORT).show();
        });

    }


    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

        private final List<String> numList;


        int currentSelectedItemPos = 2;

        public RecyclerAdapter(List<String> numList) {
            this.numList = numList;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ctp_item_time_layout, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
            holder.textView.setText(numList.get(position));

            if (position != currentSelectedItemPos) {
                holder.textView.setTextSize(SP, 12);
                holder.textView.setTextColor(Color.parseColor("#8C8C8C"));
            } else {
                holder.textView.setTextSize(SP, 24);
                holder.textView.setTextColor(Color.parseColor("#FFFFFF"));
            }
        }

        @Override
        public int getItemCount() {
            return numList.size();
        }

        public void setSelectedItem(int pos) {
            currentSelectedItemPos = pos;
            notifyDataSetChanged();
        }

        public class RecyclerViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public RecyclerViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }
        }

    }
}