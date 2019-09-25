package com.example.pocket;

import androidx.annotation.NonNull;

public class Budget {

    public int id;
    public String date;
    public String category;
    public String ammount;

    @NonNull
    @Override
    public String toString() {
        return category;
    }
}