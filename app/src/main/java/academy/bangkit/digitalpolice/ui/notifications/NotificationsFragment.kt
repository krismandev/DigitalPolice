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

//        notificationsViewModel =
//            ViewModelProvider(this).get(NotificationsViewModel::class.java)
//
//        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//        return root
//        val bottomSheetDialog: BottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.BottomSheetDialogTheme)
//        val bottomSheetView = LayoutInflater.from(requireContext())
//            .inflate(R.layout.bottom_sheet_notification)
//        FirebaseMessaging.getInstance().subscribeToTopic("news")
//        val msgs = getString(R.string.msg_subscribed)
//        Toast.makeText(requireContext(), msgs, Toast.LENGTH_SHORT).show()
//
//        val deviceToken = FcmServices
//        val msg = getString(R.string.msg_token_fmt, deviceToken)
//        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
//        FirebaseMessaging.getInstance().token.addOnSuccessListener { deviceToken ->
//            val msg = getString(R.string.msg_token_fmt, deviceToken)
//            Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
//            Log.d("OKE",msg)
//        }
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