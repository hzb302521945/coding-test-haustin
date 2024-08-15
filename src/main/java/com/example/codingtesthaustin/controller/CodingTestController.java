package com.example.codingtesthaustin.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;



@RestController
@RequestMapping("/coding-test")
public class CodingTestController
{


    /**
     * 删除相邻重复字符的接口
     * @param map
     * @return
     */
    @PostMapping("/delete-repeated-chars")
    public String deleteRepeatedChars( @RequestBody Map<String, String> map)
    {

        if(map == null){
            return "error: 参数为空";
        }

        String inputStr = map.get("inputStr");

        if(inputStr == null || inputStr.length() == 0){
            return "error: 请输入inputStr";
        }

        char[] charArr = inputStr.toCharArray();            // 输入的字符串转化为 char[]
        Set<Integer> indexSetToDelete = new HashSet<>();    // 用于存储需要被删除的字符的下标
        int repeatCount = 1;                                // 相邻字符已重复次数
        Boolean existsMoreThan3Repeats = false;             // 是否存在3个相邻的重复字符, 起始默认为 false

        // 删除相邻的重复字符(里面有递归)
        char[] finalCharArr = deleteMoreThan3RepeatedChars(charArr, repeatCount, indexSetToDelete, existsMoreThan3Repeats);

        if(finalCharArr == null || finalCharArr.length == 0){
            return "该字符串已被全删干净";
        } else {
            return new String(finalCharArr);
        }

    }


    /**
     * 替换相邻重复字符的接口
     * @param map
     * @return
     */
    @PostMapping("/replace-repeated-chars")
    public String replaceRepeatedChars( @RequestBody Map<String, String> map)
    {

        if(map == null){
            return "error: 参数为空";
        }

        String inputStr = map.get("inputStr");

        if(inputStr == null || inputStr.length() == 0){
            return "error: 请输入inputStr";
        }

        char[] charArr = inputStr.toCharArray();            // 输入的字符串转化为 char[]
        Set<Integer> indexSetToReplace = new HashSet<>();   // 用于存储需要被删除的字符的下标
        int repeatCount = 1;                                // 相邻字符串已重复次数
        Boolean existsMoreThan3Repeats = false;             // 是否存在3个相邻的重复字符, 起始默认为 false

        // 替换相邻的重复字符(里面有递归)
        String finalStr = replaceMoreThan3RepeatedChars(charArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats);

        if(finalStr == null || finalStr.length() == 0){
            return "该字符串已被全删干净";
        } else {
            return finalStr;
        }

    }


    /**
     * 删除相邻的重复字符(里面有递归)
     * @param charArr
     * @param repeatCount
     * @param indexSetToDelete
     * @param existsMoreThan3Repeats
     * @return
     */
    private char[] deleteMoreThan3RepeatedChars(char[] charArr, int repeatCount,
                                             Set<Integer> indexSetToDelete, Boolean existsMoreThan3Repeats) {

        // 循环, 将需要被删除的字符的下标做标记
        for(int i = 0; i < charArr.length; i++){

            if(i == 0){ // 第一个字符, 不做任何事情

            } else { //  从第二个字符开始处理

                if(charArr[i] == charArr[i-1]){ // 当前字符跟前一个字符相同
                    repeatCount++; // 重复次数 +1

                    if(repeatCount >= 3){ // 重复次数达到3次或以上

                        // 将需要被删除的下标保存到Set里
                        indexSetToDelete.add(i);
                        indexSetToDelete.add(i - 1);
                        indexSetToDelete.add(i - 2);

                        existsMoreThan3Repeats = true;

                    }

                } else { // 当前字符跟前一个字符不同
                    repeatCount = 1; // 重置为1
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


        // 判断是否需要递归操作
        // 1.如果当前存在重复3次的情况, 则递归
        // 2.如果当前不存在任何重复3次的情况, 则无需递归
        if(existsMoreThan3Repeats){
            repeatCount = 1;                    // 递归前重置为 1
            indexSetToDelete.clear();           // 递归前清空 Set
            existsMoreThan3Repeats = false;     // 递归前设置为 false

            // 递归调用
            resultCharArr = deleteMoreThan3RepeatedChars(resultCharArr, repeatCount, indexSetToDelete, existsMoreThan3Repeats);

        }

        return resultCharArr;
    }


    /**
     * 替换相邻的重复字符(里面有递归)
     * @param charArr
     * @param repeatCount
     * @param indexSetToReplace
     * @param existsMoreThan3Repeats
     * @return
     */
    private String replaceMoreThan3RepeatedChars(char[] charArr, int repeatCount,
                                        Set<Integer> indexSetToReplace, Boolean existsMoreThan3Repeats) {

        String resultStr = "";

        // 循环, 将需要被替换的字符的下标做标记
        for(int i = 0; i < charArr.length; i++){

            if(i == 0){ // 第一个字符, 不做任何事情

            } else { //  从第二个字符开始处理

                if(charArr[i] == charArr[i-1]){ // 当前字符跟前一个字符相同
                    repeatCount++; // 重复次数 +1

                    if(repeatCount >= 3){ // 重复次数达到3次或以上

                        // 将需要被替换的字符的下标保存到Set里
                        indexSetToReplace.add(i);
                        indexSetToReplace.add(i - 1);
                        indexSetToReplace.add(i - 2);

                        existsMoreThan3Repeats = true;

                    }

                } else { // 当前字符跟前一个字符不同
                    repeatCount = 1; // 重置为1
                }

            }

        }


        // 新建 StringBuffer , 拼接被替换后的结果
        StringBuffer sb = new StringBuffer();
        for(int j = 0; j < charArr.length; j++){

            char currentChar = charArr[j];

            if(!indexSetToReplace.contains(j)){
                sb.append(currentChar);

            } else {
                int previousIndex = j - 1;
                if(previousIndex >= 0){
                    if(indexSetToReplace.contains(previousIndex) && charArr[j] == charArr[j-1]){
                        // 被标记为需要替换且当前字符跟前一个字符相同, 则跳过
                        continue;

                    } else{
                        int ascii = (int)charArr[j];
                        char previousAsciiChar = (char)(ascii - 1); // Ascii 码前一个字符
                        sb.append(previousAsciiChar); // 拼接被替换后的字符
                    }
                }

            }
        }

        // 判断是否需要递归操作
        // 1.如果当前存在重复3次的情况, 则递归
        // 2.如果当前不存在任何重复3次的情况, 则无需递归
        if(existsMoreThan3Repeats){
            repeatCount = 1;                    // 递归前重置为 1
            indexSetToReplace.clear();          // 递归前清空 Set
            existsMoreThan3Repeats = false;     // 递归前设置为 false

            resultStr = sb.toString();          // 递归将StringBuffer转化为 char[] 进行传参
            char[] resultCharArr = resultStr.toCharArray();

            // 递归调用
            resultStr = replaceMoreThan3RepeatedChars(resultCharArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats);

        } else {
            resultStr = sb.toString();
        }

        return resultStr;
    }



}
