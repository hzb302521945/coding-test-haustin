package com.example.codingtesthaustin.factory.impl;

import com.example.codingtesthaustin.factory.OperationFactory;
import com.example.codingtesthaustin.operation.IOperation;
import com.example.codingtesthaustin.operation.impl.ReplaceOperation;

public class ReplaceOperationFactory extends OperationFactory {

    @Override
    public IOperation createOperation() {

        return new ReplaceOperation();
    }
}
