package com.example.codingtesthaustin.service.impl;

import com.example.codingtesthaustin.factory.OperationFactory;
import com.example.codingtesthaustin.factory.impl.DeleteOperationFactory;
import com.example.codingtesthaustin.factory.impl.ReplaceOperationFactory;
import com.example.codingtesthaustin.operation.IOperation;
import com.example.codingtesthaustin.service.IOperationService;
import org.springframework.stereotype.Service;

/**
 * The Service implementation class for operating (deleting or replacing) strings uses the factory method design pattern
 */
@Service
public class OperationServiceImpl implements IOperationService {


    /**
     * Delete repeated characters
     * @param inputStr
     * @return
     */
    @Override
    public String deleteRepeatedChars(String inputStr) {

        // Factory Method Design Pattern
        OperationFactory deleteOperationFactory = new DeleteOperationFactory();     // Create Factory
        IOperation deleteOperation = deleteOperationFactory.createOperation();      // Create product (specific operation class)
        String resultStr = deleteOperation.operate(inputStr);                       // Operate the string

        return resultStr;
    }

    /**
     *
     * Replace repeated characters
     * @param inputStr
     * @return
     */
    @Override
    public String replaceRepeatedChars(String inputStr) {

        // Factory Method Design Pattern
        OperationFactory replaceOperationFactory = new ReplaceOperationFactory();   // Create Factory
        IOperation replaceOperation = replaceOperationFactory.createOperation();    // Create product (specific operation class)
        String resultStr = replaceOperation.operate(inputStr);                      // Operate the string

        return resultStr;
    }
}
