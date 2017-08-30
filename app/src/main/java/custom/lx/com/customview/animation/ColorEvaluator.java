package custom.lx.com.customview.animation;

import android.animation.TypeEvaluator;

/**
 * Created by 11300 on 2017/8/28.
 */

public class ColorEvaluator implements TypeEvaluator{

    private int mCurrentRed = -1;
    private int mCurrentGreen = -1;
    private int mCurrentBlue = -1;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor = (String) startValue;
        String endColor = (String) endValue;
        //字符串截取的方式将颜色分为RGB三个部分
        int startRed = Integer.parseInt(startColor.substring(1,3), 16);
        int startGreen = Integer.parseInt(startColor.substring(3,5), 16);
        int startBlue = Integer.parseInt(startColor.substring(5,7), 16);
        int endRed = Integer.parseInt(endColor.substring(1,3), 16);
        int endGreen = Integer.parseInt(endColor.substring(3,5), 16);
        int endBlue = Integer.parseInt(endColor.substring(5,7), 16);

        //初始化颜色值
        if (mCurrentRed == -1) {
            mCurrentRed = startRed;
        }
        if (mCurrentGreen == -1) {
            mCurrentGreen = startGreen;
        }
        if (mCurrentBlue == -1) {
            mCurrentBlue = startBlue;
        }

        int redDiff = Math.abs(startRed - endRed);
        int greenDiff = Math.abs(startGreen - endGreen);
        int blueDiff = Math.abs(startBlue = endBlue);

        int colorDiff = redDiff + greenDiff + blueDiff;

        //先变红色再变绿色再变蓝色
        if (mCurrentRed != endRed) {
            mCurrentRed = getCurrentColor(startRed, endRed, colorDiff, 0, fraction);
        } else if (mCurrentGreen != endGreen) {
            mCurrentGreen = getCurrentColor(startGreen, endGreen, colorDiff, redDiff, fraction);
        } else if (mCurrentBlue != endBlue) {
            mCurrentBlue = getCurrentColor(startBlue, endBlue, colorDiff, redDiff + greenDiff, fraction);
        }

        String currentColor = "#" + getHexString(mCurrentRed)
                + getHexString(mCurrentGreen) + getHexString(mCurrentBlue);

        return currentColor;
    }

    /**
     * 根据fraction值计算当前颜色
     * @param startColor
     * @param endColor
     * @param colorDiff
     * @param offSet 补偿
     * @param fraction
     * @return
     */
    private int getCurrentColor(int startColor, int endColor, int colorDiff,
                                int offSet, float fraction) {
        int currentColor;
        if (startColor > endColor) {
            currentColor = (int) (startColor - (fraction * colorDiff - offSet));
            if (currentColor < endColor) {
                currentColor = endColor;
            }
        } else {
            currentColor = (int) (startColor + (fraction * colorDiff - offSet));
            if (currentColor > endColor) {
                currentColor = endColor;
            }
        }
        return currentColor;
    }

    /**
     * 将10进制颜色值转换成16进制。
     */
    private String getHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        return hexString;
    }
}
