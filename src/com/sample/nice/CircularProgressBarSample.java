package com.sample.nice;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.nice.progress.CircularProgressBar;

/**
 * The Class CircularProgressBarSample.
 *
 * @since 05.04.2014
 */
public class CircularProgressBarSample extends Activity {

	private static final String TAG = CircularProgressBarSample.class.getSimpleName();

	private CircularProgressBar mHoloCircularProgressBar;
	private ObjectAnimator mProgressBarAnimator;
	private Button mTwenty;
	private Button mFull;
    private Button mEighty;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		if (getIntent() != null) {
			final Bundle extras = getIntent().getExtras();
			if (extras != null) {
				final int theme = extras.getInt("theme");
				if (theme != 0) {
					setTheme(theme);
				}
			}
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mHoloCircularProgressBar = (CircularProgressBar) findViewById(R.id.holoCircularProgressBar1);

		mTwenty = (Button) findViewById(R.id.twenty);
		mTwenty.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mProgressBarAnimator != null) {
                    mProgressBarAnimator.cancel();
                }
                animate(mHoloCircularProgressBar, null, 0.2f, 1000);
            }
        });

		mFull = (Button) findViewById(R.id.full);
		mFull.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mProgressBarAnimator != null) {
                    mProgressBarAnimator.cancel();
                }
                animate(mHoloCircularProgressBar, null, 1f, 1000);
            }
        });

        mEighty = (Button) findViewById(R.id.eighty);

        mEighty.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mProgressBarAnimator != null) {
                    mProgressBarAnimator.cancel();
                }
                animate(mHoloCircularProgressBar, null, 0.8f, 1000);
            }
        });
	}

	/**
	 * Animate.
	 * 
	 * @param progressBar
	 *            the progress bar
	 * @param listener
	 *            the listener
	 */
	private void animate(final CircularProgressBar progressBar, final AnimatorListener listener) {
		final float progress = (float) (Math.random() * 2);
		int duration = 3000;
		animate(progressBar, listener, progress, duration);
	}

	private void animate(final CircularProgressBar progressBar, final AnimatorListener listener,
			final float progress, final int duration) {

		mProgressBarAnimator = ObjectAnimator.ofFloat(progressBar, "progress", progress);
		mProgressBarAnimator.setDuration(duration);

		mProgressBarAnimator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationCancel(final Animator animation) {
			}

			@Override
			public void onAnimationEnd(final Animator animation) {
				progressBar.setProgress(progress);
			}

			@Override
			public void onAnimationRepeat(final Animator animation) {
			}

			@Override
			public void onAnimationStart(final Animator animation) {
			}
		});
		if (listener != null) {
			mProgressBarAnimator.addListener(listener);
		}
		mProgressBarAnimator.reverse();
		mProgressBarAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(final ValueAnimator animation) {
				progressBar.setProgress((Float) animation.getAnimatedValue());
			}
		});
		mProgressBarAnimator.start();
	}

}
