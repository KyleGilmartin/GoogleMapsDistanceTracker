package com.example.googlemapsdistancetracker.service

import android.content.Intent
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import com.example.googlemapsdistancetracker.util.Constants.ACTION_SERVICE_START
import com.example.googlemapsdistancetracker.util.Constants.ACTION_SERVICE_STOP
import com.google.android.gms.maps.model.LatLng

class TrackerService:LifecycleService() {

    companion object {
        val started = MutableLiveData<Boolean>()
        val startTime = MutableLiveData<Long>()
        val stopTime = MutableLiveData<Long>()

        val locationList = MutableLiveData<MutableList<LatLng>>()
    }

    private fun setInitialValues() {
        started.postValue(false)
        startTime.postValue(0L)
        stopTime.postValue(0L)

        locationList.postValue(mutableListOf())
    }

    override fun onCreate() {
        setInitialValues()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_SERVICE_START -> {
                    started.postValue(true)
//                    startForegroundService()
//                    startLocationUpdates()
                }
                ACTION_SERVICE_STOP -> {
                    started.postValue(false)
//                    stopForegroundService()
                }
                else -> {
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }


}