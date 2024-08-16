package com.example.codingtesthaustin.factory;

import com.example.codingtesthaustin.operation.IOperation;

/**
 * Abstract factory class
 */
public abstract class OperationFactory {

    /**
     *
     * Abstract methods for creating product
     * @return
     */
    public abstract IOperation createOperation();

}
