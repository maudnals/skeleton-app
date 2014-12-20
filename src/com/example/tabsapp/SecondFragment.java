package com.example.tabsapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_stats, container, false);

		// TextView tv = (TextView) v.findViewById(R.id.tvFragSecond);
		// tv.setText(getArguments().getString("msg"));

		return view;
	}

	public static SecondFragment newInstance(String text) {

		SecondFragment fragment = new SecondFragment();
		Bundle bundle = new Bundle();
		bundle.putString("msg", text);

		fragment.setArguments(bundle);

		return fragment;
	}
}
