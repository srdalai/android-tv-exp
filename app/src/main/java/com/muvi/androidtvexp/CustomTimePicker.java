package com.muvi.androidtvexp;

import static androidx.annotation.Dimension.SP;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomTimePicker extends FrameLayout {

    private static final String TAG = "CustomTimePicker";

    public CustomTimePicker(@NonNull Context context) {
        super(context);
        init();
    }

    public CustomTimePicker(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTimePicker(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CustomTimePicker(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private RecyclerView hourRecycler, minutesRecycler;
    private LinearLayoutManager hourLayoutManager, minuteLayoutManager;
    private List<String> hourDataList = new ArrayList<>();
    private List<String> minuteDataList = new ArrayList<>();
    private RecyclerAdapter hourAdapter, minuteAdapter;

    private void init() {
        inflate(getContext(), R.layout.ctp_time_picker_layout, this);

        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minute = rightNow.get(Calendar.MINUTE);
        Log.d(TAG, "init: hour - " + hour + "; minute - " + minute);

        initViews();

        prepareData();
        setHourRecycler();
        setMinuteRecycler();

        hourRecycler.scrollToPosition(hour);
        minutesRecycler.scrollToPosition(minute);
        hourAdapter.setSelectedItem(hour + 3);
        minuteAdapter.setSelectedItem(minute + 3);
    }

    private void initViews() {
        hourRecycler = findViewById(R.id.hourRecycler);
        minutesRecycler = findViewById(R.id.minutesRecycler);
    }

    private void prepareData() {
        hourDataList.add("");
        hourDataList.add("");
        hourDataList.add("");
        for (int i = 0; i < 24; i++) {
            hourDataList.add(String.valueOf(i));
        }
        hourDataList.add("");
        hourDataList.add("");
        hourDataList.add("");

        minuteDataList.add("");
        minuteDataList.add("");
        minuteDataList.add("");
        for (int i = 0; i < 60; i++) {
            minuteDataList.add(String.valueOf(i));
        }
        minuteDataList.add("");
        minuteDataList.add("");
        minuteDataList.add("");
    }

    private void setHourRecycler() {
        hourLayoutManager = new LinearLayoutManager(getContext());
        hourAdapter = new RecyclerAdapter(hourDataList);
        hourRecycler.setAdapter(hourAdapter);
        hourRecycler.setLayoutManager(hourLayoutManager);
        new LinearSnapHelper().attachToRecyclerView(hourRecycler);

        hourRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE) {
                    int firstItem = hourLayoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastItem = hourLayoutManager.findLastCompletelyVisibleItemPosition();

                    if (firstItem == 0) {
                        hourAdapter.setSelectedItem(3);
                    } else if (lastItem == hourDataList.size() - 1) {
                        hourAdapter.setSelectedItem(hourDataList.size() - 4);
                    } else {
                        hourAdapter.setSelectedItem(firstItem + 3);
                    }
                }
            }
        });
    }

    private void setMinuteRecycler() {
        minuteLayoutManager = new LinearLayoutManager(getContext());
        minuteAdapter = new RecyclerAdapter(minuteDataList);
        minutesRecycler.setAdapter(minuteAdapter);
        minutesRecycler.setLayoutManager(minuteLayoutManager);
        new LinearSnapHelper().attachToRecyclerView(minutesRecycler);

        minutesRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE) {
                    int firstItem = minuteLayoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastItem = minuteLayoutManager.findLastCompletelyVisibleItemPosition();

                    if (firstItem == 0) {
                        minuteAdapter.setSelectedItem(3);
                    } else if (lastItem == minuteDataList.size() - 1) {
                        minuteAdapter.setSelectedItem(minuteDataList.size() - 4);
                    } else {
                        minuteAdapter.setSelectedItem(firstItem + 3);
                    }
                }
            }
        });
    }

    public String getSelectedHour() {
        return hourDataList.get(hourAdapter.getCurrentSelectedItemPos());
    }

    public String getSelectedMinute() {
        return minuteDataList.get(minuteAdapter.getCurrentSelectedItemPos());
    }

    private static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

        private final List<String> numList;
        private int currentSelectedItemPos = 5;

        public RecyclerAdapter(List<String> numList) {
            this.numList = numList;
        }

        @NonNull
        @Override
        public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ctp_item_time_layout, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolder holder, int position) {
            holder.textView.setText(numList.get(position));

            if (position == currentSelectedItemPos) {
                holder.textView.setTextSize(SP, 18);
                holder.textView.setTextColor(Color.parseColor("#FFFFFF"));
            } else if (position == currentSelectedItemPos - 1 || position == currentSelectedItemPos + 1) {
                holder.textView.setTextSize(SP, 14);
                holder.textView.setTextColor(Color.parseColor("#8C8C8C"));
            } else if (position == currentSelectedItemPos - 2 || position == currentSelectedItemPos + 2) {
                holder.textView.setTextSize(SP, 10);
                holder.textView.setTextColor(Color.parseColor("#8C8C8C"));
            } else {
                holder.textView.setTextSize(SP, 6);
                holder.textView.setTextColor(Color.parseColor("#8C8C8C"));
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

        public int getCurrentSelectedItemPos() {
            return currentSelectedItemPos;
        }

        private static class RecyclerViewHolder extends RecyclerView.ViewHolder {

            TextView textView;

            public RecyclerViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }
        }

    }
}
