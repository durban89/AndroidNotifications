package com.gowhich.androidnotifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;

    private NotificationManager notificationManager;
    private Notification.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) this.findViewById(R.id.button);
        button2 = (Button) this.findViewById(R.id.button2);
        button3 = (Button) this.findViewById(R.id.button3);
        button4 = (Button) this.findViewById(R.id.button4);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(MainActivity.this, "提示信息", Toast.LENGTH_SHORT);

                toast.setGravity(Gravity.TOP, 0, 0);

                toast.show();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = new Toast(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.toast_layout, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.image);
                imageView.setImageResource(R.drawable.face4);
                TextView textView = (TextView) view.findViewById(R.id.text);
                textView.setText("自定义通知");
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.setView(view);
                toast.show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                builder.setContentIntent(pendingIntent);
                builder.setContentTitle("Notification is coming");
                builder.setContentText("Hello world");
                builder.setTicker("有新通知来了");
                builder.setSmallIcon(R.drawable.face4);

                //所有的都是默认的
                builder.setDefaults(Notification.DEFAULT_ALL);

                // DEFAUTL_SOUND //默认的声音
//                builder.setSound();
                // DEFAULT_LIGHTS //默认的闪光
                Notification notification = builder.build();//限于高版本4.1中使用
                notificationManager.notify(1000, notification);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.custom_notification_layout);
                remoteViews.setImageViewResource(R.id.imageView, R.drawable.face4);
                remoteViews.setTextViewText(R.id.textView3, "自定义通知标题");
                remoteViews.setTextViewText(R.id.textView2, "自定义通知内容");

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);


                builder.setContent(remoteViews);
                builder.setContentIntent(pendingIntent);
                builder.setTicker("收到新的短信");
                Notification notification = builder.build();//限于高版本4.1中使用
                notificationManager.notify(2000, notification);

            }
        });
    }
}
