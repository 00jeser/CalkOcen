package com.example.creatif.calkocen;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    TextView ocens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ocens = (TextView) this.findViewById(R.id.editText);
        draw("0");
        ocens.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                draw(ocens.getText().toString());
            }
        });
    }

    public void draw(String txt) {
        if (txt.length() == 0) return;
        try {

            double x, y;
            x = 0;
            y = 0;
            GraphView graph = (GraphView) findViewById(R.id.graph);
            graph.removeAllSeries();
            series = new LineGraphSeries<DataPoint>();
            graph.getViewport().setMaxX(txt.length() - 1);
            graph.getViewport().setMaxY(5);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMinY(0);
            graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(1);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setYAxisBoundsManual(true);
            double s = 0;
            String[] ss = new String[txt.length()];
            for (int i = 0; i < txt.length(); i++) {
                int ocen = Integer.parseInt(txt.toCharArray()[i] + "");
                y = ocen;
                s += ocen;
                series.appendData(new DataPoint(x, y), true, 500);
                x += 1;
                ss[i] = "";
            }
            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            staticLabelsFormatter.setHorizontalLabels(new String[] {"", ""});
            staticLabelsFormatter.setVerticalLabels(new String[] {"", "", "2", "3", "4", "5"});
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            ((TextView) this.findViewById(R.id.textView)).setText((s / txt.length()) + "");
            series.setColor(Color.argb(125, 0, 0, 255));
            series.setThickness(15);
            graph.addSeries(series);
        } catch (Exception ex) {
        }
    }
}
