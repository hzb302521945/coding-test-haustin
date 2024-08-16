package com.example.codingtesthaustin.operation.impl;

import com.example.codingtesthaustin.operation.IOperation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * IOperation 的实现类: 替换操作
 *
 * @author Haustin
 */
public class ReplaceOperation implements IOperation {


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

        // jdk8 新特性: 判断输入的字符串是不是全部为小写字母
        List<Character> charList = inputStr.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        Boolean allLowerCaseLetters = charList.stream().allMatch(c -> (int)c >= 97 && (int)c <= 122);
        if(!allLowerCaseLetters){
            return "输入无效, 请确保输入的字符串只包含 a-z 的小写字母";
        }

        // 字符串长度小于 3 则直接返回
        if(optional.get().length() < 3) {
            return optional.get();
        }


        char[] charArr = inputStr.toCharArray();            // 输入的字符串转化为 char[]
        Set<Integer> indexSetToReplace = new HashSet<>();   // 用于存储需要被删除的字符的下标
        int repeatCount = 1;                                // 相邻字符已重复次数
        Boolean existsMoreThan3Repeats = false;             // 是否存在3个相邻的重复字符, 起始默认为 false

        // 开始替换相邻的重复字符(里面包含递归)
        String finalStr = replaceMoreThan3RepeatedChars(charArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats);

        if(finalStr == null || finalStr.length() == 0){
            return "";
        } else {
            return finalStr;
        }

    }


    /**
     * 替换相邻的重复字符(里面包含递归)
     * @param charArr
     * @param repeatCount
     * @param indexSetToReplace
     * @param existsMoreThan3Repeats
     * @return
     */
    private String replaceMoreThan3RepeatedChars(char[] charArr,
                                                 int repeatCount,
                                                 Set<Integer> indexSetToReplace,
                                                 Boolean existsMoreThan3Repeats) {

        String resultStr = "";

        // 遍历字符数组, 将所有需要被替换的字符的下标做标记(放入 indexSetToReplace )
        for(int i = 0; i < charArr.length; i++){

            if(i == 0){ // 第一个字符不做任何处理

            } else {    // 从第二个字符开始处理
                if(charArr[i] == charArr[i-1]){             // 当前字符跟前一个字符相同
                    repeatCount++;                          // 重复次数 +1
                    if(repeatCount >= 3){                   // 重复次数达到3次或以上
                        // 将需要被替换的字符的下标保存到Set里
                        indexSetToReplace.add(i);
                        indexSetToReplace.add(i - 1);
                        indexSetToReplace.add(i - 2);
                        existsMoreThan3Repeats = true;      // 如果为 true , 则需要进行下一步递归
                    }
                } else {                                    // 当前字符跟前一个字符不同
                    repeatCount = 1;                        // 重置为1
                }
            }
        }

        // 新建 StringBuffer , 拼接被替换后的结果
        StringBuffer sb = new StringBuffer();
        for(int j = 0; j < charArr.length; j++){
            char currentChar = charArr[j];
            if(indexSetToReplace.contains(j)){
                if( j == 0 || charArr[j] != charArr[j - 1]){
                    if('a' != charArr[j]){
                        int ascii = (int)charArr[j];
                        char previousAsciiChar = (char)(ascii - 1);   // Ascii 码前一个字符
                        sb.append(previousAsciiChar);                 // 拼接被替换后的字符
                    }
                }
            } else {
                sb.append(currentChar);
            }
        }

        // 判断是否需要递归操作
        // 1.如果当前存在重复3次的情况, 则递归
        // 2.如果当前不存在任何重复3次的情况, 则无需递归
        if(existsMoreThan3Repeats){
            repeatCount = 1;                    // 递归前重置为 1
            indexSetToReplace.clear();          // 递归前清空 Set
            existsMoreThan3Repeats = false;     // 递归前设置为 false

            resultStr = sb.toString();          // 递归前将 StringBuffer 转化为 char[] 作为参数进行传递
            char[] resultCharArr = resultStr.toCharArray();

            // 递归调用
            resultStr = replaceMoreThan3RepeatedChars(resultCharArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats);

        } else {
            resultStr = sb.toString();
        }

        return resultStr;
    }

}
