package com.example.a80797.mymaptest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.autonavi.base.amap.mapcore.maploader.AMapLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity implements AMapLocationListener {

   private TextView tvLocation;
//
    private AMapLocationClient locationClient=null;
   private AMapLocationClientOption locationClientOption =null;
//   定位参数类
//    权限的集合
    List<String> permissionList=new ArrayList<>();


    public void checkAllPermission()
    {
        //定位
        if (ContextCompat.checkSelfPermission(LocationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        //手机访问
        if (ContextCompat.checkSelfPermission(LocationActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        //存储
        if (ContextCompat.checkSelfPermission(LocationActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        //如果非空就表示有权限没有加入
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LocationActivity.this, permissions, 1);
        } else {
            //启动定位服务
            locationClient.startLocation();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tvLocation = findViewById(R.id.location_city);

//初始化定位
        locationClient = new AMapLocationClient(getApplicationContext());
        locationClient.setLocationListener(this);
//检查权限
        checkAllPermission();
//定位参数设置
        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        locationClient.setLocationOption(locationClientOption);

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {

            if (aMapLocation.getErrorCode() == 0) {

//                tvLocation.setText("纬度 " + aMapLocation.getLatitude() + "\n" +
//                        "经度 " + aMapLocation.getLongitude() + "\n" +
//                        aMapLocation.getAddress());
//                StringBuilder currentPostion=new StringBuilder();
//                currentPostion.append("纬度：").append(aMapLocation.getLatitude()+"\n");
//                currentPostion.append("经度:").append(aMapLocation.getLongitude()+"\n");
//                currentPostion.append("获得方式:").append(aMapLocation.getLocationType());
//                currentPostion.append("地址：").append(aMapLocation.getAddress());
                tvLocation.setText(aMapLocation.getCity());

            } else {

                tvLocation.setText("定位失败, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }

    }
}
