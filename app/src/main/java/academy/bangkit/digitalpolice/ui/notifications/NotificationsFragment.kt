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
import academy.bangkit.digitalpolice.databinding.FragmentNotificationsBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NotificationsFragment : BottomSheetDialogFragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        return inflater.inflate(R.layout.bottom_sheet_notification,container,false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}