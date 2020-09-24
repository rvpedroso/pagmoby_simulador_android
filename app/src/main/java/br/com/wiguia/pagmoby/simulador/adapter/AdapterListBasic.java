package br.com.wiguia.pagmoby.simulador.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import br.com.wiguia.pagmoby.simulador.R;
import br.com.wiguia.pagmoby.simulador.model.Simulacao;

public class AdapterListBasic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Simulacao> items;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Simulacao obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasic(Context context, List<Simulacao> items) {
        this.items = items;
        ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public TextView vezes;
        public TextView total;
        public TextView parcela;

        public OriginalViewHolder(View v) {
            super(v);
            vezes = v.findViewById(R.id.vezes);
            total = v.findViewById(R.id.total);
            parcela = v.findViewById(R.id.parcela);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_people_chat, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            Simulacao p = items.get(position);
            view.vezes.setText(p.vezes.toString().concat(" X"));
            view.total.setText("Valor:  ".concat(formataValor(p.valor)));
            view.parcela.setText("Parcela:  ".concat(formataValor(p.parcela)));
        }
    }

    private String formataValor(BigDecimal valor){
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(valor);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}