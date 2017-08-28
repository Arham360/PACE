package com.example.arhamakbar.pace3.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arhamakbar.pace3.Activity.Activity.data.DatabaseHandler;
import com.example.arhamakbar.pace3.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaseFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";


    // TODO: Rename and change types of parameters
    private int mParam1;

    DatabaseHandler databaseHandler;

    public CaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment CaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseFragment newInstance(int param1) {
        CaseFragment fragment = new CaseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        databaseHandler = new DatabaseHandler(getActivity());

        View rootView = inflater.inflate(R.layout.fragment_case, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.textName) ;
        textView.setText(databaseHandler.getCaseText(mParam1));//update textview with the diseaseName

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
try {
    Picasso.with(getContext())
            .load(databaseHandler.getCaseImage(mParam1)).error(R.drawable.icon).into(imageView);//display disease picture
    //.error("http://s2.quickmeme.com/img/4f/4f4b7b8cce3a920b9263179cb28d7c822cdfaa49e6bcaa2fe215a34a2fc727c8.jpg")
}catch (Exception e){
    Log.v("STUFF",e.getLocalizedMessage());
}

        return rootView;

    }

    @Override
    public void onStop() {



        super.onStop();
    }

    @Override
    public void onDestroy() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().detach(this);
        super.onDestroy();
    }
}
