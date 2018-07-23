package tutorial.demo.btes.com.servicesapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends IntentService {
    public static final String MESSAGE = "message";
    public static final int NOTIFICATION_ID = 1356;
    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent){
        synchronized (this) {
            try {
                wait(10000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String text = intent.getStringExtra(MESSAGE);
        showText(text);

    }


    private void showText(final String text) {
        Log.v("MyService", text);
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Helllo")
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setContentText(text)
                .build();
        NotificationManager notificationManager =  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,notification);

    }


}
