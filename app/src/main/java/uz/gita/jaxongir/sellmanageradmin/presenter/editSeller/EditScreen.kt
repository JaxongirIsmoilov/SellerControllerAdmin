package uz.gita.jaxongir.sellmanageradmin.presenter.editSeller

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita.jaxongir.sellmanageradmin.data.models.SellerData
import uz.gita.jaxongir.sellmanageradmin.presenter.addSeller.AddSellerContract
import uz.gita.jaxongir.sellmanageradmin.ui.components.Dialog

class EditScreen(private val sellerData: SellerData): AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<EditViewModel>()
        val context = LocalContext.current
        EditScreenContent(onEventDispatcher = viewModel::eventDispatchers, sellerData = sellerData)
        val sideEffect = viewModel.sideEffect.collectAsState()
        when(sideEffect.value){
            EditContract.SideEffect.Init -> {}
            is EditContract.SideEffect.ShowNotification -> {
                Dialog(text = (sideEffect.value as EditContract.SideEffect.ShowNotification).message ) {
                }
            }

            is EditContract.SideEffect.ShowToast -> {
                Toast.makeText(context, (sideEffect.value as EditContract.SideEffect.ShowToast).message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenContent(
    onEventDispatcher : (EditContract.Intent) -> Unit,
    sellerData: SellerData
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            var name by remember { mutableStateOf(sellerData.sellerName) }
            var password by remember { mutableStateOf(sellerData.password) }


            TopAppBar(
                title = {
                    Text(
                        text = "Edit",
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
                        modifier = Modifier.clickable { onEventDispatcher.invoke(EditContract.Intent.MoveToSellerScreen) }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEventDispatcher.invoke(
                                EditContract.Intent.EditSeller(
                                    SellerData(sellerData.sellerId, name, password)
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
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}