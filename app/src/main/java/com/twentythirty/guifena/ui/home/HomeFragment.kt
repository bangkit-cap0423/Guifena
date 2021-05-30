package com.twentythirty.guifena.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.twentythirty.guifena.R
import com.twentythirty.guifena.data.IncidentEntity
import com.twentythirty.guifena.databinding.FragmentHomeBinding
import com.twentythirty.guifena.ui.detailIncident.DetailIncident
import com.twentythirty.guifena.ui.listIncident.ListIncidentActivity
import com.twentythirty.guifena.utils.Status
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
            val intent = Intent(context, ListIncidentActivity::class.java)
            startActivity(intent)
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
        homeViewModel.allGood.observe(viewLifecycleOwner, {
            if (it) {
                binding.allClearText.text = "Semua Aman"
                binding.allClearText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.green
                    )
                )
            } else {
                binding.allClearText.text = "Ada Masalah"
                binding.allClearText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.red
                    )
                )
            }
        })
        homeViewModel.recentIncidents.observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { sensor ->
                            sensor as List<IncidentEntity>
                            if (sensor.isNotEmpty()) {
                                homeAdapter.setIncident(sensor)
                                binding.noIncident.visibility = View.GONE
                                binding.rvIncident.visibility = View.VISIBLE
                            } else {
                                binding.noIncident.visibility = View.VISIBLE
                                binding.rvIncident.visibility = View.GONE
                            }

                        }
                    }
                }

            }
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
                task.result?.let {
                    homeViewModel.sendToken(it)
                }
            })
        }
    }
}