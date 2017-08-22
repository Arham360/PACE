package com.example.arhamakbar.pace3.Activity.Fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.arhamakbar.pace3.Activity.Activity.Activity2;
import com.example.arhamakbar.pace3.Activity.Activity.data.DatabaseHandler;
import com.example.arhamakbar.pace3.Activity.Adapter.DataAdapter;
import com.example.arhamakbar.pace3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Primary.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Primary#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Primary extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    DatabaseHandler databaseHandler;
    SharedPreferences shared;

    List<String> myItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView listView;

    LinearLayout LinearLayout;
    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    private boolean mParam3;
    private ArrayList<String> mParam4;


    private OnFragmentInteractionListener mListener;

    public Primary() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Primary.
     */
    // TODO: Rename and change types and number of parameters
    public static Primary newInstance(int param1, String param2, boolean param3, ArrayList<String> param4) {
        Primary fragment = new Primary();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putBoolean(ARG_PARAM3,param3);
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
            Log.v("PARAM1",""+mParam1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Log.v("PARAM2",""+mParam2);
            mParam3 = getArguments().getBoolean(ARG_PARAM3);
            Log.v("PARAM3",""+mParam3);
            mParam4 = getArguments().getStringArrayList(ARG_PARAM4);
            Log.v("PARAM3",""+mParam4);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_primary, container, false);
        Log.v("LIST", mParam4.toString());
        try {
            if (mParam4.get(mParam4.size() - 1).equals(mParam4.get(mParam4.size() - 3))) {
                Log.v("GOTEM", "just delete the two in the middle");
                mParam4.remove(mParam4.size()-1);
                mParam4.remove(mParam4.size()-1);
                super.onCreateView(inflater,container,savedInstanceState);
            } else {
                Log.v("Size small", "dont do nothin");
            }
        }catch (Exception e){

        }
        for (int i = 0 ; i< mParam4.size();i++) {
            myItems.add(mParam4.get(i));
        }
        myItems.add(mParam2);
        mParam4.add(mParam2);
        Log.v("check ID",""+mParam1);
        ((Activity2) getActivity()).setActionBarTitle(mParam2);


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        LinearLayout = (LinearLayout) rootview.findViewById(R.id.buttonContainer);
//        LinearLayout.setAlignmentMode(LinearLayout.ALIGN_BOUNDS);
//        LinearLayout.setColumnCount(2);
//        LinearLayout.setRowCount(3);

        if(mParam3 == true) {
            createOptionsByAge(mParam1);
        }else{

            if(databaseHandler.getCaseFromOption(mParam1).size()>0){

            }

            createOptionsByID(mParam1);
        }

        String captionText = databaseHandler.getCaptionTextById(mParam1);
        if (captionText.equals("")){
            captionText = "What is the patient's primary Symptom?";
        }
        TextView textView = (TextView) rootview.findViewById(R.id.captionView);
        textView.setText(captionText);
        //check if caption length is greater than zero, if so, set that to textview else default



        listView = (ListView) rootview.findViewById(R.id.backStackList);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.backlistcard, myItems);
        listView.setAdapter(adapter);
        scrollMyListViewToBottom();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    ((Activity2)getActivity()).finish();
                }
                FragmentManager fm = getFragmentManager();
                fm.popBackStack(position+1, FragmentManager.POP_BACK_STACK_INCLUSIVE);


                //TODO WORK ON BACKSTACK FUNCTIONALITY WITH JOEL
            }
        });








        return rootview;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//

    public void createOptionsByAge (int age){
        final ArrayList<Integer> arrayList;


        arrayList = databaseHandler.getInitialOptionByAge(age);
        Log.v("ARRAY",""+arrayList.toString()+"      " + arrayList.size());
        for (int i = 0 ; i< arrayList.size(); i++){

            final Button myButton1 = new Button(getContext());
            final int a =i ;
            //myButton1.setBackgroundResource(R.drawable.rounded_corners);
            myButton1.setPadding(5,0,5,0);
           // myButton1.setBackgroundColor(0xFF497FFD);
           // myButton1.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF497FFD")));
            final String text = databaseHandler.getOptionTextById(arrayList.get(i));
            myButton1.setText(text);
            //myButton1.setTextColor(0xffffff);
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(0xFF497FFD); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(20);
            gd.setStroke(1, 0xFF000000);
            myButton1.setBackgroundDrawable(gd);
//
             myButton1.setOnClickListener(new View.OnClickListener(){
                 @Override
                 public void onClick(View view) {

                     //get activity and make new fragment? add it to backstack?
                     ((Activity2) getActivity()).makeNewFragment(arrayList.get(a), text, mParam4);

                 }
             });

            LinearLayout.addView(myButton1);

        }

    }

    private void scrollMyListViewToBottom() {
        listView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                listView.setSelection(adapter.getCount() - 1);
                // =listView.getItemAtPosition(adapter.getCount());
                //TODO CHANGE COLOR FOR LAST ONE
            }
        });
    }



    public void createOptionsByID (int id){
        final ArrayList<Integer> arrayList;

        //Log.v("TRY",databaseHandler.getOptionTextById(900));
        arrayList = databaseHandler.getOptionById(id);
        Log.v("ARRAY",""+arrayList.toString()+"      " + arrayList.size());
        for (int i = 0 ; i< arrayList.size(); i++){

            Button myButton1 = new Button(getContext());
            final int a =i ;
            //myButton1.setBackgroundResource(R.drawable.rounded_corners);

            //Button myButton2 = new Button(getContext(),null,R.layout.small_button);
            //Log.v("getText", databaseHandler.getOptionTextById(i));
            final String text = databaseHandler.getOptionTextById(arrayList.get(i));
            myButton1.setText(text);

//            LinearLayout.LayoutParams param =new LinearLayout.LayoutParams();
//            param.height = android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
//            param.width = android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
//            param.rightMargin = 5;
//            param.topMargin = 5;
//            param.setGravity(Gravity.CENTER);
//            param.columnSpec = LinearLayout.spec(90);
//            param.rowSpec = LinearLayout.spec(100);
//            myButton1.setLayoutParams (param);
            // Log.v("getText", databaseHandler.getOptionTextById(i));
            myButton1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                    //get activity and make new fragment? add it to backstack?
                    ((Activity2) getActivity()).makeNewFragment(arrayList.get(a), text, mParam4);

                }
            });

            LinearLayout.addView(myButton1);

        }

    }

    public void popFromList(ArrayList arrayList){
        arrayList.remove(arrayList.size());
    }

    @Override
    public void onPause() {
        myItems.clear();
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
