package uz.gita.jaxongir.sellmanageradmin.presenter.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.sellmanageradmin.R
import uz.gita.jaxongir.sellmanageradmin.data.source.local.pref.MyPref
import javax.inject.Inject

class LoginScreen  : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<LoginViewModel>()
        LoginScreenContent(
            uiState = viewModel.uiState.collectAsState(),
            onEventDispatcher = viewModel::onEventDispatcher,
        )
    }
}

@Composable
fun LoginScreenContent(
    uiState: State<LoginContract.UIState>,
    onEventDispatcher: (LoginContract.Intent) -> Unit,
) {
    var adminName by remember { mutableStateOf("") }
    var adminPassword by remember { mutableStateOf("") }
    var errorText: Boolean by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(22.dp))
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.ic_login),
                contentDescription = "",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .padding(top = 36.dp)
                    .align(Alignment.TopCenter)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = adminName, onValueChange = {
                if (it.length < 16) adminName = it
            }, modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .height(70.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            label = { Text(text = "AdminName") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFF3951),
                unfocusedBorderColor = Color(0xFFFF7686),
            ),
            singleLine = true,
            trailingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.name_icon),
                    contentDescription = "name",
                    modifier = Modifier.size(24.dp)

                )
            }
        )

        var isPasswordVisible by remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = adminPassword,
            onValueChange = {
                if (it.length < 16) {
                    adminPassword = it
                    errorText = false
                }

            },
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .height(70.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }
                ) {
                    Image(
                        painter = painterResource(id = if (isPasswordVisible) R.drawable.visibility_icon else R.drawable.visibility_icon),
                        contentDescription = if (isPasswordVisible) "Hide Password" else "Show Password",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            singleLine = true,
            label = {
                Text(text = "Password")
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFF3951),
                unfocusedBorderColor = Color(0xFFFF7686),
            )
        )
        Spacer(modifier = Modifier.height(72.dp))

        Button(
            onClick = {
                if (adminName.length > 2 && adminPassword.length > 2) {
                    onEventDispatcher.invoke(
                        LoginContract.Intent.IsLogin(
                            adminName.trim(),
                            adminPassword.trim(),
                        )
                    )
                } else {
                    Toast.makeText(
                        context,
                        "Username and password should be bigger than 3!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                (if (adminPassword.length >= 2 && adminName.length >= 3) Color(0xFFFF3951) else Color(
                    0xFFFF7686
                ))
            )
        ) {
            if (uiState.value.loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterVertically)
                        .padding(start = 200.dp),
                    color = Color.Red,
                    strokeWidth = 4.dp
                )
            } else {
                Text(text = "Login", fontSize = 22.sp)
            }
        }

    }
}