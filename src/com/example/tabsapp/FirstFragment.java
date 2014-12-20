package com.example.tabsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_sets, container, false);

		// TextView tv = (TextView) v.findViewById(R.id.tvFragFirst);
		// tv.setText(getArguments().getString("msg"));

		return view;
	}

	public static FirstFragment newInstance(String text) {

		FirstFragment fragment = new FirstFragment();
		Bundle bundle = new Bundle();
		bundle.putString("msg", text);

		fragment.setArguments(bundle);

		return fragment;
	}
}
