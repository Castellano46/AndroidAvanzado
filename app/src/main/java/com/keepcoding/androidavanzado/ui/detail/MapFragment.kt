package com.keepcoding.androidavanzado.ui.detail

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.keepcoding.androidavanzado.R
import com.keepcoding.androidavanzado.databinding.FragmentMapBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mMap: GoogleMap

    private val args: MapFragmentArgs by navArgs()
    private val viewModel: MapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        Log.d("MAP", args.heroName)

        viewModel.getHeroDetail(args.heroName)

        with(binding) {
            randomButton.setOnClickListener {
                moveToRandomPlace()
            }

            clearButton.setOnClickListener {
                mMap.clear()
            }

            viewModel.hero.observe(viewLifecycleOwner) {
                heroDescription.text = it.description
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val hasPermission = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasPermission) {
            val requestPermissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                    if (granted) {
                        getUserLocation()
                    } else {
                        Toast.makeText(requireContext(), "No se puede mostrar la localización", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            getUserLocation()
        }

    }

    private fun moveToRandomPlace() {
        val newLat = Random.nextDouble() * 90 * if (Random.nextBoolean()) 1 else -1
        val newLng = Random.nextDouble() * 180 * if (Random.nextBoolean()) 1 else -1

        val newLatLng = LatLng(newLat, newLng)

        mMap.addMarker(
            MarkerOptions()
                .position(newLatLng)
                .title("Marker in $newLat - $newLng")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(newLatLng))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.addCircle(
            CircleOptions().center(sydney).radius(12000.0).fillColor(Color.CYAN).strokeColor(Color.BLUE).strokeWidth(1f)
        )
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationProviderClient.lastLocation.addOnCompleteListener {
            if (it.isSuccessful) {
                if (it.result != null){
                    val latLng = LatLng(it.result.latitude, it.result.longitude)
                    Log.d("HOLA", "Latitude: ${latLng.latitude} Longitude: ${latLng.longitude}")
                }
            }
        }
    }

}
