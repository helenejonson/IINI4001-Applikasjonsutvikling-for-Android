/* Tomas Holt, 20.09.20012.
 * This class is for testing HttpWrapper. For output look in the log. */

package leksjon.oving5;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HttpActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

	public void play(View view){
		TextView nameView = findViewById(R.id.setName);
		TextView accountView = findViewById(R.id.setAccount);
		String name = nameView.getText().toString();
		String accountCheck = accountView.getText().toString();
		TextView messageView = findViewById(R.id.message);

		messageView.setText("");
		String [] valueList = {name, accountCheck};
		Intent i = new Intent("leksjon.http.GameActivity");
		ArrayList<String> myList = new ArrayList<String>();
		myList.add(name);
		myList.add(accountCheck);
		i.putExtra("mylist", myList);
		//i.putExtra("Player", valueList);
		startActivityForResult(i,1);
	}

	public void onActivityResult(int requestCode,  int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1 && resultCode == RESULT_OK) {
			TextView messageView = findViewById(R.id.message);
			String e = data.getExtras().getString("response");
			messageView.setText(e);

		}
	}

}