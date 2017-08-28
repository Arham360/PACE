package com.example.arhamakbar.pace3.Activity.Fragment;

import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arhamakbar.pace3.Activity.Activity.OptionsActivity;
import com.example.arhamakbar.pace3.Activity.Activity.data.DatabaseHandler;
import com.example.arhamakbar.pace3.R;

import java.util.ArrayList;
import java.util.List;


public class Primary extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";//id
    private static final String ARG_PARAM2 = "param2";//description
    private static final String ARG_PARAM3 = "param3";//type check
    private static final String ARG_PARAM4 = "param4";//listView list

    DatabaseHandler databaseHandler;

    List<String> myItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ListView listView;

    LinearLayout LinearLayout;

    private int mParam1;
    private String mParam2;
    private int mParam3;
    private ArrayList<String> mParam4;


    public Primary() {
        // Required empty public constructor
    }

    public static Primary newInstance(int param1, String param2, int param3, ArrayList<String> param4) {
        Primary fragment = new Primary();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3,param3);
        //args.putStringArray(ARG_PARAM4,param4);
        args.putStringArrayList(ARG_PARAM4,param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHandler = new DatabaseHandler(getActivity());

        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getInt(ARG_PARAM3);
            mParam4 = getArguments().getStringArrayList(ARG_PARAM4);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_primary, container, false);

        for (int i = 0 ; i< mParam4.size();i++) {
            myItems.add(mParam4.get(i));//add elements into the listview from previous screens
        }

        ((OptionsActivity) getActivity()).setActionBarTitle(mParam2);

        LinearLayout = (LinearLayout) rootview.findViewById(R.id.buttonContainer);

        switch (mParam3){//depending on the value, procedurally make corresponding options or case
            case 1:
                createOptionsByAge(mParam1);//1 means id is age
                break;
            case 2:
                createOptionsByID(mParam1);//2 means id is options ID
                break;
            case 3:
                createCaseByOption(mParam1);//3 means id is Case ID
                break;
        }


        String captionText = databaseHandler.getCaptionTextById(mParam1);
        if (captionText.equals("")){
            captionText = "What is the patient's primary Symptom?";//set default caption
        }
        TextView textView = (TextView) rootview.findViewById(R.id.captionView);
        textView.setText(captionText);

        listView = (ListView) rootview.findViewById(R.id.backStackList);
        adapter = new ArrayAdapter<>(getActivity(), R.layout.backlistcard, myItems);
        listView.setAdapter(adapter);
        scrollMyListViewToBottom();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getFragmentManager();

                if (position == 0){
                    ((OptionsActivity)getActivity()).finish();
                }else {

                    fm.popBackStack(position - 1, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }


            }
        });

        return rootview;
    }


    private void scrollMyListViewToBottom() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view
                listView.setSelection(adapter.getCount() - 1);
                
            }
        });
    }


    public void createOptionsByAge (int age){
        final ArrayList<Integer> arrayList;


        arrayList = databaseHandler.getInitialOptionByAge(age);
        Log.v("ARRAY",""+arrayList.toString()+"      " + arrayList.size());
        for (int i = 0 ; i< arrayList.size(); i++){

            final Button myButton1 = new Button(getContext());
            final int a =i ;

            myButton1.setPadding(5,0,5,0);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(30, 20, 30, 0);

            final String text = databaseHandler.getOptionTextById(arrayList.get(i));
            myButton1.setText(text);

            myButton1.setTextColor(getContext().getResources().getColor(R.color.Text));

            GradientDrawable gd = new GradientDrawable();
            gd.setColor(getResources().getColor(R.color.PaceTheme));
            gd.setCornerRadius(40);
            gd.setStroke(2, 0xFF000000);
            myButton1.setBackgroundDrawable(gd);

            myButton1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    //get activity and make new fragment? add it to backstack?
                    mParam4.add(databaseHandler.getOptionTextById(arrayList.get(a)));
                    ((OptionsActivity) getActivity()).makeNewFragment(arrayList.get(a), text, mParam4);

                }
            });

            LinearLayout.addView(myButton1,layoutParams);

        }

    }


    public void createOptionsByID (int id){
        final ArrayList<Integer> arrayList;
        final ArrayList<Integer> TestList;

        Log.v("CASE2",""+databaseHandler.getCaseFromOption(mParam1).toString());
        //Log.v("TRY",databaseHandler.getOptionTextById(900));
        arrayList = databaseHandler.getOptionById(id);
        //TestList = databaseHandler.getCaseFromOption(id);
        Log.v("ARRAY",""+arrayList.toString()+"      " + arrayList.size());
        //Log.v("Cases",""+TestList.toString()+"      " + TestList.size());
        for (int i = 0 ; i< arrayList.size(); i++){

            Button myButton1 = new Button(getContext());
            final int a =i ;

            myButton1.setPadding(5,0,5,0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    android.widget.LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(30, 20, 30, 0);

            final String text = databaseHandler.getOptionTextById(arrayList.get(i));
            myButton1.setText(text);

            myButton1.setTextColor(getContext().getResources().getColor(R.color.Text));

            GradientDrawable gd = new GradientDrawable();
            gd.setColor(getResources().getColor(R.color.PaceTheme));
            gd.setCornerRadius(40);
            gd.setStroke(2, 0xFF000000);
            myButton1.setBackgroundDrawable(gd);

            myButton1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Log.v("CASE",""+databaseHandler.getCaseFromOption(arrayList.get(a)).toString());
                    if (databaseHandler.getCaseFromOption(arrayList.get(a)).size()>=1){
                        Log.v("CASE","MAKING NEW CASE");
                        mParam4.add(databaseHandler.getOptionTextById(arrayList.get(a)));
                        ((OptionsActivity) getActivity()).makeNewCaseFragment(arrayList.get(a), text, mParam4);

                }else {
                    Log.v("CASE","NOT MAKING CASE");
                    //get activity and make new fragment? add it to backstack?
                    mParam4.add(databaseHandler.getOptionTextById(arrayList.get(a)));
                    ((OptionsActivity) getActivity()).makeNewFragment(arrayList.get(a), text, mParam4);
                }
                }
            });

            LinearLayout.addView(myButton1,layoutParams);

        }

    }

    private void createCaseByOption(int id) {

        final ArrayList<Integer> arrayList;


        arrayList = databaseHandler.getCaseFromOption(id);
        Log.v("CASE","Success case     "+arrayList.toString()+"      " + arrayList.size());
        for (int i = 0 ; i< arrayList.size(); i++){

            final Button myButton1 = new Button(getContext());
            final int a =i ;
            //myButton1.setBackgroundResource(R.drawable.rounded_corners);
            myButton1.setPadding(5,0,5,0);
            // myButton1.setBackgroundColor(0xFF497FFD);
            // myButton1.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF497FFD")));
            final String text = databaseHandler.getCaseText(arrayList.get(i));
            myButton1.setText(text);

            myButton1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    //get activity and make new fragment? add it to backstack?
                    mParam4.add(databaseHandler.getOptionTextById(arrayList.get(a)));
                    Log.v("Exit","about to exit");
                    ((OptionsActivity) getActivity()).showCaseFragment(arrayList.get(a));
                    Log.v("Exit","exiting caseList");
                }
            });

            LinearLayout.addView(myButton1);

        }

    }



    @Override
    public void onPause() {
       //match the number of elements in the listView with the backstack to ensure that there are no concurrency problems
        FragmentManager fm = getFragmentManager();
        if (mParam4.size() - fm.getBackStackEntryCount() > 1 ){ //the list and stack should always be at a ratio of x+1:x
            while(mParam4.size() - fm.getBackStackEntryCount() > 1 ){
            mParam4.remove(mParam4.size()-1);//keep popping off the list till ratio is maintained.
            }
        }
        myItems.clear();
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
