package com.example.codingtesthaustin.operation.impl;

import com.example.codingtesthaustin.operation.IOperation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation class of IOperation: operation class for replacing
 *
 * @author Haustin
 */
public class ReplaceOperation implements IOperation {


    /**
     * Specific string operation methods
     * @param inputStr
     * @return
     */
    @Override
    public String operate(String inputStr) {

        // JDK8 new feature: detecting null pointers exception
        Optional<String> optional = Optional.ofNullable(inputStr);
        if(optional.isEmpty()){
            return "Error: the inputStr must not be null";
        }

        // Determine if the string length is 0
        if(optional.get().length() == 0) {
            return "Error: the length of inputStr must not be zero";
        }

        // JDK8 new feature: Determine if the input string is all lowercase letters
        List<Character> charList = inputStr.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        Boolean allLowerCaseLetters = charList.stream().allMatch(c -> (int)c >= 97 && (int)c <= 122);
        if(!allLowerCaseLetters){
            return "Error: please ensure that the input string only contains lowercase letters a-z";
        }

        // If the string length is less than 3, return directly
        if(optional.get().length() < 3) {
            return optional.get();
        }


        char[] charArr = inputStr.toCharArray();            // Convert the input string to char []
        Set<Integer> indexSetToReplace = new HashSet<>();   // Used to store character indices that need to be deleted
        int repeatCount = 1;                                // Repeated times of character
        Boolean existsMoreThan3Repeats = false;             // Are there 3 adjacent repeated characters, false by default
        Boolean firstCall = true;                           // Is this the first time calling a recursive method (used to control the output content of the console)

        // Start replacing adjacent repeated characters (including recursion)
        String finalStr = replaceMoreThan3RepeatedChars(charArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats, firstCall);

        if(finalStr == null || finalStr.length() == 0){
            return "";
        } else {
            return finalStr;
        }

    }


    /**
     * Replacing adjacent repeated characters (including recursion)
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
                                                 Boolean firstCall) {

        String resultStr = "";

        // Starting index of repeated substring
        int repeatedStartIndex = 0;
        // End index of repeated substring
        int repeatedEndIndex = 0;
        // Used to store the "replaced by" informations for outputing to the console
        List<String> replacedByList = new ArrayList<>();


        // Traverse the array and mark the indices of the characters that need to be deleted((put them into indexSetToReplace)
        for(int i = 0; i < charArr.length; i++){

            if(i == 0){ // The first character, without any processing

            } else {    // Process starting from the second character

                if(charArr[i] == charArr[i-1]){             // The current character is the same as the previous character
                    repeatCount++;                          // Repeated times +1
                    repeatedEndIndex += 1;                  // End index of repeated substring +1

                    if(repeatCount >= 3){                   // If the repeated times is 3 or more

                        // Save the index of the character that needs to be replaced to the Set
                        for(int j = 0; j < repeatCount; j++){
                            indexSetToReplace.add(i - j);
                        }

                        existsMoreThan3Repeats = true;      // If true, the next step of recursion is required
                    }

                } else {  // The current character is different from the previous character

                    // The number of repetitions of the previous characters of the current character is greater than or equal to 3
                    if((repeatedEndIndex - repeatedStartIndex) >= 2){
                        StringBuffer repeatedSubString = new StringBuffer(", ");
                        for(int k = repeatedStartIndex; k <= repeatedEndIndex; k++){
                            repeatedSubString.append(charArr[k]);         // Append the previously repeated characters into a string
                        }

                        if('a' != charArr[i - 1]){
                            int ascii = (int)charArr[i - 1];              // Get the character at the previous index position of the array and convert it to ASCII code
                            char previousAsciiChar = (char)(ascii - 1);   // Get the revious Ascii code character
                            repeatedSubString.append(" is replaced by ").append(previousAsciiChar);
                        } else {
                            // If the repeated character is 'a', replace it with ""
                            repeatedSubString.append(" is replaced by \"\"");
                        }

                        replacedByList.add(repeatedSubString.toString());
                    }

                    repeatCount = 1;                        // Reset repeated times to 1
                    repeatedStartIndex = i;                 // Reset to current index
                    repeatedEndIndex = i;                   // Reset to current index
                }
            }
        }

        // Create a new StringBuffer to append the replaced results
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < charArr.length; i++){
            char currentChar = charArr[i];
            if(indexSetToReplace.contains(i)){
                if( i == 0 || charArr[i] != charArr[i - 1]){
                    if('a' != charArr[i]){
                        int ascii = (int)charArr[i];
                        char previousAsciiChar = (char)(ascii - 1);   // Get the revious Ascii code character
                        sb.append(previousAsciiChar);                 // Append the replaced character
                    }
                }
            } else {
                sb.append(currentChar);
            }
        }

        // Output the string to the console
        if(firstCall || existsMoreThan3Repeats){
            StringBuffer outputSb = new StringBuffer(sb);

            if(replacedByList.size() > 0){
                for (int i = 0; i < replacedByList.size(); i++) {
                    outputSb.append(replacedByList.get(i));  // 拼接 replaced by 信息
                }
            }

            System.out.println(new String(outputSb));

        }

        // Determine whether recursive operation is required
        // 1.If there is a current situation of repeating 3 times, then recursively
        // 2.If there is no current situation of repeating 3 times, there is no need for recursion
        if(existsMoreThan3Repeats){
            // Recursive parameter setting
            repeatCount = 1;                   // Reset to 1 before recursion
            indexSetToReplace.clear();         // Clear the Set before recursion
            existsMoreThan3Repeats = false;    // Set to false before recursion

            resultStr = sb.toString();         // Convert the JsonBuffer to char [] as a parameter before recursion and pass it as a parameter
            char[] resultCharArr = resultStr.toCharArray();

            // Recursive call
            resultStr = replaceMoreThan3RepeatedChars(resultCharArr, repeatCount, indexSetToReplace, existsMoreThan3Repeats, false);

        } else {
            resultStr = sb.toString();
        }

        return resultStr;
    }

}
