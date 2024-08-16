package com.example.codingtesthaustin.controller;


import com.example.codingtesthaustin.service.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/coding-test")
public class CodingTestController
{


    @Autowired
    IOperationService operationService;


    /**
     * 删除相邻重复字符的接口
     * @param map
     * @return
     */
    @PostMapping("/delete-repeated-chars")
    public String deleteRepeatedChars( @RequestBody Map<String, String> map)
    {

        if(map == null){
            return "error: 参数对象不能为 null";
        }

        String inputStr = map.get("inputStr");

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


        // 开始删除相邻的重复字符(里面包含递归)
        String finalStr = operationService.deleteRepeatedChars(inputStr);

        return finalStr;

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
            return "error: 参数对象不能为 null";
        }

        String inputStr = map.get("inputStr");

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
            return "error, 请确保输入的字符串只包含 a-z 的小写字母";
        }

        // 字符串长度小于 3 则直接返回
        if(optional.get().length() < 3) {
            return optional.get();
        }


        // 开始替换相邻的重复字符(里面包含递归)
        String finalStr = operationService.replaceRepeatedChars(inputStr);

        return finalStr;

    }




}
