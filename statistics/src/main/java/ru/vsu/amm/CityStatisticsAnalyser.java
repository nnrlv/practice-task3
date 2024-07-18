package ru.vsu.amm;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CityStatisticsAnalyser {
    private static final Logger logger = Logger.getLogger(CityStatisticsAnalyser.class.getName());
    public static void main(String[] args) {

        logger.log(Level.INFO, "City statistics calculation started");

        try {
            Path path = Paths.get(Objects.requireNonNull(Thread.currentThread().getContextClassLoader()
                    .getResource("answers.json")).toURI());

            logger.log(Level.INFO, "City statistics records loaded successfully");

            CityStatistics cityStatisticsCalculations = new CityStatistics();
            List<CityStatisticsEntry> statistics = CityStatisticsEntryJsonReader.readJsonFile(path);

            System.out.println("Наиболее популярный ответ для городов, название которых начинается на 'А': " +
                    cityStatisticsCalculations.mostPopularAnswerForCitiesStartingWithA(statistics));

            System.out.println("Город, в котором дали больше всего разнообразных ответов: " +
                    cityStatisticsCalculations.cityWithMostDiverseAnswers(statistics));

            System.out.println("Города, где отвечали так же, как наиболее часто отвечали в Москве: " +
                    cityStatisticsCalculations.citiesWithMatchingAnswersAsMoscow(statistics));
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        logger.log(Level.INFO, "City statistics calculation ended");
    }
}
