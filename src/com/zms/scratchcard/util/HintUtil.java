package com.zms.scratchcard.util;

import android.content.Context;
import android.widget.Toast;

public class HintUtil {

	public HintUtil() {
	}

	public static void showToast(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
}
