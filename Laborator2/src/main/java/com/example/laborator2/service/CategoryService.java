package com.example.laborator2.service;

import com.example.laborator2.bean.Category;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Service class for the CRUD operations on the Category entity
 * The class will be a singleton
 */
public class CategoryService {

    private static CategoryService instance;

    private CategoryService(){
    }

    public static CategoryService getCategoryService(){
        if(instance == null){
            instance = new CategoryService();
        }
        return instance;
    }

    /**
     * Read the categories from a JSON file and returns them
     * @return
     *      a list with the read categories
     */
    public List<Category> getCategories() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        String fileName = "categories.json";
        return Arrays.asList(objectMapper.readValue(new File(fileName), Category[].class));
    }


}
