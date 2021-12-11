package com.example.googlemapsdistancetracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.googlemapsdistancetracker.databinding.FragmentMapsBinding
import com.example.googlemapsdistancetracker.service.TrackerService
import com.example.googlemapsdistancetracker.util.Constants.ACTION_SERVICE_START
import com.example.googlemapsdistancetracker.util.ExtensionFunctions.disable
import com.example.googlemapsdistancetracker.util.ExtensionFunctions.enable
import com.example.googlemapsdistancetracker.util.ExtensionFunctions.hide
import com.example.googlemapsdistancetracker.util.ExtensionFunctions.show
import com.example.googlemapsdistancetracker.util.Permission.hasBackGroundLocationPermission
import com.example.googlemapsdistancetracker.util.Permission.requestBackgroundLocationPermission
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsFragment : Fragment(),OnMapReadyCallback,GoogleMap.OnMyLocationButtonClickListener,EasyPermissions.PermissionCallbacks  {

    private var _binding:FragmentMapsBinding? = null
    private val binding get() = _binding!!

    private lateinit var map: GoogleMap


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)


        binding.startButton.setOnClickListener {
            onStartButtonClicked()
        }
        binding.stopButton.setOnClickListener {  }
        binding.resetButton.setOnClickListener {  }

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap!!
        map.isMyLocationEnabled = true
        map.setOnMyLocationButtonClickListener (this )
        map.uiSettings.apply {
            isZoomControlsEnabled = false
            isZoomGesturesEnabled = false
            isRotateGesturesEnabled = false
            isCompassEnabled = false
            isScrollGesturesEnabled = false
        }
    }

    private fun onStartButtonClicked() {
        if (hasBackGroundLocationPermission(requireContext())){
                startCountDown()
            binding.startButton.disable()
            binding.startButton.hide()

        }
        else{
            requestBackgroundLocationPermission(this)
        }
    }

    private fun startCountDown() {
        binding.timerTextView.show()
        binding.stopButton.disable()

        val timer: CountDownTimer = object : CountDownTimer(4000,1000){
            override fun onTick(millisUntilFinished: Long) {
                val currentSecond = millisUntilFinished / 1000
                if (currentSecond.toString() == "0"){
                    binding.timerTextView.text = "Go"
                    binding.timerTextView.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                    binding.stopButton.show()
                }
                else{
                    binding.timerTextView.text = currentSecond.toString()
                    binding.timerTextView.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
                }
            }

            override fun onFinish() {
                sendActionCommandToService(ACTION_SERVICE_START)
                binding.timerTextView.hide()
            }

        }

        timer.start()
    }

    private fun sendActionCommandToService(action:String){
        Intent(
            requireContext(),
            TrackerService::class.java
        ).apply {
            this.action = action
            requireContext().startService(this)
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        binding.hintTextView.animate().alpha(0f).duration = 1500
        lifecycleScope.launch {
            delay(2500)
            binding.hintTextView.hide()
            binding.startButton.show()
        }

        return false
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
       if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
           SettingsDialog.Builder(requireActivity()).build().show()
       }
        else{
            requestBackgroundLocationPermission(this)
       }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        onStartButtonClicked()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}