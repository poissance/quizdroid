package edu.washington.hudson.quizdroid;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuizApp extends Application implements TopicRepository {

    private static QuizApp instance;
    private static ArrayList<Topic> topicStore;
    private static int currentQuestionNum;
    private static int numCorrect;
    private int currentTopic;

    public static void initInstance() {
        if (instance == null) {
            instance = new QuizApp();
        }
    }

    public static QuizApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QuizApp", "QuizApp created");

        // Math Questions
        topicStore = new ArrayList<Topic>();
        ArrayList<Quiz> questions = null;
        try {
            questions = questionsFromJSON("Math");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createTopic("Math", "Asks simple math related questions", "Asks questions related to math including addition and famous mathematicians.", questions);

        // Physics Questions
        try {
            questions = questionsFromJSON("Physics");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createTopic("Physics", "Asks simple physics related questions", "Asks questions related to physics including force and buoyancy", questions);

        // Marvel Super Heroes
        try {
            questions = questionsFromJSON("Marvel Super Heroes");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        createTopic("Marvel Super Heroes", "Asks simple Marvel related questions", "Asks questions related to Marvel Super Heroes including Thor, Hulk, and Captain America.", questions);
        Log.i("QuizApp", "Topics added to store");

    }

    private ArrayList<Quiz> questionsFromJSON(String topic) throws IOException, FileNotFoundException, JSONException {
        ArrayList<Quiz> questions = new ArrayList<Quiz>();
        JSONObject obj;
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS + "/quizdata.json");
        Log.i("QuizApp","Does file exist? " + file.exists());
        String files = Environment.DIRECTORY_DOWNLOADS;
        Log.i("QuizApp",Environment.DIRECTORY_DOWNLOADS);
        if (file.exists()) {
            Log.i("QuizApp","Using downloaded file!");
            FileInputStream stream = null;
            stream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            obj = new JSONObject(builder.toString());
        // need to create json object
        } else {
            //local file
            Log.i("QuizApp","Using asset file!");
            obj = new JSONObject(loadJSONFromAsset());
        }
        JSONArray array = obj.getJSONObject("Questions").getJSONArray(topic);
        for (int i = 0; i < array.length(); i++) {
            JSONObject question = (JSONObject) array.get(i);
            JSONArray answers = (JSONArray) question.get("Answers");
            questions.add(createQuestion(question.get("Question").toString(), answers.get(0).toString(), answers.get(1).toString(), answers.get(2).toString(), answers.get(3).toString(), (int) question.get("Correct")));
        }
        return questions;
    }

    // other methods go here

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream streamReader = getAssets().open("quizdata.json");
            int size = streamReader.available();
            byte[] buffer = new byte[size];
            streamReader.read(buffer);
            streamReader.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        //Log.i("QuizApp","Your JSON string is: " + json);
        return json;
    }

    public String check() {
        return "Yeah";
    }

    public Quiz createQuestion(String question, String answerOne, String answerTwo, String answerThree, String answerFour, int correct) {
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(answerOne);
        answers.add(answerTwo);
        answers.add(answerThree);
        answers.add(answerFour);
        Log.i("QuizApp","Within quizapp, correct is " + correct);
        return new Quiz(question, answers, correct);
    }

    @Override
    public void createTopic(String title, String shortDescription, String longDescription, ArrayList<Quiz> quizes) {
        topicStore.add(new Topic(title, shortDescription, longDescription, quizes));
    }

    @Override
    public Topic getTopic(int title) {
        return topicStore.get(title);
    }

    @Override
    public void updateTopic(String title) {
    }

    @Override
    public void deleteTopic(String title) {
    }

    public void incrementCurrentQuestionNum() {
        currentQuestionNum++;
    }

    public int getCurrentQuestionNum() {
        return currentQuestionNum;
    }

    public void incrementNumCorrect() {
        numCorrect++;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void quizDone() {
        currentQuestionNum = 0;
        numCorrect = 0;
    }

    public int getCurrentTopic() {
        return currentTopic;
    }

    public void setCurrentTopic(int currentTopic) {
        this.currentTopic = currentTopic;
    }
}
