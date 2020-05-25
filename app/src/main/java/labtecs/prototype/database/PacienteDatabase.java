package labtecs.prototype.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Paciente.class}, version = 1)
public abstract class PacienteDatabase extends RoomDatabase {
    public abstract PacienteDAO pacienteDAO();
}