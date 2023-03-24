package id.ac.unpas.functionalcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class DaftarBeasiswa(
    @PrimaryKey val id: String,
    val nama: String,
    val nrp: String,
    val universitas: String,
    val jurusan: String,
    val semester: String
)
