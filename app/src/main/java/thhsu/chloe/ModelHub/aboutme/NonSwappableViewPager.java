package thhsu.chloe.ModelHub.aboutme;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by Chloe on 5/5/2018.
 */

public class NonSwappableViewPager extends ViewPager {

    public NonSwappableViewPager(@NonNull Context context) {
        super(context);
        setMyScroller();

    }

    public NonSwappableViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
        setMyScroller();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //Never allow swiping to switch between pages
        return false;
    }

    private void setMyScroller(){ //for smooth transition
        try{
            Class<?> viewPager = ViewPager.class;
            Field scroller = viewPager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public class MyScroller extends Scroller{
        MyScroller(Context context){
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 350);
        }
    }
}
