package labtecs.prototype.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PacienteDAO {
    @Query("SELECT * FROM paciente")
    List<Paciente> getAll();

    @Query("SELECT * FROM paciente WHERE id IN (:userIds)")
    List<Paciente> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM paciente WHERE nome LIKE :first LIMIT 1")
    Paciente findByName(String first);

    @Insert
    void insertAll(Paciente... pacientes);

    @Delete
    void delete(Paciente paciente);
}