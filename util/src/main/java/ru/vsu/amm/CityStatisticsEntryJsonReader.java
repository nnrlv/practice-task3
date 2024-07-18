package ru.vsu.amm;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CityStatisticsEntryJsonReader {
    public static List<CityStatisticsEntry> readJsonFile(Path filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CityStatisticsEntry[] entries = objectMapper.readValue(new File(filePath.toString()),
                    CityStatisticsEntry[].class);
            return Arrays.asList(entries);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
