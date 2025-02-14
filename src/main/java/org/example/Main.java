package org.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class Main extends JFrame {

    // Kriter ağırlıkları: [Nüfus Yoğunluğu, Ulaşım Altyapısı, Maliyet Analizi, Çevresel Etki, Sosyal Fayda]
    private static final double[] WEIGHTS = {0.30, 0.25, 0.20, 0.15, 0.10};

    // Mahalle bilgilerini tutan iç sınıf
    static class Neighborhood {
        String name;
        double[] scores;          // 5 kriter için sentetik skorlar
        Coordinate coordinate;    // OpenStreetMap koordinatları (enlem, boylam)

        public Neighborhood(String name, double[] scores, Coordinate coordinate) {
            this.name = name;
            this.scores = scores;
            this.coordinate = coordinate;
        }
    }

    private JMapViewer map;

    // Constructor: Pencere özelliklerini ayarlar
    public Main() {
        super("Toplu Taşıma Hattı Planlaması");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        map = new JMapViewer();
        add(map);
    }

    // Softmax dönüşümü: Giriş değerlerini normalize edilmiş olasılık dağılımına çevirir
    public static List<Double> softmax(List<Double> values) {
        List<Double> expValues = new ArrayList<>();
        double max = Collections.max(values);  // Numerik stabilite için maksimum değeri kullanıyoruz
        double sum = 0.0;
        for (double value : values) {
            double expVal = Math.exp(value - max);
            expValues.add(expVal);
            sum += expVal;
        }
        List<Double> softmaxValues = new ArrayList<>();
        for (double expVal : expValues) {
            softmaxValues.add(expVal / sum);
        }
        return softmaxValues;
    }

    // Bir mahalle için ağırlıklı skoru hesaplar
    public static double computeWeightedScore(double[] scores) {
        double sum = 0.0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i] * WEIGHTS[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        // Mahalle verilerini oluşturuyoruz (sentetik kriter skorları ve örnek koordinatlar)
        List<Neighborhood> neighborhoods = new ArrayList<>();
        neighborhoods.add(new Neighborhood("Merkez Mahallesi", new double[]{9, 8, 7, 6, 8}, new Coordinate(41.7360, 27.2297)));
        neighborhoods.add(new Neighborhood("Cumhuriyet Mahallesi", new double[]{7, 7, 8, 7, 7}, new Coordinate(41.7375, 27.2320)));
        neighborhoods.add(new Neighborhood("Atatürk Mahallesi", new double[]{8, 6, 6, 8, 9}, new Coordinate(41.7350, 27.2250)));

        // Her mahalle için ağırlıklı skoru hesaplıyoruz
        List<Double> weightedScores = new ArrayList<>();
        Map<String, Double> neighborhoodScoreMap = new HashMap<>();
        for (Neighborhood nb : neighborhoods) {
            double score = computeWeightedScore(nb.scores);
            weightedScores.add(score);
            neighborhoodScoreMap.put(nb.name, score);
            System.out.println(nb.name + " ağırlıklı skor: " + score);
        }

        // Ağırlıklı skorlar üzerinden softmax dönüşümü yapıyoruz
        List<Double> softmaxValues = softmax(weightedScores);
        int idx = 0;
        Map<String, Double> neighborhoodSoftmaxMap = new HashMap<>();
        for (Neighborhood nb : neighborhoods) {
            double smVal = softmaxValues.get(idx);
            neighborhoodSoftmaxMap.put(nb.name, smVal);
            System.out.println(nb.name + " softmax değeri: " + smVal);
            idx++;
        }

        // En yüksek softmax değerine sahip mahalleyi belirleyelim
        String bestNeighborhood = null;
        double maxProbability = -1;
        for (Map.Entry<String, Double> entry : neighborhoodSoftmaxMap.entrySet()) {
            if (entry.getValue() > maxProbability) {
                maxProbability = entry.getValue();
                bestNeighborhood = entry.getKey();
            }
        }
        System.out.println("\nEn uygun güzergah: " + bestNeighborhood);

        // bestNeighborhood değişkenini inner class içinde kullanabilmek için final yapıyoruz.
        final String finalBestNeighborhood = bestNeighborhood;

        // Harita GUI'sini oluşturup mahalleleri işaretleyelim
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.map.setZoomControlsVisible(true);
                frame.map.setScrollWrapEnabled(true);
                // Harita merkezini (örneğin mahallelerin ortası) belirleyip yakınlaştırma seviyesini ayarlıyoruz.
                frame.map.setDisplayPosition(new Coordinate(41.7365, 27.2280), 14);

                for (Neighborhood nb : neighborhoods) {
                    MapMarkerDot marker = new MapMarkerDot(nb.name, nb.coordinate);
                    // En uygun mahalleyi kırmızı, diğerlerini mavi ile işaretleyelim.
                    if (nb.name.equals(finalBestNeighborhood)) {
                        marker.setBackColor(Color.RED);
                    } else {
                        marker.setBackColor(Color.BLUE);
                    }
                    frame.map.addMapMarker(marker);
                }
                frame.setVisible(true);
            }
        });
    }
}
