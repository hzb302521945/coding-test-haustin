package com.example.codingtesthaustin.controller;


import com.example.codingtesthaustin.service.IOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/coding-test")
public class CodingTestController
{


    @Autowired
    IOperationService operationService;


    /**
     * Interface for deleting adjacent repeated characters
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

        // Start deleting adjacent repeated characters (including recursion)
        String finalStr = operationService.deleteRepeatedChars(inputStr);

        return finalStr;

    }


    /**
     * Interface for replacing adjacent repeated characters
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

        // Start replacing adjacent repeated characters (including recursion)
        String finalStr = operationService.replaceRepeatedChars(inputStr);

        return finalStr;

    }


}
