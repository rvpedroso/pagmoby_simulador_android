package br.com.wiguia.pagmoby.simulador

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val amountTv: EditText = requireView().findViewById(R.id.et_search)
            val amount = amountTv.text.toString()
            val bundle = bundleOf("valor" to amount)

            if (amount == "") {
                toastIconError()
            } else {
                hideSoftKeyBoard(view)
                Navigation.findNavController(view).navigate(
                    R.id.action_FirstFragment_to_SecondFragment,
                    bundle
                )
            }
        }
    }

    private fun toastIconError() {
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_LONG

        //inflate view
        val custom_view: View = layoutInflater.inflate(R.layout.toast_icon_text, null)
        (custom_view.findViewById<View>(R.id.message) as TextView).text = "Digite um valor"
        (custom_view.findViewById<View>(R.id.icon) as ImageView).setImageResource(R.drawable.ic_close)
        (custom_view.findViewById<View>(R.id.parent_view) as CardView).setCardBackgroundColor(
            resources.getColor(R.color.red_600)
        )
        toast.setView(custom_view)
        toast.show()
    }

    fun hideSoftKeyBoard(view: View) {
        try {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }
}