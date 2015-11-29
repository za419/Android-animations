package com.ryan.Animations;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.animation.*;
import android.view.animation.*;
import android.graphics.*;
import android.util.*;
import java.util.*;
import android.graphics.drawable.*;

public class MainActivity extends Activity
{
	Animation outAnim;
	View currentView;
	final MainActivity t=this;
	Random gen=new Random();
	boolean isBackgroundWhite;
	final Runnable jackRunnable=new Runnable()
	{
		@Override
		public void run()
		{
			ViewGroup c=(ViewGroup)findViewById(R.id.wordsLayout);
			TextView tv=new TextView(t);
			String s="All work and no play makes Jack a dull boy";
			String str="";
			for (int i=0; i<s.length(); ++i)
			{
				if (0!=gen.nextInt(20))
				{
					str+=(0==gen.nextInt(30) ? Character.toUpperCase(s.charAt(i)) : s.charAt(i));
				}
			}
			tv.setText(str);
			tv.setTextColor(isBackgroundWhite ? Color.BLACK : Color.WHITE);
			int bright=gen.nextInt(75)+75;
			tv.setBackgroundColor(Color.rgb(bright, bright, bright));
			try{c.addView(tv);
			c.postDelayed(this, 500+gen.nextInt(250));}
			catch (NullPointerException ne) {}
		}
	};
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		outAnim=AnimationUtils.loadAnimation(this, R.anim.fadeout);
		currentView=findViewById(R.id.mainLayout);
		isBackgroundWhite=false;
		if (Build.VERSION.SDK_INT>=11)
			getActionBar().hide();
    }

	@Override
	public void onBackPressed()
	{
		if (currentView==null)
			return;
		Runnable r=(currentView.getId()!=R.id.mainLayout) ? new Runnable()
		{
			@Override
			public void run()
			{
				setContentView(R.layout.main);
				currentView=findViewById(R.id.mainLayout);
				outAnim=AnimationUtils.loadAnimation(t, R.anim.fadeout);
				isBackgroundWhite=false;
			}
		}:
		new Runnable()
		{
			@Override
			public void run()
			{
				finish();
			}
		};
		currentView.startAnimation(outAnim);
		currentView.postDelayed(r, outAnim.getDuration());
		currentView=null;
	}
	
	public void switchLayout(final View source)
	{
		if (currentView==null)
			return;
		Runnable r=new Runnable()
		{
			@Override
			public void run()
			{
				switch (source.getId())
				{
				case R.id.mainFadeButton:
					setContentView(R.layout.fade);
					currentView=findViewById(R.id.fadeLayout);
					outAnim=AnimationUtils.loadAnimation(t, R.anim.fadeout);
					isBackgroundWhite=false;
					currentView.postDelayed(jackRunnable, 7000+gen.nextInt(2500));
					break;
				case R.id.mainScaleButton:
					setContentView(R.layout.scale);
					currentView=findViewById(R.id.scaleLayout);
					outAnim=AnimationUtils.loadAnimation(t, R.anim.scaleout);
					isBackgroundWhite=true;
					currentView.postDelayed(jackRunnable, 6000+gen.nextInt(2500));
					break;
				case R.id.mainSpinFadeButton:
					setContentView(R.layout.spinfade);
					currentView=findViewById(R.id.spinFadeLayout);
					outAnim=AnimationUtils.loadAnimation(t, R.anim.spinout);
					isBackgroundWhite=true;
					currentView.postDelayed(jackRunnable, 5000+gen.nextInt(2500));
					break;
				case R.id.mainSpinScaleButton:
					setContentView(R.layout.spinscale);
					currentView=findViewById(R.id.spinScaleLayout);
					outAnim=AnimationUtils.loadAnimation(t, R.anim.spinoutscale);
					isBackgroundWhite=true;
					currentView.postDelayed(jackRunnable, 4000+gen.nextInt(2500));
					break;
				case R.id.mainSlideLeftButton:
					setContentView(R.layout.slideleft);
					currentView=findViewById(R.id.slideInLeftLayout);
					outAnim=AnimationUtils.loadAnimation(t, R.anim.slideoutright);
					currentView.postDelayed(jackRunnable, 3000+gen.nextInt(2500));
					break;
				case R.id.mainSlideRightButton:
					setContentView(R.layout.slideright);
					currentView=findViewById(R.id.slideInRightLayout);
					outAnim=AnimationUtils.loadAnimation(t, R.anim.slideoutleft);
					currentView.postDelayed(jackRunnable, 2000+gen.nextInt(2500));
					break;
				case R.id.mainSlideTopButton:
					setContentView(R.layout.slidetop);
					currentView=findViewById(R.id.slideInTopLayout);
					outAnim=AnimationUtils.loadAnimation(t, R.anim.slideoutdown);
					currentView.postDelayed(jackRunnable, 1000+gen.nextInt(2500));
					break;
				case R.id.mainSlideBottomButton:
					setContentView(R.layout.slidebottom);
					currentView=findViewById(R.id.slideInBottomLayout);
					outAnim=AnimationUtils.loadAnimation(t, R.anim.slideoutup);
					currentView.postDelayed(jackRunnable, gen.nextInt(2500));
					break;
				case R.id.mainQuitButton:
				default: // Duplicate case
					finish();
					break;
				}
			}
		};
		currentView.startAnimation(outAnim);
		currentView.postDelayed(r, outAnim.getDuration());
		currentView=null;
	}
}
