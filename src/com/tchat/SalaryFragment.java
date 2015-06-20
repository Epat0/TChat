package com.tchat;

import android.content.SharedPreferences;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SalaryFragment extends Fragment {

	WebView myWebView;
	String myUrl;
	String mobile;
	
	public SalaryFragment(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.layout_webfragment, container,
				false);
		myWebView = (WebView) view.findViewById(R.id.mywebview);

		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		myWebView.getSettings().setAllowFileAccess(true);
		myWebView.getSettings().setAppCacheEnabled(true);
		myWebView.getSettings().setSaveFormData(false);
		myWebView.getSettings().setLoadsImagesAutomatically(true);
		myWebView.getSettings().setDefaultTextEncodingName("GBK");
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.loadUrl("http://zhangpingzhong.gotoip1.com/" + mobile
				+ ".txt");
		return view;

	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);
	}
}
