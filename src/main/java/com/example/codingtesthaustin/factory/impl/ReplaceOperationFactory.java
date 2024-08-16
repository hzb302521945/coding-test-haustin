package com.example.codingtesthaustin.factory.impl;

import com.example.codingtesthaustin.factory.OperationFactory;
import com.example.codingtesthaustin.operation.IOperation;
import com.example.codingtesthaustin.operation.impl.ReplaceOperation;

/**
 * Factory class of ReplaceOperation
 */
public class ReplaceOperationFactory extends OperationFactory {

    /**
     * Create specific product (ReplaceOperation)
     * @return
     */
    @Override
    public IOperation createOperation() {

        return new ReplaceOperation();
    }
}
