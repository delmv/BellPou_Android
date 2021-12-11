package com.henallux.bellpou.view.fragments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel

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
import com.henallux.bellpou.exception.APIConnectionFailedException
import com.henallux.bellpou.viewmodel.MapsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.reflect.typeOf

class MapsFragment : Fragment() {

    private val mapsVM by activityViewModels<MapsViewModel>()

    private val callback = OnMapReadyCallback { googleMap ->

        CoroutineScope(Dispatchers.IO).launch {
            addPinPointToMap(googleMap)
        }

        val iesn = LatLng(50.471263836087324, 4.854830342468285)
        val zoom: Float = 15F
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(iesn, zoom))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)

        if (activity!!::class.simpleName == "LoggedActivity") {
            view.findViewById<FloatingActionButton>(R.id.scanQR).visibility = View.VISIBLE
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

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

            withContext(Dispatchers.Main) {
                trashes?.forEach {
                    val location = LatLng(it.position.coordinate_x, it.position.coordinate_y)
                    val image = if (it.is_full) R.drawable.opened_trash else R.drawable.closed_trash

                    googleMap.addMarker(
                        MarkerOptions()
                            .position(location)
                            .icon(activity?.let { bitmapDescriptorFromVector(it.baseContext, image) }))
                }
            }

        } catch (e: kotlin.Exception) {
            withContext(Dispatchers.Main) {
                val toast = Toast.makeText(App.applicationContext(), e.message, Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}