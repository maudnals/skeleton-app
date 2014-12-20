package com.example.tabsapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

public class FirstFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_sets, container, false);

		GridView gridview = (GridView) view.findViewById(R.id.gridview);

		gridview.setAdapter(new GridAdapterImageText(getActivity(), web,
				imageId));

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

	String[] web = { "Google", "Github", "Instagram", "Facebook", "Flickr" };
	int[] imageId = { R.drawable.ic_action_play, R.drawable.ic_action_play,
			R.drawable.ic_action_play, R.drawable.ic_action_play,
			R.drawable.ic_action_play };
}
