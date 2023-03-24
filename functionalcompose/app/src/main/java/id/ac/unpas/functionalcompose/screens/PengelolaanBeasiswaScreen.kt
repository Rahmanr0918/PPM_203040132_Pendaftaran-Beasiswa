package id.ac.unpas.functionalcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.functionalcompose.model.DaftarBeasiswa
import id.ac.unpas.functionalcompose.persistences.AppDatabase
import kotlinx.coroutines.flow.MutableStateFlow
@Composable
fun PengelolaanBeasiswaScreen() {

    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pengelolaan-beasiswa"
    ).build()
    val daftarbeasiswaDao = db.daftarBeasiswaDao()

    val list : LiveData<List<DaftarBeasiswa>> = daftarbeasiswaDao.loadAll()
    val items: List<DaftarBeasiswa> by list.observeAsState(initial = listOf())
    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanBeasiswa(daftarbeasiswaDao)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()) {


                    Column(modifier = Modifier.weight(3f)) {
                        Row(modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()) {
                            Text(text = "Nama   :", fontSize = 14.sp)
                            Text(text = item.nama, fontSize = 16.sp,
                                fontWeight = FontWeight.Bold)
                        }
                        Row(modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()) {
                            Text(text = "Nrp    :", fontSize = 14.sp)
                            Text(text = item.nrp, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold)
                        }
                        Row(modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()) {
                            Text(text = "Universitas    :", fontSize = 14.sp)
                            Text(text = item.universitas, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold)
                        }
                        Row(modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()) {
                            Text(text = "Jurusan    :", fontSize = 14.sp)
                            Text(text = item.jurusan, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold)
                        }
                        Row(modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()) {
                            Text(text = "Semester   :", fontSize = 14.sp)
                            Text(text = item.semester, fontSize = 16.sp,
                                fontWeight = FontWeight.Bold)
                        }

                    }
//
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}