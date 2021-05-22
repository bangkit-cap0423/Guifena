package com.twentythirty.guifena.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.twentythirty.guifena.R
import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.databinding.FragmentHomeBinding
import com.twentythirty.guifena.ui.detailIncident.DetailIncident
import com.twentythirty.guifena.ui.sensor.dummyData.dummyIncidentData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by sharedViewModel()
    private var _binding: FragmentHomeBinding? = null
    private val homeAdapter = HomeAdapter()

    private val binding get() = _binding!!
    lateinit var mainHandler: Handler

    companion object {
        const val TAG = "farin"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            with(binding.rvIncident) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = homeAdapter
            }
            val dummy = dummyIncidentData.setIncident()

            homeAdapter.setIncident(dummy)
            homeAdapter.onItemClick = { data ->
                val intent = Intent(activity, DetailIncident::class.java)
                intent.putExtra(DetailIncident.EXTRA_DATA, data)
                startActivity(intent)
            }
        }

        (activity as AppCompatActivity).supportActionBar?.let {
            it.title = "Guifena"
            it.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar_layer_list2))
        }
        fetchFirebaseToken()
        fetchData()
        mainHandler = Handler(Looper.getMainLooper())
        binding.btnAllIncident.setOnClickListener {
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(fetchDataTask)
    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(fetchDataTask)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchData() {
        homeViewModel.fetchCount()
        homeViewModel.onlineText.observe(viewLifecycleOwner, {
            binding.txOnlineCount.text = it
        })
        homeViewModel.incidentText.observe(viewLifecycleOwner, {
            binding.txIncidentCount.text = it
        })
    }

    private val fetchDataTask = object : Runnable {
        override fun run() {
            fetchData()
            Log.d("farin", "updating data...")
            mainHandler.postDelayed(this, 10000)
        }
    }

    private fun fetchFirebaseToken() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(200L)
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result
                //SEND TOKEN TO SERVER
                Log.d("farin", token!!)
                Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
            })
        }
    }
}