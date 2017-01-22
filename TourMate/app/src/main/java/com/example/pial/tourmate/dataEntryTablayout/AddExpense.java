package com.example.pial.tourmate.dataEntryTablayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pial.tourmate.R;
import com.example.pial.tourmate.activityPackage.LoginSharedPreference;
import com.example.pial.tourmate.database.EventManager;
import com.example.pial.tourmate.database.Expense;
import com.example.pial.tourmate.database.ExpenseManager;
import com.example.pial.tourmate.eventDetailsTablayout.EventDetailsActivity;
import com.example.pial.tourmate.eventTablayout.EventActivity;

/**
 * Created by Pial on 26-Nov-16.
 */

public class AddExpense extends Fragment {
    EditText deataitsET,amountET,dialogET;
    Button bt;
    String eventID;
    String phn;
    private String value;
    final Context context=getContext();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_expense, container, false);

        deataitsET= (EditText) rootView.findViewById(R.id.expDeatailsET);
        amountET= (EditText)rootView.findViewById(R.id.expAmountET);
        bt= (Button) rootView.findViewById(R.id.btnexpentry);
        final LoginSharedPreference loginSharedPreference=new LoginSharedPreference(getActivity());
        eventID=loginSharedPreference.getEventKey();
        phn=loginSharedPreference.getUserPhone();


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deatails=deataitsET.getText().toString();
                String amount=amountET.getText().toString();




                Calendar cal = Calendar.getInstance();

                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");

                String datee=simpleDateFormat.format(cal.getTime());
                SimpleDateFormat simpleTimeFormat=new SimpleDateFormat("hh:mma");



                String time=simpleTimeFormat.format(cal.getTime());

                if (deatails.isEmpty()||amount.isEmpty())
                {
                    Toast.makeText(getActivity(), "Fill all the field", Toast.LENGTH_SHORT).show();
                }
                else {
                    int res = loginSharedPreference.getBudget() - (loginSharedPreference.getTotalExpense() + Integer.parseInt(amount));
                    if (res < 0) {
                        LayoutInflater li = LayoutInflater.from(getActivity());
                        View promptsView = li.inflate(R.layout.row_dialog, null);
                        final EditText userInput = (EditText) promptsView
                                .findViewById(R.id.dialogET);

                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setMessage("This amount out of budget.Wanna put extra budget?")
                                .setView(promptsView).setPositiveButton("ok", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                value=userInput.getText().toString();
                                EventManager ev=new EventManager(getActivity());
                                long val=ev.updateEventBudget(eventID,loginSharedPreference.getBudget()+Integer.parseInt(value));
                                if (val<1)
                                {
                                    Toast.makeText(getActivity(), "Something Wrong", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    loginSharedPreference.saveBudget(loginSharedPreference.getBudget()+Integer.parseInt(value));
                                    Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();

                                }
                            }

                        }).setNegativeButton("No",null)
                                .setCancelable(false);
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();


                        /*EventManager ev=new EventManager(getActivity());
                        long val=ev.updateEventBudget(eventID,loginSharedPreference.getBudget()+Integer.parseInt(value));
                        if (val<1)
                        {
                            Toast.makeText(getActivity(), "Something Wrong", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();

                        }*/


                    }else {
                        ExpenseManager expenseManager = new ExpenseManager(getActivity());
                        //int expenseID, String eventId, String userPhoneNo, String expenseDetails, String expenseAmount, String expenseDate, String expenseTime
                        Expense expense = new Expense(eventID, phn, deatails, amount, datee, time);


                        long result = expenseManager.addExpense(expense);
                        if (result > 0) {
                            Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getActivity(), "Something Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        return rootView;
    }


}
