package id.ac.unpas.functionalcompose.persistences
import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.functionalcompose.model.DaftarBeasiswa

@Dao
interface DaftarBeasiswaDao {
    @Query("SELECT * FROM DaftarBeasiswa")
    fun loadAll(): LiveData<List<DaftarBeasiswa>>
    @Query("SELECT * FROM DaftarBeasiswa WHERE id = :id")
    fun find(id: String): DaftarBeasiswa?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: DaftarBeasiswa)
    @Delete
    fun delete(item: DaftarBeasiswa)
}