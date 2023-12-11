package uz.gita.jaxongir.sellmanageradmin

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.google.firebase.messaging.FirebaseMessaging
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.jaxongir.sellmanageradmin.data.source.local.pref.MyPref
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository
import uz.gita.jaxongir.sellmanageradmin.presenter.login.LoginScreen
import uz.gita.jaxongir.sellmanageradmin.ui.theme.SellManagerAdminTheme
import uz.gita.jaxongir.sellmanageradmin.utills.MyWorker
import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigationHandler
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationHandler: AppNavigationHandler
    @Inject
    lateinit var repository: AppRepository
    @Inject
    lateinit var myPref: MyPref
    @SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }

            val token = task.result
            myPref.setAdminToken(token)

        }

        val myWork = PeriodicWorkRequestBuilder<MyWorker>(
            15, TimeUnit.MINUTES
        )
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "name", ExistingPeriodicWorkPolicy.KEEP,
            myWork
        )

        val periodWorker =
            PeriodicWorkRequestBuilder<MyWorker>(24, TimeUnit.HOURS)
                .build()
        WorkManager.getInstance(this).enqueue(periodWorker)


        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    checkNotificationPermission()
                }
            }
            SellManagerAdminTheme {
                Navigator(screen = LoginScreen()) { navigate ->
                    navigationHandler.uiNavigator
                        .onEach { it.invoke(navigate) }
                        .launchIn(lifecycleScope)
                    CurrentScreen()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkNotificationPermission() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.ACCESS_NOTIFICATION_POLICY,
                Manifest.permission.USE_EXACT_ALARM,
                Manifest.permission.SCHEDULE_EXACT_ALARM
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (it.areAllPermissionsGranted()) {
                        }
                        if (it.isAnyPermissionPermanentlyDenied) {
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?,
                ) {
                    token?.continuePermissionRequest()
                }
            })
            .withErrorListener {

            }
            .onSameThread()
            .check()

    }
}
