import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;

public class EnergyGraph {

    public static void showLineGraph(double[] withBattery, double[] withoutBattery) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < 24; i++) {
            dataset.addValue(withBattery[i], "With Battery", "H" + (i + 1));
            dataset.addValue(withoutBattery[i], "Without Battery", "H" + (i + 1));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "24 Hour Smart Energy Cost Comparison",
                "Hour",
                "Total Cost (₹)",
                dataset
        );

        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 22));

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(0, 153, 0));
        renderer.setSeriesStroke(0, new BasicStroke(3.5f));
        renderer.setSeriesShapesVisible(0, true);

        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesStroke(1, new BasicStroke(3.5f));
        renderer.setSeriesShapesVisible(1, true);

        plot.setRenderer(renderer);

        // Save PNG automatically
        try {
            ChartUtils.saveChartAsPNG(new File("EnergyReport.png"), chart, 1000, 700);
            System.out.println("PNG saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Show graph window
        ChartFrame frame = new ChartFrame("Smart Energy System - Cost Analysis", chart);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}