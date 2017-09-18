package us.koller.cameraroll.ui.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import us.koller.cameraroll.util.Util;

@SuppressWarnings("unused")
public class FabSnackbarBehaviour extends CoordinatorLayout.Behavior<FloatingActionButton> {

    private float fabTranslation = -1;

    public FabSnackbarBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        return Util.SNACKBAR.equals(dependency.getTag());
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
        if (Util.SNACKBAR.equals(dependency.getTag())) {
            if (fabTranslation == -1) {
                fabTranslation = fab.getTranslationY();
            }
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            int fabBottomMargin = lp.bottomMargin;

            float fabBottom = fab.getY() + fab.getHeight() + fabBottomMargin;
            float delta = dependency.getY() - fabBottom;
            float translationY = fab.getTranslationY() + delta;
            fab.setTranslationY(translationY < fabTranslation ?
                    translationY : fabTranslation);
        }
        return true;
    }
}