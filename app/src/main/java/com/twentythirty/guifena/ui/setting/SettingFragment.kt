package com.twentythirty.guifena.ui.setting

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.twentythirty.guifena.R
import com.twentythirty.guifena.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    companion object {
        const val TAG_NAMA = "nama"
    }

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
            resources.getDrawable(
                R.drawable.actionbar_layer_list
            )
        )


        binding.btnSimpan.setOnClickListener {
            val nama = binding.edtNama.text.toString()

            if (nama.isBlank()){
                Toast.makeText(context, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else {
                val preferance = context?.getSharedPreferences("setting", 0)
                preferance?.edit().apply {
                    this?.putString(TAG_NAMA, nama)
                    this?.apply()
                }
                Toast.makeText(context, "berhasil", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}