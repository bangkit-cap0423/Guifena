package com.twentythirty.guifena.ui.home

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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.twentythirty.guifena.R
import com.twentythirty.guifena.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : Fragment() {

    val homeViewModel: HomeViewModel by sharedViewModel()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mainHandler: Handler

    companion object {
        val TAG = "farin"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.let {
            it.title = "Guifena"
            it.setBackgroundDrawable(resources.getDrawable(R.drawable.actionbar_layer_list2))
        }
        fetchFirebaseToken()
        fetchData()
        mainHandler = Handler(Looper.getMainLooper())

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
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            Log.d(TAG, token!!)
            Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
        })
    }
}