package com.henallux.bellpou.view.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.henallux.bellpou.App
import com.henallux.bellpou.R
import com.henallux.bellpou.view.activities.QRScanActivity
import com.henallux.bellpou.viewmodel.MapsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MapsFragment : Fragment() {

    private val mapsVM by activityViewModels<MapsViewModel>()
    private val callback = OnMapReadyCallback { googleMap ->

        CoroutineScope(Dispatchers.IO).launch {
            addPinPointToMap(googleMap)
        }

        val iesn = LatLng(50.471263836087324, 4.854830342468285)
        val zoom = 15F
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iesn, zoom))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_maps, container, false)

        // Check if the user is connected by checking the current activity and let him scan a QR if he's connected.
        if (activity!!::class.simpleName == "LoggedActivity") {
            view.findViewById<FloatingActionButton>(R.id.scanQR).visibility = View.VISIBLE

            view.findViewById<FloatingActionButton>(R.id.scanQR).setOnClickListener {
                scanQRButtonAction()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun scanQRButtonAction() {
        val intent = Intent(activity, QRScanActivity::class.java)
        activity ?.startActivity(intent)
    }

    // Convert vector asset to bitmap. Source : https://stackoverflow.com/questions/42365658/custom-marker-in-google-maps-in-android-with-vector-asset-icon
    private fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {

        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptorFactory.fromBitmap(bitmap)
        }

    }

    private suspend fun addPinPointToMap(googleMap: GoogleMap) {

        try {

            val trashes = mapsVM.getTrashesAndLocations()

            trashes.forEach {
                val location = LatLng(it.position.coordinate_x, it.position.coordinate_y)
                val image = if (it.is_full) R.drawable.opened_trash else R.drawable.closed_trash

                withContext(Dispatchers.Main) {
                    googleMap.addMarker(
                        MarkerOptions()
                            .position(location)
                            .icon(activity?.let { bitmapDescriptorFromVector(it.baseContext, image) }))
                }
            }

        } catch (e: Exception) {

            withContext(Dispatchers.Main) {
                val toast = Toast.makeText(App.applicationContext(), e.message, Toast.LENGTH_SHORT)
                toast.show()
            }

        }

    }

}