
package by.graph.graphproject;


import java.util.Random;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private static final int SERIES_NR = 2;
	
        private String[] label = new String[]{"Скаттерограмма", "Гистограмма"};

        @Override
        public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1,label));
        }

        @Override
        protected void onListItemClick(ListView l, View v, int position, long id) {
          Intent intent = null;
          switch (position) {
			case 0:
		        intent = new ScatterPlot().execute(getApplicationContext());
		        startActivity(intent);
				break;
			case 1:
				XYMultipleSeriesRenderer renderer = getBarDemoRenderer();
				setChartSettings(renderer);
				intent = ChartFactory.getBarChartIntent(this, getBarDemoDataset(), renderer, Type.DEFAULT);
				startActivity(intent);
				break;
		}
          
        }
        
        public XYMultipleSeriesRenderer getBarDemoRenderer() {
            XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
            renderer.setAxisTitleTextSize(16);
            renderer.setChartTitleTextSize(20);
            renderer.setLabelsTextSize(15);
            renderer.setLegendTextSize(15);
            renderer.setMargins(new int[] {20, 30, 15, 0});
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(Color.BLUE);
            renderer.addSeriesRenderer(r);
            r = new SimpleSeriesRenderer();
            r.setColor(Color.GREEN);
            renderer.addSeriesRenderer(r);
            return renderer;
          }
        
        private void setChartSettings(XYMultipleSeriesRenderer renderer) {
            renderer.setChartTitle("Chart demo");
            renderer.setXTitle("x values");
            renderer.setYTitle("y values");
            renderer.setXAxisMin(0.5);
            renderer.setXAxisMax(10.5);
            renderer.setYAxisMin(0);
            renderer.setYAxisMax(210);
          }
        
        private XYMultipleSeriesDataset getBarDemoDataset() {
            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
            final int nr = 10;
            Random r = new Random();
            for (int i = 0; i < SERIES_NR; i++) {
              CategorySeries series = new CategorySeries("Demo series " + (i + 1));
              for (int k = 0; k < nr; k++) {
                series.add(100 + r.nextInt() % 100);
              }
              dataset.addSeries(series.toXYSeries());
            }
            return dataset;
          }
}
