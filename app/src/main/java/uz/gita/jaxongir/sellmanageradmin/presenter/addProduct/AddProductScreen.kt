package uz.gita.jaxongir.sellmanageradmin.presenter.addProduct

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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import uz.gita.jaxongir.sellmanageradmin.presenter.addSeller.AddSellerContract

class AddProductScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<AddProductViewModel>()
        AddProductContent(onEventDispatcher = viewModel::onEventDispatcher)
        val sideEffect = viewModel.sideEffect.collectAsState()
        val context = LocalContext.current
        when(sideEffect.value){
            AddProductContract.SideEffect.Init -> {}
            is AddProductContract.SideEffect.ShowNotification -> {
                Toast.makeText(context, (sideEffect.value as AddProductContract.SideEffect.ShowNotification).message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductContent(
    onEventDispatcher: (AddProductContract.Intent) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            var productName by remember { mutableStateOf("") }
            var productCount by remember { mutableIntStateOf(0) }
            var productInitialPrice by remember { mutableDoubleStateOf(0.0) }


            TopAppBar(
                title = {
                    Text(
                        text = "Add Product",
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
                        modifier = Modifier.clickable { onEventDispatcher.invoke(AddProductContract.Intent.Back) }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            onEventDispatcher.invoke(
                                AddProductContract.Intent.AddProduct(
                                    productName, productCount, productInitialPrice
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
                value = productName,
                onValueChange = { if (it.length <= 10) productName = it },
                label = { Text("Name") },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = productCount.toString(),
                onValueChange = { newValue ->
                    if (newValue.isNotBlank()) {
                        val parsedCount = newValue.toIntOrNull() ?: 0
                        productCount = parsedCount
                    } else {
                        productCount = 0
                    }
                },
                label = { Text("Product Count") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = productInitialPrice.toString(),
                onValueChange = { newValue ->
                    if (newValue.isNotBlank()) {
                        val parsedPrice = newValue.toDoubleOrNull() ?: 0.0
                        productInitialPrice = parsedPrice
                    } else {
                        productInitialPrice = 0.0
                    }
                },
                label = { Text("Initial Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )



            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

