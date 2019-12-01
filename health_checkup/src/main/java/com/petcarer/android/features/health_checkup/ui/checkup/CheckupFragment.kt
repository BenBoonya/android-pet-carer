package com.petcarer.android.features.health_checkup.ui.checkup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.boonya.android.library_base.base.ViewModelDialogFragment
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.petcarer.android.features.health_checkup.R
import com.petcarer.android.features.health_checkup.databinding.CheckupFragmentBinding

class CheckupFragment : ViewModelDialogFragment<CheckUpViewModel>() {

    private lateinit var binding: CheckupFragmentBinding

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(CheckUpViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CheckupFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.cameraView.setLifecycleOwner(viewLifecycleOwner)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
    }

    override fun attachObserver() {
        super.attachObserver()

        with(viewModel) {
            _closeDialog.observe(viewLifecycleOwner, Observer {
                dialog?.dismiss()
            })

            _captureCamera.observe(viewLifecycleOwner, Observer {
                binding.cameraView.takePicture()
            })
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cameraView.addCameraListener(object : CameraListener() {

            override fun onPictureTaken(result: PictureResult) {
                super.onPictureTaken(result)
                viewModel.extractDataFromFrame(result)
            }

        })
    }
}