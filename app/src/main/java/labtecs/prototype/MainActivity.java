package labtecs.prototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import labtecs.prototype.database.myAdapter;
import labtecs.prototype.database.Paciente;
import labtecs.prototype.database.PacienteDatabase;


public class MainActivity extends AppCompatActivity implements myAdapter.onPacienteListener {
    public PacienteDatabase db;
    public String result = "";
    private RecyclerView recyclerView;
    private myAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addFab;

    private static final String TAG = "MainActivity";




    private class RequestDB extends AsyncTask<String, String, ArrayList<Paciente>> {
        ArrayList<Paciente> populateList() {
            PacienteDatabase db = Room.databaseBuilder(getApplicationContext(),
                    PacienteDatabase.class, "pacientes-database").build();

            return new ArrayList<Paciente>(db.pacienteDAO().getAll());
        }

        @Override
        protected ArrayList<Paciente> doInBackground(String... strings) {
            return populateList();
        }

        @Override
        protected void onPostExecute(ArrayList<Paciente> rs) {
            super.onPostExecute(rs);
            mAdapter.updateData(rs);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fab
        addFab = (FloatingActionButton) findViewById(R.id.fab_add);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), Form_1.class);
                startActivity(myIntent);

            }
        });

        // Set the recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mAdapter = new myAdapter(null,this);
        recyclerView.setAdapter(mAdapter);



    }


    void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    void updateDatabase(){
        // Request
        RequestDB runner = new RequestDB();
        runner.execute();
    }

    @Override
    public void onPacienteClick(int position) {
        Log.d(TAG, "onPacienteClick: clicked " + position);
        Intent intent = new Intent(MainActivity.this,PacienteActivity.class);
        intent.putExtra("Paciente",mAdapter.get(position));
        startActivity(intent);
    }


}
