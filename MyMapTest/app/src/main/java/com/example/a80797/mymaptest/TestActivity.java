package com.example.a80797.mymaptest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.example.a80797.mymaptest.view.FeatureView;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends CheckPermissionsActivity implements INaviInfoCallback {

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }

    @Override
    public void onReCalculateRoute(int i) {

    }

    @Override
    public void onExitPage(int i) {

    }

    @Override
    public void onStrategyChanged(int i) {

    }

    @Override
    public View getCustomNaviBottomView() {
        return null;
    }

    @Override
    public View getCustomNaviView() {
        return null;
    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onMapTypeChanged(int i) {

    }

    @Override
    public View getCustomMiddleView() {
        return null;
    }

    @Override
    public void onNaviDirectionChanged(int i) {

    }

    @Override
    public void onDayAndNightModeChanged(int i) {

    }

    @Override
    public void onBroadcastModeChanged(int i) {

    }

    @Override
    public void onScaleAutoChanged(boolean b) {

    }
    LatLng p1 = new LatLng(39.993266, 116.473193);//首开广场
    LatLng p2 = new LatLng(39.917337, 116.397056);//故宫博物院
    LatLng p3 = new LatLng(39.904556, 116.427231);//北京站
    LatLng p4 = new LatLng(39.773801, 116.368984);//新三余公园(南5环)
    LatLng p5 = new LatLng(40.041986, 116.414496);//立水桥(北5环)

    private ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initView();
       // AmapNaviPage.getInstance().showRouteActivity(this, new AmapNaviParams(null), TestActivity.this);
    }
    private AdapterView.OnItemClickListener mOnItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position==1)
            {
                AmapNaviParams params=new AmapNaviParams(new Poi("北京站",p3,""),null,new Poi("故宫博物馆",p2,""),AmapNaviType.DRIVER);
                params.setUseInnerVoice(true);
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(),params,TestActivity.this);
            }
            else if(position==2){
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(),new AmapNaviParams(null,null,new Poi("故宫博物馆",p2,""),AmapNaviType.DRIVER),TestActivity.this);
            }
            else if (position==3)
            {
                List<Poi> poiList=new ArrayList<>();
                poiList.add(new Poi("首开广场",p1,""));
                poiList.add(new Poi("故宫博物馆",p2,""));
                poiList.add(new Poi("北京站",p3,""));
                AmapNaviParams params=new AmapNaviParams( new Poi("立交桥",p5,""),poiList, new Poi("新三余公园(南5环)", p4, ""), AmapNaviType.DRIVER);
                params.setUseInnerVoice(true);
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(),params,TestActivity.this);
            }
            else if(position==4)
            {
                //起点
                Poi start = new Poi("立水桥(北5环)", p5, "");
                //途经点
                List<Poi> poiList = new ArrayList();
                poiList.add(new Poi("首开广场", p1, ""));
                poiList.add(new Poi("故宫博物院", p2, ""));
                poiList.add(new Poi("北京站", p3, ""));
                //终点
                Poi end = new Poi("新三余公园(南5环)", p4, "");
                AmapNaviParams amapNaviParams = new AmapNaviParams(start, poiList, end, AmapNaviType.DRIVER, AmapPageType.NAVI);
                amapNaviParams.setUseInnerVoice(true);
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), amapNaviParams, TestActivity.this);
            }
            else if (position==5)
            {
                AmapNaviParams params=new AmapNaviParams(new Poi("北京站",p3,""),null,new Poi("故宫博物馆",p2,""),AmapNaviType.DRIVER);
                params.setUseInnerVoice(true);
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(),params,TestActivity.this);
            }

        }
    };
    private void initView() {
        ListView listView=findViewById(R.id.list);
        adapter=new CustpmArrayAdapter(this.getApplicationContext(),DEMOS);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(mOnItemClickListener);
    }

    private static class CustpmArrayAdapter extends ArrayAdapter<DemoDetails>
    {

        public CustpmArrayAdapter( Context context, DemoDetails[] details) {
            super(context, R.layout.feature,R.id.title,details);
        }


        @Override
        public View getView(int position,  View convertView,  ViewGroup parent) {
            FeatureView featureView;
            if(convertView instanceof FeatureView)
            {
                featureView= (FeatureView) convertView;
            }
            else {
                featureView=new FeatureView(getContext());
            }
            DemoDetails details=getItem(position);
            featureView.setTitleId(details.titleId,details.activityClass!=null);
            return  featureView;
        }
    }
    private static final DemoDetails[] DEMOS = {
//		    // 导航组件
            new DemoDetails(R.string.navi_ui, R.string.blank, null),
            // 组件起终点算路
            new DemoDetails(R.string.navi_start_end_poi_calculate_title, R.string.navi_start_end_poi_calculate_desc, TestActivity.class),
            // 组件起终点算路
            new DemoDetails(R.string.navi_end_poi_calculate_title, R.string.navi_end_poi_calculate_desc, TestActivity.class),
            // 组件起终点算路
            new DemoDetails(R.string.navi_bywayof_poi_calculate_title, R.string.navi_bywayof_poi_calculate_desc, TestActivity.class),
            // 直接导航
            new DemoDetails(R.string.navi_ui_navi_title, R.string.navi_ui_navi_desc, TestActivity.class),
            // 组件起终点算路（白色主题）
            new DemoDetails(R.string.navi_ui_custom_activity, R.string.navi_ui_custom_activity, TestActivity.class),
    };
    private static class DemoDetails {
        private final int titleId;
        private final int descriptionId;
        private final Class<? extends android.app.Activity> activityClass;

        public DemoDetails(int titleId, int descriptionId,
                           Class<? extends android.app.Activity> activityClass) {
            super();
            this.titleId = titleId;
            this.descriptionId = descriptionId;
            this.activityClass = activityClass;
        }
    }




}