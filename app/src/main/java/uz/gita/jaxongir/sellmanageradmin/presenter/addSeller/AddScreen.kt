package uz.gita.jaxongir.sellmanageradmin.presenter.addSeller

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.sellmanageradmin.ui.components.Dialog

class AddScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewModel = getViewModel<AddViewModel>()
        AddScreenContent(onEventDispatcher = viewModel::onEventDispatcher)
        val sideEffect = viewModel.sideEffect.collectAsState()
        when(sideEffect.value){
            AddSellerContract.SideEffect.Init -> {}
            is AddSellerContract.SideEffect.ShowNotification -> {
                Dialog(text = (sideEffect.value as AddSellerContract.SideEffect.ShowNotification).message ) {
                    AddSellerContract.Intent.Back
                }
            }

            is AddSellerContract.SideEffect.ShowSuccessMessage -> {
                Toast.makeText(context, (sideEffect.value as AddSellerContract.SideEffect.ShowSuccessMessage).message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent(
    onEventDispatcher: (AddSellerContract.Intent) -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            var name by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }


            TopAppBar(
                title = {
                    Text(
                        text = "Add",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF000000),
                        )
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable { onEventDispatcher.invoke(AddSellerContract.Intent.Back) }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEventDispatcher.invoke(
                                AddSellerContract.Intent.AddSeller(
                                    name, password
                                )
                            )
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    }
                },
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { if (it.length <= 10) name = it },
                label = { Text("Name") },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { if (it.length <= 10) password = it },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                singleLine = true,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}