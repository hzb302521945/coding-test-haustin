package com.example.codingtesthaustin.operation.impl;

import com.example.codingtesthaustin.operation.IOperation;

import java.util.*;
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
        Boolean firstInvoke = true;                         // 是否为首次调用递归方法(用于控制控制台的输出内容)

        // 开始替换相邻的重复字符(里面包含递归)
        String finalStr = replaceMoreThan3RepeatedChars(charArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats, firstInvoke);

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
                                                 Boolean existsMoreThan3Repeats,
                                                 Boolean firstInvoke) {

        String resultStr = "";

        // 重复子字符串的开始下标
        int repeatedStartIndex = 0;
        // 重复子字符串的结束下标
        int repeatedEndIndex = 0;
        // 用于存储被输出到控制台的 replaced by 信息
        List<String> replacedByList = new ArrayList<>();


        // 遍历字符数组, 将所有需要被替换的字符的下标做标记(放入 indexSetToReplace )
        for(int i = 0; i < charArr.length; i++){

            if(i == 0){ // 第一个字符不做任何处理

            } else {    // 从第二个字符开始处理

                if(charArr[i] == charArr[i-1]){             // 当前字符跟前一个字符相同
                    repeatCount++;                          // 重复次数 +1
                    repeatedEndIndex += 1;

                    if(repeatCount >= 3){                   // 重复次数达到3次或以上

                        // 将需要被替换的字符的下标保存到Set里
                        for(int j = 0; j < repeatCount; j++){
                            indexSetToReplace.add(i - j);
                        }

                        existsMoreThan3Repeats = true;      // 如果为 true , 则需要进行下一步递归
                    }

                } else {                                    // 当前字符跟前一个字符不同

                    // 当前字符的前面几个字符重复次数大于等于 3
                    if((repeatedEndIndex - repeatedStartIndex) >= 2){
                        StringBuffer repeatedSubString = new StringBuffer(", ");
                        for(int k = repeatedStartIndex; k <= repeatedEndIndex; k++){
                            repeatedSubString.append(charArr[k]);     // 将前面几个重复的字符拼接成字符串
                        }

                        if('a' != charArr[i - 1]){
                            int ascii = (int)charArr[i - 1];              // 获取数组的前一个下标位置的字符, 转化成 ascii 码
                            char previousAsciiChar = (char)(ascii - 1);   // Ascii 码前一个字符
                            repeatedSubString.append(" is replaced by ").append(previousAsciiChar);
                        } else {
                            // 如果重复的字符是 a 则替换为 ""
                            repeatedSubString.append(" is replaced by \"\"");
                        }

                        replacedByList.add(repeatedSubString.toString());
                    }

                    repeatCount = 1;                        // 重置为 1
                    repeatedStartIndex = i;                 // 重置为当前下标
                    repeatedEndIndex = i;                   // 重置为当前下标
                }
            }
        }

        // 新建 StringBuffer , 拼接被替换后的结果
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < charArr.length; i++){
            char currentChar = charArr[i];
            if(indexSetToReplace.contains(i)){
                if( i == 0 || charArr[i] != charArr[i - 1]){
                    if('a' != charArr[i]){
                        int ascii = (int)charArr[i];
                        char previousAsciiChar = (char)(ascii - 1);   // Ascii 码前一个字符
                        sb.append(previousAsciiChar);                 // 拼接被替换后的字符
                    }
                }
            } else {
                sb.append(currentChar);
            }
        }

        // 将本次次操作的结果输出到控制台
        if(firstInvoke || existsMoreThan3Repeats){
            StringBuffer outputSb = new StringBuffer(sb);

            if(replacedByList.size() > 0){
                for (int i = 0; i < replacedByList.size(); i++) {
                    outputSb.append(replacedByList.get(i));  // 拼接 replaced by 信息
                }
            }

            System.out.println(new String(outputSb));

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
            resultStr = replaceMoreThan3RepeatedChars(resultCharArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats, false);

        } else {
            resultStr = sb.toString();
        }

        return resultStr;
    }

}
