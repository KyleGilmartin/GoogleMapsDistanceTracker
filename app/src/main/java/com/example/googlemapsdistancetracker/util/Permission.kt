package com.example.googlemapsdistancetracker.util

import android.content.Context
import android.os.Build
import androidx.fragment.app.Fragment
import com.example.googlemapsdistancetracker.util.Constants.PERMISSION_BACKGROUND_LOCATION_REQUEST_CODE
import com.example.googlemapsdistancetracker.util.Constants.PERMISSION_LOCATION_REQUEST_CODE
import com.vmadalin.easypermissions.EasyPermissions

object Permission {


    fun hasLocationPermission(context: Context) =
        EasyPermissions.hasPermissions(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )


    fun requestLocationPermission(fragment: Fragment){
        EasyPermissions.requestPermissions(
            fragment,
            "This app can not work without permissions",
            PERMISSION_LOCATION_REQUEST_CODE,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    fun hasBackGroundLocationPermission(context: Context):Boolean{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            return EasyPermissions.hasPermissions(
                context,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }

        return true
    }

    fun requestBackgroundLocationPermission(fragment: Fragment){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            return EasyPermissions.requestPermissions(
               fragment,
                "Background location permission is needed to to run this app",
                PERMISSION_BACKGROUND_LOCATION_REQUEST_CODE,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }
}