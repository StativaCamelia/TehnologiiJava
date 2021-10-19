package com.example.laborator2.service;

import com.example.laborator2.bean.Category;
import com.example.laborator2.bean.Record;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Service class for the CRUD operations on the Record entity
 * The class will be a singleton
 */
public class RecordService {

    private static RecordService recordServiceInstance;

    private RecordService(){

    }

    public static RecordService getRecordServiceInstance(){

        if(recordServiceInstance == null){

            recordServiceInstance = new RecordService();
        }

        return recordServiceInstance;
    }

    /**
     * Saves a record in a json file
     * @param value
     *          The value for the record entity
     * @param key
     *          The key from the request for the record entity
     * @param category
     *          The category from the request for the record entity
     */
    public void postRecord(String value, String key, Category category) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("record.json");
        JsonGenerator g = objectMapper.getFactory().createGenerator(new FileOutputStream(file));

        Record record = new Record();
        record.setCategory(category);
        record.setKey(key);
        record.setValue(value);

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        objectMapper.writeValue(out, record);

        g.close();
    }

    public List<Record> getRecord() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        String fileName = "record.json";
        return Arrays.asList(objectMapper.readValue(new File(fileName), Record[].class));
    }
}
