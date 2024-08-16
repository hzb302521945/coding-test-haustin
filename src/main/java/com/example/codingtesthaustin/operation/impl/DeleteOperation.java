package com.example.codingtesthaustin.operation.impl;

import com.example.codingtesthaustin.operation.IOperation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation class of IOperation: operation class for deleting
 *
 * @author Haustin
 */
public class DeleteOperation implements IOperation {

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
        Set<Integer> indexSetToDelete = new HashSet<>();    // Used to store character indices that need to be deleted
        int repeatCount = 1;                                // Repeated times of character
        Boolean existsMoreThan3Repeats = false;             // Are there 3 adjacent repeated characters, false by default
        Boolean firstCall = true;                           // Is this the first time calling a recursive method (used to control the output content of the console)

        // Start deleting adjacent repeated characters (including recursion)
        char[] finalCharArr = deleteMoreThan3RepeatedChars(charArr, repeatCount, indexSetToDelete, existsMoreThan3Repeats, firstCall);

        if(finalCharArr == null || finalCharArr.length == 0){
            return "";
        } else {
            return new String(finalCharArr);
        }

    }


    /**
     * Deleting adjacent repeated characters (including recursion)
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
                                                Boolean firstCall) {

        // Traverse the array and mark the indices of the characters that need to be deleted(put them into indexSetToDelete)
        for(int i = 0; i < charArr.length; i++){

            if(i == 0){ // The first character, without any processing

            } else {    // Process starting from the second character
                if(charArr[i] == charArr[i-1]){             // The current character is the same as the previous character
                    repeatCount++;                          // Repeated times +1
                    if(repeatCount >= 3){                   // If the repeated times is 3 or more

                        // Save the index that needs to be deleted to the Set
                        for(int j = 0; j < repeatCount; j++){
                            indexSetToDelete.add(i - j);
                        }

                        existsMoreThan3Repeats = true;      // If true, the next step of recursion is required
                    }
                } else {                                    // The current character is different from the previous character
                    repeatCount = 1;                        // Reset repeated times to 1
                }
            }
        }


        // Create a new array to store the deleted results
        char[] resultCharArr = new char[charArr.length - indexSetToDelete.size()];
        for(int j = 0, k = 0; j < charArr.length; j++){
            char currentChar = charArr[j];
            if(!indexSetToDelete.contains(j)){
                resultCharArr[k++] = currentChar;
            }
        }

        // Output the string to the console
        if(firstCall || existsMoreThan3Repeats){
            if(resultCharArr == null || resultCharArr.length == 0){
                System.out.println("");
            } else {
                System.out.println(new String(resultCharArr));
            }
        }

        // Determine whether recursive operation is required
        // 1.If there is a current situation of repeating 3 times, then recursively
        // 2.If there is no current situation of repeating 3 times, there is no need for recursion
        if(existsMoreThan3Repeats){
            // Recursive parameter setting
            repeatCount = 1;                    // Reset to 1 before recursion
            indexSetToDelete.clear();           // Clear the Set before recursion
            existsMoreThan3Repeats = false;     // Set to false before recursion

            // Recursive call
            resultCharArr = deleteMoreThan3RepeatedChars(resultCharArr, repeatCount, indexSetToDelete, existsMoreThan3Repeats, false);

        }

        return resultCharArr;
    }

}
