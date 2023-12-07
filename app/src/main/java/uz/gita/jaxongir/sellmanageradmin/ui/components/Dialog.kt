package uz.gita.jaxongir.sellmanageradmin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(
    text : String,
    onClickCancel: () -> Unit
) {
    androidx.compose.material3.AlertDialog(
        onDismissRequest = {
            onClickCancel()
        }
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = text,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,

                    )
                Spacer(modifier = Modifier.size(30.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { onClickCancel.invoke() },
                        modifier = Modifier
                            .background(Color.White),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466))
                    ) {
                        Text(text = "Cancel", color = Color(0xFFFFFFFF))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { onClickCancel.invoke() },
                        modifier = Modifier
                            .background(Color.White),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFA1466))
                    ) {
                        Text(text = "Ok", color = Color(0xFFFFFFFF))
                    }
                }
            }
        }
    }
}


