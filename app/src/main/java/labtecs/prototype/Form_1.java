package labtecs.prototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import labtecs.prototype.database.Paciente;
import labtecs.prototype.database.PacienteDatabase;

public class Form_1 extends AppCompatActivity {

    public Paciente p;

    private class AddPaciente extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            PacienteDatabase db = Room.databaseBuilder(getApplicationContext(),
                    PacienteDatabase.class, "pacientes-database").build();
            db.pacienteDAO().insertAll(p);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            showToast("Obrigado pela sua colaboraçao!");
            finish();
        }
    }



    private TextInputEditText e_nome;
    private TextInputEditText e_cpf;

    private SwitchMaterial sw_dor;

    private TextView t_intensidade;
    private Slider sl_dor;

    private SwitchMaterial sw_desconforto;
    private TextView t_aonde;
    private Spinner sp_desconforto;

    private SwitchMaterial sw_cirurgia;
    private TextView t_tipo;
    private Spinner sp_tipo;
    private TextView t_quando;
    private Spinner sp_cirurgia_tempo;
    private TextView t_sentiuDor;
    private SwitchMaterial sw_cirurgia_dor;

    private Button b_confirmar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_1);

        e_nome = (TextInputEditText) findViewById(R.id.nome);
        e_cpf = (TextInputEditText) findViewById(R.id.cpf);

        sw_dor = (SwitchMaterial) findViewById(R.id.switch_dor);
        t_intensidade = (TextView) findViewById(R.id.intensidade);
        sl_dor = (Slider) findViewById(R.id.slider_dor);

        sw_desconforto = (SwitchMaterial) findViewById(R.id.switch_desconforto);
        t_aonde = (TextView) findViewById(R.id.aonde);
        sp_desconforto = (Spinner) findViewById(R.id.spinner_desconforto);

        sw_cirurgia = (SwitchMaterial) findViewById(R.id.switch_cirurgia);
        t_tipo = (TextView) findViewById(R.id.tipo);
        sp_tipo = (Spinner) findViewById(R.id.spinner_tipo);
        t_quando = (TextView) findViewById(R.id.quando);
        sp_cirurgia_tempo = (Spinner) findViewById(R.id.spinner_tempo_cirurgia);
        t_sentiuDor = (TextView) findViewById(R.id.sentiudor);
        sw_cirurgia_dor = (SwitchMaterial) findViewById(R.id.switch_dor_cirurgia);

        b_confirmar = (Button) findViewById(R.id.confirmar);


        // Set default values
        sl_dor.setEnabled(false);
        t_intensidade.setEnabled(false);

        t_aonde.setEnabled(false);
        sp_desconforto.setEnabled(false);

        t_quando.setEnabled(false);

        sp_cirurgia_tempo.setEnabled(false);
        t_tipo.setEnabled(false);
        sp_tipo.setEnabled(false);
        t_sentiuDor.setEnabled(false);
        sw_cirurgia_dor.setEnabled(false);


        // Set the Spinner DESCONFORTO
        String[] arraySpinner_d = new String[]{
                "Cabeça",
                "Braços",
                "Tórax",
                "Barriga",
                "Pernas",
                "Costas"
        };
        ArrayAdapter<String> aondeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner_d);
        aondeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_desconforto.setAdapter(aondeAdapter);

        // Set the Spinner QUANDO
        String[] arraySpinner_q = new String[]{
                "1 a 2 meses",
                "3 a 6 meses",
                "6 a 12 meses",
                "Mais de 1 ano"
        };
        ArrayAdapter<String> quandoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner_q);
        quandoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_cirurgia_tempo.setAdapter(quandoAdapter);

        // Set the Spinner TIPO
        String[] arraySpinner_t = new String[]{
                "Estetica",
                "Saude"
        };
        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner_t);
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_tipo.setAdapter(tipoAdapter);


        // Activate first
        sw_dor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sl_dor.setEnabled(isChecked);
                t_intensidade.setEnabled(isChecked);
            }
        });

        // Activate Second
        sw_desconforto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sp_desconforto.setEnabled(isChecked);
                t_aonde.setEnabled(isChecked);
            }
        });

        // Activate third

        sw_cirurgia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                t_tipo.setEnabled(isChecked);
                sp_tipo.setEnabled(isChecked);
                t_quando.setEnabled(isChecked);
                sp_cirurgia_tempo.setEnabled(isChecked);
                t_sentiuDor.setEnabled(isChecked);
                sw_cirurgia_dor.setEnabled(isChecked);
            }
        });


        b_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (e_nome.getText().toString().matches("")) {
                    showToast("Por favor insira um nome");
                    return;
                }

                if (e_cpf.getText().toString().matches("")) {
                    showToast("Por favor insira um CPF");
                    return;
                }

                Paciente pp = new Paciente(e_cpf.getText().toString(),
                        e_nome.getText().toString(),
                        sw_dor.isChecked(),
                        Math.round(sl_dor.getValue()),
                        sw_desconforto.isChecked(),
                        sp_desconforto.getSelectedItem().toString(),
                        sw_cirurgia.isChecked(),
                        sp_cirurgia_tempo.getSelectedItem().toString(),
                        sp_tipo.getSelectedItem().toString(),
                        sw_cirurgia_dor.isChecked()
                        );
                p = pp;

                // Request
                AddPaciente runner = new AddPaciente();
                runner.execute();
            }
        });




    }

    void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
