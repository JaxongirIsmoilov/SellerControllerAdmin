package uz.gita.jaxongir.sellmanageradmin.utills


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import uz.gita.jaxongir.sellmanageradmin.R

class NotificationHelper(private val context: Context) {

    private val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(context, "Notification")
            .setSmallIcon(R.drawable.ic_statistics_selected)
            .setContentTitle("Product App")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
    }

    val notificationManagerCompat: NotificationManagerCompat by lazy {
        NotificationManagerCompat.from(
            context
        )
    }


    fun getNotification(): Notification {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Product App"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("Notification", name, importance)
            notificationManagerCompat.createNotificationChannel(mChannel)
        }

        return notificationBuilder.build()
    }

}