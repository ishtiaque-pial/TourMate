package com.example.pial.tourmate.eventDetailsTablayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pial.tourmate.R;
import com.example.pial.tourmate.activityPackage.LoginSharedPreference;
import com.example.pial.tourmate.customListView.ExpenseListAdapter;
import com.example.pial.tourmate.dataEntryTablayout.DataEntryActivity;
import com.example.pial.tourmate.database.Event;
import com.example.pial.tourmate.database.EventManager;
import com.example.pial.tourmate.database.Expense;
import com.example.pial.tourmate.database.ExpenseManager;

import java.util.ArrayList;

/**
 * Created by Pial on 26-Nov-16.
 */

public class EventExpense extends Fragment {
    Button btnExpense;
    FloatingActionButton fltExpense;
    ListView expenseDetailsListView;
    ArrayList<Expense> expensesList;
    ExpenseManager expenseManager;
    ExpenseListAdapter expenseListAdapter;
    ImageView imageView;
    TextView tx;
    String eventID;
    String phn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_expense, container, false);
        btnExpense= (Button) rootView.findViewById(R.id.btnAddEventExpense);
        fltExpense= (FloatingActionButton) rootView.findViewById(R.id.fabAddEventExpense);
        expenseDetailsListView= (ListView) rootView.findViewById(R.id.eventExpenseListView);
        imageView= (ImageView) rootView.findViewById(R.id.imageViewExpense);
        tx= (TextView) rootView.findViewById(R.id.textViewExpense);
        LoginSharedPreference loginSharedPreference=new LoginSharedPreference(getActivity());
        String idd=loginSharedPreference.getEventKey();


        eventID=loginSharedPreference.getEventKey();
        phn=loginSharedPreference.getUserPhone();
        ExpenseManager ex=new ExpenseManager(getActivity());
        int i=ex.totalExpense(eventID);
        if (i==0)
        {

        }
        else {

            loginSharedPreference.saveTotalExpense(i);
            EventManager eventManager = new EventManager(getActivity());
            long res = eventManager.updateEventTable(eventID, i);
            if (res<1)
            {
                Toast.makeText(getActivity(), "Something Wrong", Toast.LENGTH_SHORT).show();
            }



        }
        expenseManager = new ExpenseManager(getActivity());
        expensesList = expenseManager.getAllExpense(idd);

        if (expensesList.isEmpty())
        {

        }
        else
        {
            btnExpense.setVisibility(View.GONE);
            tx.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            expenseDetailsListView.setVisibility(View.VISIBLE);
            expenseListAdapter=new ExpenseListAdapter(getActivity(),expensesList);
            expenseDetailsListView.setAdapter(expenseListAdapter);
        }

        btnExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoIntentExpense();
            }
        });
        fltExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoIntentExpense();
            }
        });


        return rootView;
    }
    public void gotoIntentExpense()
    {
        Intent intent=new Intent(getActivity(), DataEntryActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}
