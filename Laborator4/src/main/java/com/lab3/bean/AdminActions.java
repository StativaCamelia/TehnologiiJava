package com.lab3.bean;

import com.lab3.model.Documents;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface AdminActions {

    void setTimeFrame();

    List<Documents> viewAllSubmits() throws UnsupportedEncodingException;
}
