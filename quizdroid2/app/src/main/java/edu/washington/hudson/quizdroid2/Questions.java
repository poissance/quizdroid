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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class Questions extends Fragment implements OnClickListener{

    private RadioGroup radioGroupId;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // get View of Fragment from fragment_main.xml
        View view = inflater.inflate(R.layout.fragment_questions, container, false);
        Log.i("Questions", "Inflating layout!");
        // get button @id/button4 from fragment_main.xml
        Button btn = (Button) view.findViewById(R.id.submit);
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        radioGroupId = (RadioGroup) getView().findViewById(R.id.radioGroup);
        TextView temp = (TextView)getView().findViewById(R.id.questionNumber);
        temp.setText("Question " + QuizGiver.getCurrentQuestionNumber());
        ArrayList<View> choices = new ArrayList<View>();
        choices.add(getView().findViewById(R.id.choice1));
        choices.add(getView().findViewById(R.id.choice2));
        choices.add(getView().findViewById(R.id.choice3));
        choices.add(getView().findViewById(R.id.choice4));
        super.onActivityCreated(savedInstanceState);
        Log.i("Questions","On attach called");
        String question = QuizGiver.getQuestion();
        Log.i("Questions",question);
        temp = (TextView)getView().findViewById(R.id.question);
        temp.setText(question);
        ArrayList<String> answers = QuizGiver.getChoices();
        for (int i = 0; i < 4; i++) {
            Random placer = new Random();
            int randInt = placer.nextInt(4 - i);
            temp = (TextView) choices.remove(randInt);
            temp.setText(answers.remove(0));
        }
    }

    @Override
    public void onClick(View v) {
        int selected = radioGroupId.getCheckedRadioButtonId();
        RadioButton button = (RadioButton) getView().findViewById(selected);
        QuizGiver.setSelected("" + button.getText());
        Log.i("QuestionsPage","" + button.getText() + " Has been selected");
        if (button.getText().equals(QuizGiver.getCorrect())) {
            QuizGiver.incrementCorrect();
            Log.i("QuestionsPage","Das correct sir");
        }
        switch (v.getId()) {
            case R.id.submit:
                Log.i("Questions", "Questions Test clicked");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                FragmentTransaction replace = transaction.replace(R.id.quiz_giver, new Answers());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }

    }
}
