package com.example.data_transfer

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    val getContent = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {
        imageView.setImageURI(it)
    }

    val startActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {activityResult ->
        activityResult.data?.let { intent ->
            intent.extras?.let {bundle ->
                Toast.makeText(requireContext(), "result : ${bundle.getString("data", "world")}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        if (map[Manifest.permission.ACCESS_FINE_LOCATION]!!) {
            Toast.makeText(requireContext(),"ACCESS_FINE_LOCATION 성공",Toast.LENGTH_SHORT).show()
        }

        if (map[Manifest.permission.CAMERA]!!) {
            Toast.makeText(requireContext(),"CAMERA 성공",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("requestKey") { resultKey, bundle->
            val data = bundle.getString("data", "")
            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
        }

        button.setOnClickListener {
            requestPermission.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
            ))

//            Intent(requireContext(), ResultActivity::class.java).apply {
//                startActivityForResult.launch(this)
//            }

            //findNavController().navigate(R.id.action_firstFragment_to_resultActivity)

            //setFragmentResult("requestKey", bundleOf("data" to "hello"))
            //findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }
}