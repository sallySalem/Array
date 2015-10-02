package com.example.savearray_example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	String fileName = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fileName = "generated array.txt";
		
		Button btnSave  = (Button)findViewById(R.id.btn_SaveArray);
		Button btnRead = (Button)findViewById(R.id.btn_ReadArray);
		
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ArrayList<String> arrayOfString = new ArrayList<>();
				arrayOfString.add("test1");
				arrayOfString.add("test2");
				arrayOfString.add("test3");
				
				saveTofile(arrayOfString);
			}
		});
		btnRead.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ArrayList<String> data = readFromFile();
				String txt = "";
				for(int i=0; i<data.size(); i++){
					txt += data.get(i) + "\n";
				}
				
				Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
			}
		});
	}

	@SuppressWarnings("unchecked")
	protected ArrayList<String> readFromFile() {
		ArrayList<String> data = new ArrayList<>();
		File file = getFileStreamPath(fileName);
	      try {
	            if(file.exists()){
	                  FileInputStream fis = openFileInput(fileName);
	                  ObjectInputStream ois = new ObjectInputStream(fis);
	                  data = (ArrayList<String>) ois.readObject();
	                  fis.close();
	            }else {
	                  return null;
	            }
	       } catch (Exception e) {
	          e.printStackTrace();
	       }
	       return data;
	}

	protected void saveTofile(ArrayList<String> arrayOfString) {

		File file = getFileStreamPath(fileName);
        try {
            if(file.exists() || file.createNewFile()){
                FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(arrayOfString);
                fos.close();
                Toast.makeText(getApplicationContext(), "Save Done", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
         e.printStackTrace();
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
