package com.example.codingtesthaustin.operation.impl;

import com.example.codingtesthaustin.operation.IOperation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * IOperation 的实现类: 删除操作
 *
 * @author Haustin
 */
public class DeleteOperation implements IOperation {

    /**
     * 具体的字符串操作方法
     * @param inputStr
     * @return
     */
    @Override
    public String operate(String inputStr) {

        // jdk8 新特性: 判断空指针
        Optional<String> optional = Optional.ofNullable(inputStr);
        if(optional.isEmpty()){
            return "error: inputStr must not be null";
        }

        // 判断字符串长度是否为 0
        if(optional.get().length() == 0) {
            return "error: length of inputStr must not be zero";
        }

        // jdk8 new feature : 判断输入的字符串是不是全部为小写字母
        List<Character> charList = inputStr.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        Boolean allLowerCaseLetters = charList.stream().allMatch(c -> (int)c >= 97 && (int)c <= 122);
        if(!allLowerCaseLetters){
            return "error, 请确保输入的字符串只包含 a-z 的小写字母";
        }

        // 字符串长度小于 3 则直接返回
        if(optional.get().length() < 3) {
            return optional.get();
        }


        char[] charArr = inputStr.toCharArray();            // 输入的字符串转化为 char[]
        Set<Integer> indexSetToDelete = new HashSet<>();    // 用于存储需要被删除的字符的下标
        int repeatCount = 1;                                // 相邻字符已重复次数
        Boolean existsMoreThan3Repeats = false;             // 是否存在3个相邻的重复字符, 起始默认为 false
        Boolean firstInvoke = true;                         // 是否为首次调用递归方法(用于控制控制台的输出内容)

        // 开始删除相邻的重复字符(里面包含递归)
        char[] finalCharArr = deleteMoreThan3RepeatedChars(charArr, repeatCount, indexSetToDelete, existsMoreThan3Repeats, firstInvoke);

        if(finalCharArr == null || finalCharArr.length == 0){
            return "";
        } else {
            return new String(finalCharArr);
        }

    }


    /**
     * 删除相邻的重复字符(里面包含递归)
     * @param charArr
     * @param repeatCount
     * @param indexSetToDelete
     * @param existsMoreThan3Repeats
     * @return
     */
    private char[] deleteMoreThan3RepeatedChars(char[] charArr,
                                                int repeatCount,
                                                Set<Integer> indexSetToDelete,
                                                Boolean existsMoreThan3Repeats,
                                                Boolean firstInvoke) {

        // 循环, 将需要被删除的字符的下标做标记
        for(int i = 0; i < charArr.length; i++){

            if(i == 0){ // 第一个字符, 不做任何处理

            } else {    // 从第二个字符开始处理
                if(charArr[i] == charArr[i-1]){             // 当前字符跟前一个字符相同
                    repeatCount++;                          // 重复次数 +1
                    if(repeatCount >= 3){                   // 重复次数达到3次或以上
                        // 将需要被删除的下标保存到Set里
                        indexSetToDelete.add(i - 2);
                        indexSetToDelete.add(i - 1);
                        indexSetToDelete.add(i);

                        existsMoreThan3Repeats = true;      // 如果为 true , 则需要进行下一步递归
                    }
                } else {                                    // 当前字符跟前一个字符不同
                    repeatCount = 1;                        // 重置为1
                }
            }
        }


        // 新建数组, 获取被删除后的结果
        char[] resultCharArr = new char[charArr.length - indexSetToDelete.size()];
        for(int j = 0, k = 0; j < charArr.length; j++){
            char currentChar = charArr[j];
            if(!indexSetToDelete.contains(j)){
                resultCharArr[k++] = currentChar;
            }
        }

        // 将本次次操作的结果输出到控制台
        if(firstInvoke || existsMoreThan3Repeats){
            if(resultCharArr == null || resultCharArr.length == 0){
                System.out.println("");
            } else {
                System.out.println(new String(resultCharArr));
            }
        }

        // 判断是否需要递归操作
        // 1.如果当前存在重复3次的情况, 则递归
        // 2.如果当前不存在任何重复3次的情况, 则无需递归
        if(existsMoreThan3Repeats){
            // 递归参数设置
            repeatCount = 1;                    // 递归前重置为 1
            indexSetToDelete.clear();           // 递归前清空 Set
            existsMoreThan3Repeats = false;     // 递归前设置为 false

            // 递归调用
            resultCharArr = deleteMoreThan3RepeatedChars(resultCharArr, repeatCount, indexSetToDelete, existsMoreThan3Repeats, false);

        }

        return resultCharArr;
    }

}
