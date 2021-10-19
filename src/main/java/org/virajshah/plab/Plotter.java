package org.virajshah.plab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class Plotter {
    public static void displayChart(HashMap<Integer, long[]> data) {

        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).theme(ChartTheme.Matlab)
                .title("Parallel Matrix Multiplication in Java").xAxisTitle("Matrix Size (Sq)")
                .yAxisTitle("Runtime (ms)").build();

        // Customize Chart
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setXAxisTickMarkSpacingHint(100);

        // Series
        List<Integer> xData = new ArrayList<Integer>();
        List<List<Long>> yDatas = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            yDatas.add(new ArrayList<Long>());

        for (Map.Entry<Integer, long[]> entry : data.entrySet()) {
            xData.add(entry.getKey());
            for (int i = 0; i < entry.getValue().length; i++) {
                yDatas.get(i).add(entry.getValue()[i]);
            }
        }

        XYSeries series = chart.addSeries("Series", xData, yDatas.get(0));
        series.setMarker(SeriesMarkers.NONE);

        for (int i = 1; i < yDatas.size(); i++) {
            series = chart.addSeries(String.format("x%d (Parallel)", i + 1), xData, yDatas.get(i));
            series.setMarker(SeriesMarkers.NONE);
        }

        new SwingWrapper<XYChart>(chart).displayChart();
    }
}
