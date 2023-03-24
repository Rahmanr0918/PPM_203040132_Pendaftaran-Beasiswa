package id.ac.unpas.functionalcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.functionalcompose.model.DaftarBeasiswa
import id.ac.unpas.functionalcompose.persistences.DaftarBeasiswaDao
import id.ac.unpas.functionalcompose.ui.theme.Purple700
import id.ac.unpas.functionalcompose.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanBeasiswa(daftarBeasiswaDao : DaftarBeasiswaDao) {
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val nrp = remember { mutableStateOf(TextFieldValue("")) }
    val universitas = remember { mutableStateOf(TextFieldValue("")) }
    val jurusan = remember { mutableStateOf(TextFieldValue("")) }
    val semester = remember { mutableStateOf(TextFieldValue("")) }

    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Nama") },
            value = nama.value,
            onValueChange = {
                nama.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "Rahman") }
        )
        OutlinedTextField(
            label = { Text(text = "NRP / NIM") },
            value = nrp.value,
            onValueChange = {
                nrp.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Number
            ),
            placeholder = { Text(text = "203040000") }
        )
        OutlinedTextField(
            label = { Text(text = "Universitas") },
            value = universitas.value,
            onValueChange = {
                universitas.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Text
            ),
            placeholder = { Text(text = "Universitas Indonesia") }
        )
        OutlinedTextField(
            label = { Text(text = "Jurusan") },
            value = jurusan.value,
            onValueChange = {
                jurusan.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Text
            ),
            placeholder = { Text(text = "Informatika") }
        )
        OutlinedTextField(
            label = { Text(text = "Semester") },
            value = semester.value,
            onValueChange = {
                semester.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Text
            ),
            placeholder = { Text(text = "5") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row(modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {

                val id = uuid4().toString()
                val item = DaftarBeasiswa(
                    id, nama.value.text, nrp.value.text,
                    universitas.value.text, jurusan.value.text, semester.value.text
                )
                scope.launch {
                    daftarBeasiswaDao.insertAll(item)
                }
//                onSimpan(item)
                nama.value = TextFieldValue("")
                nrp.value = TextFieldValue("")
                universitas.value = TextFieldValue("")
                jurusan.value = TextFieldValue("")
                semester.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {

                nama.value = TextFieldValue("")
                nrp.value = TextFieldValue("")
                universitas.value = TextFieldValue("")
                jurusan.value = TextFieldValue("")
                semester.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}