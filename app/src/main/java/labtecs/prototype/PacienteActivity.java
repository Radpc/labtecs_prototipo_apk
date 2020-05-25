package labtecs.prototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import labtecs.prototype.database.Paciente;

public class PacienteActivity extends AppCompatActivity {

    private TextView t_name;
    private TextView t_cpf;

    private TextView t_dor;
    private TextView t_nivel;

    private TextView t_desconforto;
    private TextView t_local;

    private TextView t_cirurgia;
    private TextView t_tipo;
    private TextView t_tempo;
    private TextView t_dor_cirurgia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        Intent intent = getIntent();
        Paciente p = intent.getParcelableExtra("Paciente");

        // Connect
        t_name = (TextView) findViewById(R.id.t_name);
        t_cpf = (TextView) findViewById(R.id.t_cpf);
        t_dor = (TextView) findViewById(R.id.t_dor);
        t_nivel = (TextView) findViewById(R.id.t_nivel);
        t_desconforto = (TextView) findViewById(R.id.t_desconforto);
        t_local = (TextView) findViewById(R.id.t_local);
        t_cirurgia = (TextView) findViewById(R.id.t_cirurgia);
        t_tipo = (TextView) findViewById(R.id.t_tipo);
        t_tempo = (TextView) findViewById(R.id.t_tempo);
        t_dor_cirurgia = (TextView) findViewById(R.id.t_dor_cirurgia);


        // Update
        t_name.setText(p.getNome());
        t_cpf.setText(p.getCpf());

        if(p.getSenteDor()){
            t_dor.setText("Sim");
        }else{
            t_dor.setText("Não");
        }

        t_nivel.setText(p.getDorIntensidade().toString());


        if(p.getDesconforto()){
            t_desconforto.setText("Sim");
        }else{
            t_desconforto.setText("Não");
        }

        t_local.setText(p.getDesconfortoLocal());
        if(p.getTeveCirurgia()){
            t_cirurgia.setText("Sim");
        }else{
            t_cirurgia.setText("Não");
        }

        t_tipo.setText(p.getTipoCirurgia());
        t_tempo.setText(p.getQuandoCirurgia());
        if(p.getDorPosCirurgia()){
            t_dor_cirurgia.setText("Sim");
        }else{
            t_dor_cirurgia.setText("Não");
        }

    }
}
