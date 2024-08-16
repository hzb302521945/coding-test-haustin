package com.example.codingtesthaustin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit testing class, please run the method with @Test annotation to test different scenarios
 *
 * Author: Haustin
 */
@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {

    @InjectMocks
    OperationServiceImpl operationServiceImpl;

    @BeforeEach
    void setUp() {
    }


    // The following methods starting with testDelete are used to test the operation of deleting repeated characters (DeleteOperation)

    @Test
    void testDelete1() {
        // The repeated character is located on the left and has only one place
        // (repeated character refers to 3 or more adjacent identical characters, the same below)
        assertEquals("bbceef", operationServiceImpl.deleteRepeatedChars("aaabbceef"));
    }

    @Test
    void testDelete2() {
        // The repeated character is located in the middle and has only one place
        assertEquals("aaccde", operationServiceImpl.deleteRepeatedChars("aabbbccde"));
    }

    @Test
    void testDelete3() {
        // The repeated character is located on the right side and has only one place
        assertEquals("aabbcc", operationServiceImpl.deleteRepeatedChars("aabbccddd"));
    }

    @Test
    void testDelete4() {
        // There are multiple repeated characters, and they are adjacent
        assertEquals("aaddee", operationServiceImpl.deleteRepeatedChars("aabbbcccddee"));
    }

    @Test
    void testDelete5() {
        // There are multiple repeated characters that are not adjacent
        assertEquals("aaccdd", operationServiceImpl.deleteRepeatedChars("aabbbccddeee"));
    }

    @Test
    void testDelete6() {
        // Recursive processing is required (the string returned after processing once can still be processed again)
        assertEquals("d", operationServiceImpl.deleteRepeatedChars("aabcccbbad"));
    }


    @Test
    void testDelete7() {
        // Repeated characters greater than 3
        assertEquals("aaccdd", operationServiceImpl.deleteRepeatedChars("aabbbbccdd"));
    }

    @Test
    void testDelete8() {
        // No repeated characters
        assertEquals("abbccdde", operationServiceImpl.deleteRepeatedChars("abbccdde"));
    }


    @Test
    void testDelete9() {
        // All characters will finally be deleted
        assertEquals("", operationServiceImpl.deleteRepeatedChars("aaabbbccc"));
    }



    // The following methods starting with testReplace are used to test the operation of replacing duplicate characters (replaceOperation)

    @Test
    void testReplace1() {
        // The repeated character is located on the left and has only one place
        // (repeated character refers to 3 or more adjacent identical characters, the same below)
        assertEquals("accde", operationServiceImpl.replaceRepeatedChars("bbbccde"));
    }

    @Test
    void testReplace2() {
        // The repeated character is located in the middle and has only one place
        assertEquals("abbde", operationServiceImpl.replaceRepeatedChars("abcccde"));
    }

    @Test
    void testReplace3() {
        // The repeated character is located on the right side and has only one place
        assertEquals("bbcc", operationServiceImpl.replaceRepeatedChars("bbcddd"));
    }

    @Test
    void testReplace4() {
        // There are multiple repeated characters, and they are adjacent
        assertEquals("abbce", operationServiceImpl.replaceRepeatedChars("abcccddde"));
    }

    @Test
    void testReplace5() {
        // There are multiple repeated characters that are not adjacent
        assertEquals("abbddf", operationServiceImpl.replaceRepeatedChars("abcccdeeef"));
    }

    @Test
    void testReplace6() {
        // Recursive processing is required (the string returned after processing once can still be processed again)
        assertEquals("d", operationServiceImpl.replaceRepeatedChars("abcccbad"));
    }

    @Test
    void testReplace7() {
        // Repeated characters greater than 3
        assertEquals("abbeef", operationServiceImpl.replaceRepeatedChars("abcccceef"));
    }

    @Test
    void testReplace8() {
        // No repeated characters
        assertEquals("abbccddeef", operationServiceImpl.replaceRepeatedChars("abbccddeef"));
    }

    @Test
    void testReplace9() {
        // The repeated character 'a' needs to be replaced with ''
        assertEquals("ccdd", operationServiceImpl.replaceRepeatedChars("abbbaccdd"));
    }


}