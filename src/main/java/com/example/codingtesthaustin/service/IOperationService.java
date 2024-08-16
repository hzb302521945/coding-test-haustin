package com.example.codingtesthaustin.service;

/**
 * Service interface for operating strings
 */
public interface IOperationService {


    /**
     * Delete repeated characters
     * @param inputStr
     * @return
     */
    String deleteRepeatedChars(String inputStr);

    /**
     * Replace repeated characters
     * @param inputStr
     * @return
     */
    String replaceRepeatedChars(String inputStr);


}
