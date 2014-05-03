package com.quentindommerc.dublinbikes.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.quentindommerc.dublinbikes.interfaces.OnApiFinished;

public class Api {

	private final AsyncHttpClient client;

	public Api() {
		client = new AsyncHttpClient();
	}

	public void execute(String url, RequestParams params,
			final OnApiFinished listener) {
		if (params == null)
			params = new RequestParams();
		url = Constants.BASE_URL + "/" + url;
		Utils.log(AsyncHttpClient.getUrlWithQueryString(true, url, params));

		client.get(url, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, String arg1) {
				super.onSuccess(arg0, arg1);
//				Utils.log(arg1);
				listener.success(arg1);
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				super.onFailure(arg0, arg1);
				Utils.log("Error : " + arg1);
				listener.error(arg1);
			}
		});
	}

}
