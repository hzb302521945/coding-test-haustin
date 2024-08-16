package com.example.codingtesthaustin.factory.impl;

import com.example.codingtesthaustin.factory.OperationFactory;
import com.example.codingtesthaustin.operation.IOperation;
import com.example.codingtesthaustin.operation.impl.DeleteOperation;

/**
 * Factory class of DeleteOperation
 */
public class DeleteOperationFactory extends OperationFactory {


    /**
     * Create specific product (DeleteOperation)
     * @return
     */
    @Override
    public IOperation createOperation() {

        return new DeleteOperation();
    }
}
