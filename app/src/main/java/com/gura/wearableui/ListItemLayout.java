package com.gura.wearableui;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.wearable.view.WearableListView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2017-02-28.
 */

public class ListItemLayout extends LinearLayout
                implements WearableListView.Item{
    //현재 배율을 저장할 맴버필드
    float mScale;
    float mFadedTextAlpha;
    int mFadedCircleColor;
    int mChosenCircleColor;
    ImageView mCircle;
    TextView mName;

    public ListItemLayout(Context context) {
        super(context, null);
    }

    public ListItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ListItemLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }
    //레이아웃(각각의 아이템) 전개가 완료 되었을때 호출되는 메소드
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //fade 되었을때 투명도
        mFadedTextAlpha = 40/100f;
        //fade 되었을때 색상
        mFadedCircleColor = getResources().getColor(R.color.my_gray);
        //선택 되었을때 색상
        mChosenCircleColor = getResources().getColor(R.color.my_blue);

        mCircle = (ImageView) findViewById(R.id.circle);
        mName = (TextView) findViewById(R.id.text_name);
    }
    //최소 크기 배율
    @Override
    public float getProximityMinValue() {
        return 1f; //원본크기
    }
    //최대 크기 배율
    @Override
    public float getProximityMaxValue() {
        return 1.6f; //원본 크기의 1.5배
    }
    //현재 스케일 값을 리턴해주는 메소드
    @Override
    public float getCurrentProximityValue() {
        return mScale;
    }
    //현재 스케일 value 값이 인자로 전달되는 메소드
    @Override
    public void setScalingAnimatorValue(float scale) {
        //인자로 전달되는 현재 배율값을 맴버필드에 저장한다.
        mScale=scale;
        mCircle.setScaleX(scale);
        mCircle.setScaleY(scale);
    }
    //확대가 시작되었을때 호출되는 메소드
    @Override
    public void onScaleUpStart() {
        //TextView 를 완전 불투명하게 한다.
        mName.setAlpha(1f);
        //원의 색상을 파란색으로 바꿔준다.
        ((GradientDrawable) mCircle.getDrawable())
                .setColor(mChosenCircleColor);
    }
    //축소가 시작되었을때 호출되는 메소드
    @Override
    public void onScaleDownStart() {
        //원의 색상을 회색으로 바꿔준다.
        ((GradientDrawable) mCircle.getDrawable())
                .setColor(mFadedCircleColor);
        //TextView 를 약간 투명하게 한다.
        mName.setAlpha(mFadedTextAlpha);
    }
}
