package com.example.tabsapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AddSetActivity extends Activity {

	private EditText title;
	private EditText storedSetView;
	private LinearLayout add_set_view;
	private int count = 0;
	private final static String SET = "set.txt";
	private final int titleIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_set);

		add_set_view = (LinearLayout) findViewById(R.id.add_set_view);
		title = (EditText) add_set_view.getChildAt(titleIndex);
		count = add_set_view.getChildCount();
		storedSetView = (EditText) findViewById(R.id.storedSetView);

		Log.d("debug", "count = " + add_set_view.getChildCount());
		Log.d("debug", "title = " + title.getText().toString());

		readSet();
	}

	public void saveSet(View view) {

//		Kin kin1 = new Kin("tomato", "tomate");
//		Kin kin2 = new Kin("home", "maison");
		List<Kin> kinsList = new ArrayList<Kin>();
//		kinsList.add(kin1);
//		kinsList.add(kin2);

		for (int i = titleIndex + 1; i < 2; i+=2) {
			EditText editTextLg1 = (EditText) add_set_view.getChildAt(i);
			EditText editTextLg2 = (EditText) add_set_view.getChildAt(i+1);
			Kin kin = new Kin(editTextLg1.getText().toString(), 
					editTextLg2.getText().toString());
			kinsList.add(kin);
		}
		
		// test if all empty
		OutputStreamWriter out;
		try {
			out = new OutputStreamWriter(openFileOutput(SET, 0));
			JsonWriter writer = new JsonWriter(out);
			writer.setIndent("  ");
			writeWordSetArray(writer, kinsList);
			writer.close();
			Toast.makeText(this, "Your set has been saved", Toast.LENGTH_LONG)
					.show();
		} catch (Throwable t) {
			Toast.makeText(this, "Your set could not be saved" + t.toString(),
					Toast.LENGTH_LONG).show();
			t.printStackTrace();
		}

		// check if all fields empty
		// try {
		// for (int i = 0; i < count; i++) {
		// if (i != titleIndex) {
		// EditText editText = (EditText) add_set_view.getChildAt(i);
		// out.write(editText.getText().toString());
		// // à remplacer par writeJsonStream(out, words)
		// }
		// }
		// out.close();
		// Toast.makeText(this, "Your set has been saved", Toast.LENGTH_LONG)
		// .show();
		// } catch (Throwable t) {
		// Toast.makeText(this, "Your set could not be saved" + t.toString(),
		// Toast.LENGTH_LONG).show();
		// }

		// if (word1.getText().toString().isEmpty()) {
		// Toast.makeText(this, "The set is empty", Toast.LENGTH_LONG).show();
		// return;
		// }
	}

	public void readSet() {

		InputStream in;
		try {
			in = openFileInput(SET);
			if (in != null) {
				List<Kin> kinsList;
				kinsList = readJsonStream(in);
				Log.d("list", kinsList.get(0).toString());

				storedSetView.setText(kinsList.get(0).getWordLg1() + " = "
						+ kinsList.get(0).getWordLg2());
				
//				String kins ="";
//				for(int i=0 ; i<kinsList.size() ; i++){
//					kins.concat(kinsList.get(i).getWordLg1() + " = "
//						+ kinsList.get(i).getWordLg2()
//						+ ". ");
//				}
//				storedSetView.setText(kins);
				
				
//				+ kinsList.get(0).getWordLg1() + " = "
//				+ kinsList.get(0).getWordLg2()
			}
		} catch (FileNotFoundException e) {
			// OK, not created yet
		} catch (Throwable t) {
			Toast.makeText(this, "Exception: " + t.toString(),
					Toast.LENGTH_LONG).show();
		}
	}

	public void writeJsonStream(OutputStream out, List<Kin> kinsList)
			throws JSONException, IOException {
		JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
		writer.setIndent("  ");
		writeWordSetArray(writer, kinsList);
		writer.close();
	}

	public void writeWordSetArray(JsonWriter writer, List<Kin> kinsList)
			throws IOException {
		writer.beginArray();
		for (Kin kin : kinsList) {
			writer.beginObject();
			writer.name("lg1").value(kin.getWordLg1());
			writer.name("lg2").value(kin.getWordLg2());
			writer.endObject();
		}
		writer.endArray();
	}

	public List readJsonStream(InputStream in) throws IOException {
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		try {
			return readKinsArray(reader);
		} finally {
			reader.close();
		}
	}

	public List readKinsArray(JsonReader reader) throws IOException {
		List kinsList = new ArrayList();

		reader.beginArray();
		while (reader.hasNext()) {
			kinsList.add(readKins(reader));
		}
		reader.endArray();
		return kinsList;
	}

	public Kin readKins(JsonReader reader) throws IOException {
		String wordLg1 = null;
		String wordLg2 = null;
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("lg1")) {
				wordLg1 = reader.nextString();
			} else if (name.equals("lg2")) {
				wordLg2 = reader.nextString();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return new Kin(wordLg1, wordLg2);
	}

}
