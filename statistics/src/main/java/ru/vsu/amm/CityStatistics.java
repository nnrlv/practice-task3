package ru.vsu.amm;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CityStatistics {

    // Наиболее популярный ответ для городов, название которых начинается на 'А'
    public String mostPopularAnswerForCitiesStartingWithA(List<CityStatisticsEntry> data) {
        return data.stream()
                .filter(entry -> entry.getCity().startsWith("А"))
                .collect(Collectors.groupingBy(CityStatisticsEntry::getAnswer,
                        Collectors.summingLong(CityStatisticsEntry::getRespondentsCount)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    // Город, в котором дали больше всего разнообразных ответов
    public String cityWithMostDiverseAnswers(List<CityStatisticsEntry> data) {
        Map<String, Long> cityAnswersCount = data.stream()
                .collect(Collectors.groupingBy(CityStatisticsEntry::getCity,
                        Collectors.mapping(CityStatisticsEntry::getAnswer,
                                Collectors.collectingAndThen(Collectors.toSet(),
                                        set -> (long) set.size()))));

        long maxDiverseAnswersCount = cityAnswersCount.values().stream().max(Long::compare).orElse(0L);

        // Если максимальное количество разнообразных ответов у нескольких городов, возвращаем пустую строку
        if (maxDiverseAnswersCount > 1 && Collections.frequency(cityAnswersCount.values(), maxDiverseAnswersCount) > 1) {
            return "";
        }

        // В противном случае, находим город с максимальным количеством разнообразных ответов
        return cityAnswersCount.entrySet().stream()
                .filter(entry -> entry.getValue() == maxDiverseAnswersCount)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("");
    }

    // Города, где отвечали так же, как наиболее часто отвечали в Москве
    public List<String> citiesWithMatchingAnswersAsMoscow(List<CityStatisticsEntry> data) {
        String moscowMostFrequentAnswer = data.stream()
                .filter(entry -> entry.getCity().equals("Москва"))
                .collect(Collectors.groupingBy(CityStatisticsEntry::getAnswer,
                        Collectors.summingLong(CityStatisticsEntry::getRespondentsCount)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");

        return data.stream()
                .filter(entry -> !entry.getCity().equals("Москва") && entry.getAnswer().equals(moscowMostFrequentAnswer))
                .map(CityStatisticsEntry::getCity)
                .distinct()
                .toList();
    }
}
