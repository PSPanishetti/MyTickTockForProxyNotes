package in.codeworld.myticktock;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

import in.codeworld.myticktock.interfaces.SwipeActions;


public class DetectSwipeGesture implements GestureDetector.OnGestureListener {


        private  String TAG="DetectSwipeGesture";
        private final int MIN_X_SWIPE_DISTANCE=180;
        private int MIN_Y_SWIPE_DISTANCE=180;
        private SwipeActions swipeActions;


        public DetectSwipeGesture(SwipeActions actions) {
            swipeActions=actions;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


            int distanceSwipedInX= (int) (e1.getX()-e2.getX());
            int distanceSwipedInY= (int) (e1.getY()-e2.getY());

            // Make Check For Horizontal Swipe

             if(Math.abs(distanceSwipedInX)>MIN_X_SWIPE_DISTANCE)
             {
                 // Now Check Which Side Swipe Happened
                 if(distanceSwipedInX>0)
                 {
                     swipeActions.onSwipeLeft();
                 }else {
                     swipeActions.onSwipeRight();


                 }

                 return true;


             }

            if(Math.abs(distanceSwipedInY)>MIN_Y_SWIPE_DISTANCE)
            {
                if(distanceSwipedInY>0)
                {
                    swipeActions.onSwipeUp();

                }else {
                    swipeActions.onSwipeDown();

                }

            }


            return false;


           // Toast.makeText(context, "Something Done ", Toast.LENGTH_SHORT).show();

        }
    }

