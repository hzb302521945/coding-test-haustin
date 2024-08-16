package com.example.codingtesthaustin.service.impl;

import com.example.codingtesthaustin.factory.OperationFactory;
import com.example.codingtesthaustin.factory.impl.DeleteOperationFactory;
import com.example.codingtesthaustin.factory.impl.ReplaceOperationFactory;
import com.example.codingtesthaustin.operation.IOperation;
import com.example.codingtesthaustin.service.IOperationService;
import org.springframework.stereotype.Service;

/**
 * 操作(删除或替换)字符串的 Service 实现类, 这里使用了工厂方法设计模式
 */
@Service
public class OperationServiceImpl implements IOperationService {


    /**
     * 删除重复字符
     * @param inputStr
     * @return
     */
    @Override
    public String deleteRepeatedChars(String inputStr) {

        // 工厂方法设计模式
        OperationFactory deleteOperationFactory = new DeleteOperationFactory();     // 创建工厂
        IOperation deleteOperation = deleteOperationFactory.createOperation();      // 创建产品(具体的操作类)
        String resultStr = deleteOperation.operate(inputStr);                       // 操作字符串

        return resultStr;
    }

    /**
     * 替换重复字符
     * @param inputStr
     * @return
     */
    @Override
    public String replaceRepeatedChars(String inputStr) {

        // 工厂方法设计模式
        OperationFactory replaceOperationFactory = new ReplaceOperationFactory();   // 创建工厂
        IOperation replaceOperation = replaceOperationFactory.createOperation();    // 创建产品(具体的操作类)
        String resultStr = replaceOperation.operate(inputStr);                      // 操作字符串

        return resultStr;
    }
}
