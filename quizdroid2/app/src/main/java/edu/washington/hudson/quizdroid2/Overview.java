package edu.washington.hudson.quizdroid2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Overview extends Fragment implements OnClickListener{

    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // get View of Fragment from fragment_main.xml
        View view = inflater.inflate(R.layout.fragment_overview, container, false);
        Log.i("Overview", "Inflating layout!");

        // get button @id/button4 from fragment_main.xml
        Button btn = (Button) view.findViewById(R.id.buttonf1);
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("Overview","On attach called");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonf1:
                Log.i("Overview", "Overview Test clicked");
                //go to question fragment
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                FragmentTransaction replace = transaction.replace(R.id.quiz_giver, new Questions());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }
    }

    public interface OnNewsItemSelectedListener{
        public void onNewsItemPicked(int position);
    }
}
