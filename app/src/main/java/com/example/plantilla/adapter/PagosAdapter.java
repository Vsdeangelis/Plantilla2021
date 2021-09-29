package com.example.plantilla.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantilla.R;
import com.example.plantilla.modelo.Contrato;
import com.example.plantilla.modelo.Pago;

import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.PagoHolder> {
    private ArrayList<Pago> listaPagos;
    private LayoutInflater infPago;
    private View vistaPagos;

    public PagosAdapter(ArrayList<Pago> listaPagos, LayoutInflater infPago, View vistaPagos) {
        this.listaPagos = listaPagos;
        this.infPago = infPago;
        this.vistaPagos = vistaPagos;
    }

    @NonNull
    @Override
    ///Refrenciar a la vista item y pasarsela al ViewHolder
    public PagosAdapter.PagoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= infPago.inflate(R.layout.item_detalle_pago, parent, false);
        return new PagosAdapter.PagoHolder(view);
    }

    @Override
    ///se ejecuta por cada elemento de la lista
    public void onBindViewHolder(@NonNull PagoHolder holder, int position) {
        Pago p= listaPagos.get(position);
        holder.tvCodPago.setText("Código de pago: "+listaPagos.get(position).getIdPago());
        holder.tvNumPago.setText("Número de pago: "+listaPagos.get(position).getNumero());
        holder.tvCodContrato.setText("Código de contrato: "+listaPagos.get(position).getContrato().getIdContrato());
        holder.tvImportePago.setText("Importe: "+listaPagos.get(position).getImporte());
        holder.tvFechaPago.setText("Fecha de pago: "+listaPagos.get(position).getFechaDePago());
    }
    @Override
    public int getItemCount() {
        return listaPagos.size();
    }

    public class PagoHolder extends RecyclerView.ViewHolder{
        private TextView tvCodPago,tvNumPago, tvCodContrato, tvImportePago, tvFechaPago;
        public PagoHolder(@NonNull View itemView) {
            super(itemView);
            tvCodPago= itemView.findViewById(R.id.tvCodigoPago);
            tvNumPago=itemView.findViewById(R.id.tvNumeroPago);
            tvCodContrato=itemView.findViewById(R.id.tvCodigoCont);
            tvImportePago=itemView.findViewById(R.id.tvImportePago);
            tvFechaPago=itemView.findViewById(R.id.tvFechaPago);
        }
    }
}
