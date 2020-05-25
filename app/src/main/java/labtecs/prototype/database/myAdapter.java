package labtecs.prototype.database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import labtecs.prototype.R;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {
    private String[] mDataset;
    private ArrayList<Paciente> pacienteList;

    public Paciente get(int n){
        return pacienteList.get(n);
    }

    private onPacienteListener mOnPacienteListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nome;
        private onPacienteListener onPacienteListener;


        public MyViewHolder(@NonNull View v,onPacienteListener onPacienteListener) {
            super(v);
            nome = (TextView) v.findViewById(R.id.rv_name);
            this.onPacienteListener = onPacienteListener;

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPacienteListener.onPacienteClick(getAdapterPosition());
        }
    }

    public myAdapter(ArrayList<Paciente> lp, onPacienteListener l) {
        if(lp == null){
            ArrayList<Paciente> p = new ArrayList<>();
            this.pacienteList = p;
        }else{
            this.pacienteList = lp;
        }

        this.mOnPacienteListener = l;
    }

    public void updateData(ArrayList<Paciente> lp){
        this.pacienteList = lp;
        notifyDataSetChanged();
    }

    @Override
    public myAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);

        MyViewHolder vh = new MyViewHolder(v,mOnPacienteListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.nome.setText(pacienteList.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return pacienteList.size();
    }


    public interface onPacienteListener{
        void onPacienteClick(int position);
    }

}