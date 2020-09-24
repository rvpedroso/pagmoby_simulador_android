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
import java.math.RoundingMode
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: AdapterListBasic? = null
    private val items = ArrayList<Simulacao>()

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
        addList()

        view.findViewById<FloatingActionButton>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    private fun initComponent() {
        recyclerView = view?.findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.setHasFixedSize(true)

        mAdapter = AdapterListBasic(context, items)
        recyclerView!!.adapter = mAdapter
    }


    private fun addList() {
        val juros: Array<String> = resources.getStringArray(R.array.juros)

        for (i in 1 until juros.size) {
            val obj = Simulacao()
            obj.vezes = i
            obj.valor = calculo(i, juros.get(i))
            obj.parcela = obj.valor / i.toBigDecimal()

            items.add(obj)
        }

    }


    private fun calculo(vezes: Int, juros: String): BigDecimal {
        val valorFloat = valor.toFloat()
        val jurosFloat = juros.toFloat()
        val resultado = valorFloat / (1.00 - jurosFloat / 100)
        val b = BigDecimal(resultado)
        return b.setScale(2, RoundingMode.CEILING)
    }

}