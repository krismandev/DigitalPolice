package academy.bangkit.digitalpolice.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import academy.bangkit.digitalpolice.R
import academy.bangkit.digitalpolice.core.ui.ViewModelFactory
import academy.bangkit.digitalpolice.databinding.BottomSheetNotificationBinding
import academy.bangkit.digitalpolice.databinding.FragmentNotificationsBinding
import academy.bangkit.digitalpolice.ui.home.HistoryAdapter
import academy.bangkit.digitalpolice.ui.home.HomeViewModel
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.messaging.FirebaseMessaging

class NotificationsFragment : BottomSheetDialogFragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var binding: BottomSheetNotificationBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = BottomSheetNotificationBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[NotificationsViewModel::class.java]
        val notifAdapter = NotificationsAdapter()

        viewModel.getHistoriesToday().observe(viewLifecycleOwner,{
            notifAdapter.setData(it)
        })

        with(binding.rvNotification) {
            layoutManager = LinearLayoutManager(context)
            this.adapter = notifAdapter
        }

    }



}