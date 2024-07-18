package ru.vsu.amm;

import java.util.Objects;

public class CityStatisticsEntry {
    private final String city;
    private final int respondentsCount;
    private final String answer;

    public CityStatisticsEntry() {
        this.city = null;
        this.respondentsCount = 0;
        this.answer = null;
    }

    public CityStatisticsEntry(String city, int respondentsCount, String answer) {
        this.city = city;
        this.respondentsCount = respondentsCount;
        this.answer = answer;
    }

    public String getCity() {
        return city;
    }

    public int getRespondentsCount() {
        return respondentsCount;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityStatisticsEntry that = (CityStatisticsEntry) o;
        return respondentsCount == that.respondentsCount &&
                Objects.equals(city, that.city) &&
                Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, respondentsCount, answer);
    }
}

