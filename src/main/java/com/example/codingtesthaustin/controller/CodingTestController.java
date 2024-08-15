package com.example.codingtesthaustin.controller;


import com.sun.source.tree.BreakTree;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 *
 * 
 * @author
 */
@RestController
@RequestMapping("/coding-test")
public class CodingTestController
{


    /**
     * 删除字符串的接口
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

        char[] charArr = inputStr.toCharArray();
        Set<Integer> indexSetToDelete = new HashSet<>();

        int repeatCount = 1; // 相邻字符串已重复次数
        Boolean existsMoreThan3Repeats = false; // 是否存在3个相邻的重复字符, 起始默认为 false


        char[] finalCharArr = deleteMoreThan3Chars(charArr, repeatCount, indexSetToDelete, existsMoreThan3Repeats);

        if(finalCharArr == null || finalCharArr.length == 0){
            return "该字符串已被全删干净";
        } else {
            return new String(finalCharArr);
        }

    }


    /**
     * 替换字符串的接口
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

        char[] charArr = inputStr.toCharArray();
        Set<Integer> indexSetToReplace = new HashSet<>();

        int repeatCount = 1; // 相邻字符串已重复次数
        Boolean existsMoreThan3Repeats = false; // 是否存在3个相邻的重复字符, 起始默认为 false

        String finalStr = replaceMoreThan3Chars(charArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats);

        if(finalStr == null || finalStr.length() == 0){
            return "该字符串已被全删干净";
        } else {
            return finalStr;
        }

    }



    private char[] deleteMoreThan3Chars(char[] charArr, int repeatCount,
                                             Set<Integer> indexSetToDelete, Boolean existsMoreThan3Repeats) {

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

        // 新建数组, 保存被删除后的结果
        char[] resultCharArr = new char[charArr.length - indexSetToDelete.size()];
        for(int j = 0, k = 0; j < charArr.length; j++){
            char currentChar = charArr[j];
            if(!indexSetToDelete.contains(j)){
                resultCharArr[k++] = currentChar;
            }
        }

        // 如果当前存在重复3次的情况, 则递归
        // 如果当前不存在任何重复3次的情况, 则无需递归
        if(existsMoreThan3Repeats){
            repeatCount = 1;
            indexSetToDelete.clear(); // 清空 Set
            existsMoreThan3Repeats = false;

            resultCharArr = deleteMoreThan3Chars(resultCharArr, repeatCount, indexSetToDelete, existsMoreThan3Repeats);

        }

        return resultCharArr;
    }



    private String replaceMoreThan3Chars(char[] charArr, int repeatCount,
                                        Set<Integer> indexSetToReplace, Boolean existsMoreThan3Repeats) {


        String resultStr = "";

        for(int i = 0; i < charArr.length; i++){

            if(i == 0){ // 第一个字符, 不做任何事情

            } else { //  从第二个字符开始处理

                if(charArr[i] == charArr[i-1]){ // 当前字符跟前一个字符相同
                    repeatCount++; // 重复次数 +1

                    if(repeatCount >= 3){ // 重复次数达到3次或以上

                        // 将需要被删除的下标保存到Set里
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

        // 新建数组, 保存被替换后的结果
        //char[] resultCharArr = new char[charArr.length - indexSetToReplace.size()];
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
                        sb.append(previousAsciiChar);
                    }
                }

            }
        }

        // 如果当前存在重复3次的情况, 则递归
        // 如果当前不存在任何重复3次的情况, 则无需递归
        if(existsMoreThan3Repeats){
            repeatCount = 1;
            indexSetToReplace.clear(); // 清空 Set
            existsMoreThan3Repeats = false;

            resultStr = sb.toString();
            char[] resultCharArr = resultStr.toCharArray();
            resultStr = replaceMoreThan3Chars(resultCharArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats);

        } else {
            resultStr = sb.toString();
        }

        return resultStr;
    }



}
