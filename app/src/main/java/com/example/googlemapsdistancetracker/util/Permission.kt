package com.example.googlemapsdistancetracker.util

import android.content.Context
import androidx.fragment.app.Fragment
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
}