package tool.net;

import com.android.volley.VolleyError;

public interface ApplyCallback {
	void NetFailed(VolleyError error);

	void onReturned(String response);
}
