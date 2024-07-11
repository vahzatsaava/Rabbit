import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitCarrotCount {
    private static final Logger LOGGER = Logger.getLogger(RabbitCarrotCount.class.getName());

    public static void main(String[] args) {
        int[] carrotWeights = {1, 2, 3, 4, 5};
        int maxCarrotsPerCount = 5;
        int maxCounts = 10;

        int maxCarrots = getMaxCarrots(carrotWeights, maxCarrotsPerCount, maxCounts);
        LOGGER.log(Level.INFO, "Максимальное количество моркови, которое может унести заяц: {0} кг", maxCarrots);
    }

    public static int getMaxCarrots(int[] carrotWeights, int maxCarrotsPerTrip, int maxTrips) {
        List<Integer> trips = new ArrayList<>();

        // 1) перебираем все варианты комбинаций морковок от 1 до 5
        for (int i = 0; i < carrotWeights.length; i++) {
            for (int j = i; j < carrotWeights.length; j++) {
                int sum = carrotWeights[i] + carrotWeights[j];
                if (sum <= maxCarrotsPerTrip) {
                    trips.add(sum);
                }
            }
        }

        //2) добавляем по отдельности каждую морковку в нашу корзину комбинаций
        for (int weight : carrotWeights) {
            if (weight <= maxCarrotsPerTrip) {
                trips.add(weight);
            }
        }

        trips.sort(Collections.reverseOrder());

        int totalCarrots = 0;
        // 3) суммируем наши варианты комбинаций 10 раз начиная с максимального колличества морковок
        for (int i = 0; i < maxTrips && i < trips.size(); i++) {
            totalCarrots += trips.get(i);
        }

        return totalCarrots;
    }
}
