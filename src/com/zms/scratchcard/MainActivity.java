package com.zms.scratchcard;

import android.app.Activity;
import android.os.Bundle;

import com.zms.scratchcard.util.HintUtil;
import com.zms.scratchcard.view.ScratchCard;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initialLayout();
	}

	private void initialLayout() {
		ScratchCard scratchCard1 = (ScratchCard) findViewById(R.id.scratchCard1);
		scratchCard1.setOnCompleteListener(new MyOnCompleteListener());

		ScratchCard scratchCard2 = (ScratchCard) findViewById(R.id.scratchCard2);
		scratchCard2.setOnCompleteListener(new MyOnCompleteListener());

		ScratchCard scratchCard3 = (ScratchCard) findViewById(R.id.scratchCard3);
		scratchCard3.setOnCompleteListener(new MyOnCompleteListener());

		ScratchCard scratchCard4 = (ScratchCard) findViewById(R.id.scratchCard4);
		scratchCard4.setOnCompleteListener(new MyOnCompleteListener());

		ScratchCard scratchCard5 = (ScratchCard) findViewById(R.id.scratchCard5);
		scratchCard5.setOnCompleteListener(new MyOnCompleteListener());

	}

	class MyOnCompleteListener implements ScratchCard.OnCompleteListener {

		@Override
		public void complete(ScratchCard scratchCard, String content) {
			switch (scratchCard.getId()) {
			case R.id.scratchCard1:
				HintUtil.showToast(MainActivity.this, "Card 1:" + content);
				break;

			case R.id.scratchCard2:
				HintUtil.showToast(MainActivity.this, "Card 2:" + content);
				break;

			case R.id.scratchCard3:
				HintUtil.showToast(MainActivity.this, "Card 3:" + content);
				break;

			case R.id.scratchCard4:
				HintUtil.showToast(MainActivity.this, "Card 4:" + content);
				break;

			case R.id.scratchCard5:
				HintUtil.showToast(MainActivity.this, "Card 5:" + content);
				break;

			default:
				break;
			}
		}

	}
}
