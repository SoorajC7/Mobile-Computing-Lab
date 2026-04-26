package com.example.experiment9;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

public class MyWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(
                    context.getPackageName(),
                    R.layout.widget_layout
            );
            views.setTextViewText(
                    R.id.widget_text, "Widget is working!"
            );
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
