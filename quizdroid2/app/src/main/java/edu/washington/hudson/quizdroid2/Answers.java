package edu.washington.hudson.quizdroid2;

import android.app.Activity;
import android.content.Intent;
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

public class Answers extends Fragment implements OnClickListener{

    Button btn;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // get View of Fragment from fragment_main.xml
        View view = inflater.inflate(R.layout.fragment_answers, container, false);
        Log.i("Overview", "Inflating layout!");

        // get button @id/button4 from fragment_main.xml
        btn = (Button) view.findViewById(R.id.next);
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i("Answers","On attach called");
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        if ((QuizGiver.isLast())) {
            btn.setText("Finish");
        }
        super.onActivityCreated(savedInstanceState);
        TextView temp = (TextView) getView().findViewById(R.id.numCorrect);
        temp.setText("You have " + (QuizGiver.getNumCorrect() + " out of "));
        temp = (TextView)getView().findViewById(R.id.numQuestions);
        temp.setText("" + (QuizGiver.getCurrentQuestionNumber() + " correct"));
        temp = (TextView)getView().findViewById(R.id.your_answer);
        temp.setText("Your answer: " + QuizGiver.getSelected());
        temp = (TextView)getView().findViewById(R.id.answerIs);
        temp.setText("The correct answer is: " + QuizGiver.getCorrect());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                if ((QuizGiver.isLast())) {
                    btn.setText("Finish");
                    QuizGiver.reset();
                    Intent toTopicsHome = new Intent(getActivity(), TopicsHome.class);
                    startActivity(toTopicsHome);
                    getActivity().finish();
                } else {
                    QuizGiver.incrementCurrentQuestionNum();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    FragmentTransaction replace = transaction.replace(R.id.quiz_giver, new Questions());
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                break;
        }

    }
}
