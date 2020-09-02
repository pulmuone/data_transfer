package com.example.data_transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

class SecondFragment : Fragment() {

    val mainViewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("requestKey") { resultKey, bundle->
            val data = bundle.getString("data", "")

            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {
            setFragmentResult("requestKey", bundleOf("data" to "hello"))
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
    }
}