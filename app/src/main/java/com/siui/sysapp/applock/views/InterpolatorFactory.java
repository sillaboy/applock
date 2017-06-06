package com.siui.sysapp.applock.views;

import android.view.animation.Interpolator;

import com.siui.sysapp.applock.views.Eases.EaseType;


public class InterpolatorFactory {

    public static BLVInterpolator getInterpolator(EaseType easeType) {
        return new BLVInterpolator(easeType);
    }

    public static class BLVInterpolator implements Interpolator {

        private EaseType easeType;

        public BLVInterpolator(EaseType easeType) {
            this.easeType = easeType;
        }

        @Override
        public float getInterpolation(float input) {
            return easeType.getOffset(input);
        }
    }

}
