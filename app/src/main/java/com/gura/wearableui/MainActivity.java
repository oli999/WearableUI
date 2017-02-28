package com.gura.wearableui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.wearable.view.WearableListView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
        implements WearableListView.ClickListener{

    //필요한 맴버필드 정의하기
    WearableListView wearListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rect_activity_main);
        //WearableListview 객체의 참조값 얻어오기
        wearListView=(WearableListView)findViewById(R.id.wearListView);

        List<String> friends=new ArrayList<>();
        friends.add("김구라1");
        friends.add("김구라2");
        friends.add("김구라3");
        friends.add("김구라4");
        friends.add("김구라5");
        friends.add("김구라6");
        friends.add("김구라7");
        friends.add("김구라8");

        //아답타 객체를 생성해서
        MyAdapter adapter=new MyAdapter(this, R.layout.list_cell, friends);
        //WearableListView 에 연결한다.
        wearListView.setAdapter(adapter);
        //WearableListView 의 셀 click 리스너 등록
        wearListView.setClickListener(this);
    }
    //활성화된 셀을 클릭했을때 호출되는 메소드
    @Override
    public void onClick(WearableListView.ViewHolder viewHolder) {
        //인자로 전달된 ViewHolder 객체에서 View 의 tag 을 읽어온다.
        int position = (Integer)viewHolder.itemView.getTag();
        Toast.makeText(this,"index:"+position,Toast.LENGTH_SHORT).show();
    }
    //WearableListView 의 위쪽 빈영역을 클릭했을때 호출되는 메소드
    @Override
    public void onTopEmptyRegionClick() {
        Toast.makeText(this,"empty click!",Toast.LENGTH_SHORT).show();
    }

    /*
    ListView 에 데이터(ViewHolder)를 공급할 아답타 클래스 정의하기

    -WearableListView.Adapter 추상클래스를 상속 받아서 만든다.

 */
    class MyAdapter extends WearableListView.Adapter{
        //맴버필드 정의하기
        Context context;
        LayoutInflater inflater; //레이아웃 전개자 객체
        List<String> list;
        int layoutRes;
        //생성자로 Context, 레이아웃 리소스 아이디, 모델 을 전달 받는다.
        public MyAdapter(Context context,int layoutRes,
                         List<String> list){
            this.context=context;
            this.layoutRes=layoutRes;
            this.list=list;
            //레이아웃 전개자 객체의 참조값을 맴버필드에 저장한다.
            inflater=LayoutInflater.from(context);
        }
        //ViewHolder 객체를 만들어서 리턴해주는 메소드
        @Override
        public WearableListView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            //레이아웃을 전개해서 View 객체를 만든다.
            View view=inflater.inflate(layoutRes, null);
            //View 객체를 이용해서 ViewHolder 객체를 만든다.
            WearableListView.ViewHolder holder=
                    new WearableListView.ViewHolder(view);
            //만든 객체의 참조값 리턴해주기
            return holder;
        }
        //모델의 데이터가 바인딩 될때 호출되는 메소드
        @Override
        public void onBindViewHolder(WearableListView.ViewHolder viewHolder, int position) {
            //TextView 의 참조값을 얻어온다.
            TextView view=(TextView)
                    viewHolder.itemView.findViewById(R.id.text_name);
            //Model 에서 해당 position 의 데이터 읽어오기
            String name=list.get(position);
            //TextView 에 출력한다.
            view.setText(name);
            //position 으로 텍(꼬리표) 를 붙인다.
            viewHolder.itemView.setTag(position);

        }
        //모델의 갯수를 리턴해주는 메소드
        @Override
        public int getItemCount() {
            return list.size();
        }
    }

}
















