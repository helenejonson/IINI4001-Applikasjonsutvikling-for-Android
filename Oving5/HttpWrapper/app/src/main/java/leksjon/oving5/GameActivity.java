package leksjon.oving5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameActivity extends Activity {//
    private HttpWrapperThreaded network;
    final static String urlToServer = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        network = new HttpWrapperThreaded(this, urlToServer);
        ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");
        //p = getIntent().getExtras().getStringArray("Player");
        Map<String,String> valueList = new HashMap<String,String>();
        if(!myList.get(0).equals("")){
            valueList.put("navn", myList.get(0));
        }if(!myList.get(1).equals("")){
            valueList.put("kortnummer", myList.get(1));
        }
        network.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET, valueList);

    }

    public void showResponse(String response){
        String [] responseCheck = response.split(" ");
        TextView messageView = findViewById(R.id.hint);
        if((responseCheck.length == 5 && !responseCheck[0].equals("Feil,"))|| responseCheck.length == 7){
            messageView.setText(response);
        }else if(responseCheck.length == 13 || (responseCheck.length == 18 && !responseCheck[2].equals("glemt"))){
            messageView.setText(response);
        }
        else{
            back1(response);
        }




    }

    public void bet(View view){
        TextView tallValue = findViewById(R.id.number);
        String tall = tallValue.getText().toString();
        Map<String,String> valueList = new HashMap<String,String>();
        valueList.put("tall", tall);
        network.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET, valueList);
    }

    public void back1(String response){
        Intent i = new Intent();
        i.putExtra("response", response );
        setResult(RESULT_OK, i);
        GameActivity.this.finish();
    }

    public void back(View view){
        back1("");
    }
}
