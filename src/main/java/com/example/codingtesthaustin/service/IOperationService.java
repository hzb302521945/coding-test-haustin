package com.example.codingtesthaustin.service;

public interface IOperationService {


    /**
     * 删除重复字符
     * @param inputStr
     * @return
     */
    String deleteRepeatedChars(String inputStr);

    /**
     * 替换重复字符
     * @param inputStr
     * @return
     */
    String replaceRepeatedChars(String inputStr);


}
