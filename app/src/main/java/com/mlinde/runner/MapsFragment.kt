package com.mlinde.runner

import android.app.DownloadManager
import android.graphics.Color
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import org.json.JSONObject
import kotlin.properties.Delegates
import com.google.maps.android.PolyUtil

private const val ARG_PARAM1 = "startLat"

class MapsFragment : Fragment(), OnMapReadyCallback {

    var startLat by Delegates.notNull<Double>()
    var startLng by Delegates.notNull<Double>()
    var endLat by Delegates.notNull<Double>()
    var endLng by Delegates.notNull<Double>()

    private var googleMap: GoogleMap? = null

    private val viewModel: MapsViewModel by viewModels { MapsViewModel.Factory(arguments?.getSerializable(ARG_PARAM1) as? Trail) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        startLat = viewModel.trail!!.lat_start!!
        startLng = viewModel.trail!!.lng_start!!
        endLat = viewModel.trail!!.lat_end!!
        endLng = viewModel.trail!!.lng_end!!
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        val start = LatLng(startLat, startLng)
        val end = LatLng(endLat, endLng)
        this.googleMap!!.addMarker(MarkerOptions().position(start).title("Start"))
        this.googleMap!!.addMarker(MarkerOptions().position(end).title("Finish"))
        this.googleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(start,14.5f))

        val path: MutableList<List<LatLng>> = ArrayList()
        val urlDirections = "https://maps.googleapis.com/maps/api/directions/json?origin=$startLat,$startLng&destination=$endLat,$endLng&key=AIzaSyCtZfpbsDsvBswM7FH5fmpp1tTuyaXnD5M"
        val directionsRequest = object : StringRequest(Method.GET, urlDirections, Response.Listener<String> {
                response ->
            val jsonResponse = JSONObject(response)
            val routes = jsonResponse.getJSONArray("routes")
            val legs = routes.getJSONObject(0).getJSONArray("legs")
            val steps = legs.getJSONObject(0).getJSONArray("steps")
            for (i in 0 until steps.length()) {
                val points = steps.getJSONObject(i).getJSONObject("polyline").getString("points")
                path.add(PolyUtil.decode(points))
            }
            for (i in 0 until path.size) {
                this.googleMap!!.addPolyline(PolylineOptions().addAll(path[i]).color(Color.RED))
            }
        }, Response.ErrorListener {
        }){}
        if (isAdded){
            val requestQueue = Volley.newRequestQueue(requireContext())
            requestQueue.add(directionsRequest)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Trail?) =
            MapsFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}
