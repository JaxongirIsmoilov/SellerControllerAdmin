package uz.gita.jaxongir.sellmanageradmin.utills

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import uz.gita.jaxongir.sellmanageradmin.domain.repository.AppRepository

@HiltWorker
class MyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: AppRepository,
) : CoroutineWorker(context, params) {
    private val notificationHelper by lazy { NotificationHelper(context) }
    private val scope = CoroutineScope(Dispatchers.IO + Job())

    @SuppressLint("MissingPermission")
    override suspend fun doWork(): Result {
        try {
            scope.launch {
                notificationHelper.notificationManagerCompat.notify(
                    1,
                    notificationHelper.getNotification()
                )
                repository.clearSoldProjects()
            }
            Result.retry()
        } catch (e: Exception) {
            Result.failure()
        }
        return Result.success()
    }
}