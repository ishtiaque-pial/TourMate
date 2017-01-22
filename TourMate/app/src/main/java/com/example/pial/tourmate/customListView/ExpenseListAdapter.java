package com.example.pial.tourmate.customListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.pial.tourmate.R;
import com.example.pial.tourmate.database.Event;
import com.example.pial.tourmate.database.Expense;

import java.util.ArrayList;

/**
 * Created by Pial on 25-Nov-16.
 */

public class ExpenseListAdapter extends ArrayAdapter {
    ArrayList<Expense> expenseList;
    private Context context;


    public ExpenseListAdapter(Context context, ArrayList<Expense> expenseList) {
        super(context, R.layout.row_expense_info, expenseList);
        this.context = context;
        this.expenseList=expenseList;

    }

    private static class ViewHolder {
        TextView expenseDateTV,expenseTimeTV,expenseDetailsTV,colonView,expenseAmountTV,expenseAmountTkView;



    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.row_expense_info, parent, false);

            viewHolder.expenseDateTV= (TextView) view.findViewById(R.id.expenseDateTV);
            viewHolder.expenseTimeTV= (TextView) view.findViewById(R.id.expenseTimeTV);
            viewHolder.expenseDetailsTV= (TextView) view.findViewById(R.id.expenseDetailsTV);
            viewHolder.colonView= (TextView) view.findViewById(R.id.colonView);
            viewHolder.expenseAmountTV= (TextView) view.findViewById(R.id.expenseAmountTV);
            viewHolder.expenseAmountTkView= (TextView) view.findViewById(R.id.expenseAmountTkView);



            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ExpenseListAdapter.ViewHolder) view.getTag();
        }

        viewHolder.expenseDateTV.setText(expenseList.get(position).getExpenseDate());
        viewHolder.expenseTimeTV.setText(expenseList.get(position).getExpenseTime());
        viewHolder.expenseDetailsTV.setText(expenseList.get(position).getExpenseDetails());
        viewHolder.expenseAmountTV.setText(expenseList.get(position).getExpenseAmount());








        return view;

    }
}
