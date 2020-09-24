package br.com.wiguia.pagmoby.simulador

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.wiguia.pagmoby.simulador.adapter.AdapterListBasic
import br.com.wiguia.pagmoby.simulador.model.Simulacao
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.math.BigDecimal
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: AdapterListBasic? = null

    private var valor = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        valor = 1000
        initComponent()

        view.findViewById<FloatingActionButton>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun initComponent() {
        recyclerView = view?.findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.setHasFixedSize(true)

        val items = ArrayList<Simulacao>()
        val obj = Simulacao()
        obj.parcela = BigDecimal(1000)
        obj.valor = BigDecimal(1000)
        obj.vezes = 1

        val obj2 = Simulacao()
        obj2.parcela = BigDecimal(500)
        obj2.valor = BigDecimal(500)
        obj2.vezes = 2
        items.add(obj)
        items.add(obj2)

        //set data and list adapter
        mAdapter = AdapterListBasic(context, items)
        recyclerView!!.adapter = mAdapter
    }


}