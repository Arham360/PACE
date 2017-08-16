package com.example.arhamakbar.pace3.Activity.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private final String defaultt = "What is the patient's primary symptom?";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

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
    public static Primary newInstance(int param1, String param2) {
        Primary fragment = new Primary();
        Bundle args = new Bundle();
        args.putDouble(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_primary, container, false);
        List<String> myItems = new ArrayList<String>();
        myItems.add("Select Age Range");
        myItems.add(mParam2);
        ((Activity2) getActivity()).setActionBarTitle(mParam2);
        //should be recursive




        TextView textView = (TextView) rootview.findViewById(R.id.textView);
        //check if caption length is greater than zero, if so, set that to textview else default



        ListView listView = (ListView) rootview.findViewById(R.id.backStackList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.backlistcard, myItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView t = (TextView) view;
                //add that mparam2 into the listView as well and make it unclickable
                //add listeners to all members of the listview and give them id
                //poptobackstack with id when pressed
            }
        });



        LinearLayout linearLayout = (LinearLayout) rootview.findViewById(R.id.buttonContainer);


        //TODO add buttons programitally with the data provided
       // for (all instances of ){
//        Button myButton = new Button(getContext(),null,R.layout.small_button);
//        myButton.setText(i.getText);
//        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        linearLayout.addView(myButton, lp);

       // }

        //set clicklisteners to the button
        //when pressed, take that button id and text, pass it back to the activity and then call updateUI method


        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }




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
