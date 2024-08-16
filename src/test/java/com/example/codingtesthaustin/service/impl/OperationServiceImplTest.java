package com.example.codingtesthaustin.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 单元测试类, 测试不同的场景请运行带 @Test 注解的方法
 */
@ExtendWith(MockitoExtension.class)
class OperationServiceImplTest {

    @InjectMocks
    OperationServiceImpl operationServiceImpl;

    @BeforeEach
    void setUp() {
    }


    // 如下以 testDelete 开头的方法用于测试删除重复字符的操作( DeleteOperation )

    @Test
    void testDelete1() { // 重复字符位于左边, 且只有一处地方 (重复字符指3个或3个以上的相邻的相同字符, 下同)
        assertEquals("bbceef", operationServiceImpl.deleteRepeatedChars("aaabbceef"));
    }

    @Test
    void testDelete2() { // 重复字符位于中间, 且只有一处地方
        assertEquals("aaccde", operationServiceImpl.deleteRepeatedChars("aabbbccde"));
    }

    @Test
    void testDelete3() { // 重复字符位于右边, 且只有一处地方
        assertEquals("aabbcc", operationServiceImpl.deleteRepeatedChars("aabbccddd"));
    }

    @Test
    void testDelete4() { // 重复字符有多处, 且相邻
        assertEquals("aaddee", operationServiceImpl.deleteRepeatedChars("aabbbcccddee"));
    }

    @Test
    void testDelete5() { // 重复字符有多处, 且不相邻
        assertEquals("aaccdd", operationServiceImpl.deleteRepeatedChars("aabbbccddeee"));
    }

    @Test
    void testDelete6() { // 需要递归处理(处理一次后返回的字符串仍可被再次处理)
        assertEquals("d", operationServiceImpl.deleteRepeatedChars("aabcccbbad"));
    }


    @Test
    void testDelete7() { // 重复字符大于3个
        assertEquals("aaccdd", operationServiceImpl.deleteRepeatedChars("aabbbbccdd"));
    }

    @Test
    void testDelete8() { // 无任何重复字符
        assertEquals("abbccdde", operationServiceImpl.deleteRepeatedChars("abbccdde"));
    }


    @Test
    void testDelete9() { // 所有字符全被删除
        assertEquals("", operationServiceImpl.deleteRepeatedChars("aaabbbccc"));
    }



    // 如下以 testReplace 开头的方法用于测试替换重复字符的操作( ReplaceOperation )

    @Test
    void testReplace1() { // 重复字符位于左边, 且只有一处地方 (重复字符指3个或3个以上的相邻的相同字符, 下同)
        assertEquals("accde", operationServiceImpl.replaceRepeatedChars("bbbccde"));
    }

    @Test
    void testReplace2() { // 重复字符位于中间, 且只有一处地方
        assertEquals("abbde", operationServiceImpl.replaceRepeatedChars("abcccde"));
    }

    @Test
    void testReplace3() { // 重复字符位于右边, 且只有一处地方
        assertEquals("bbcc", operationServiceImpl.replaceRepeatedChars("bbcddd"));
    }

    @Test
    void testReplace4() { // 重复字符有多处, 且相邻
        assertEquals("abbce", operationServiceImpl.replaceRepeatedChars("abcccddde"));
    }

    @Test
    void testReplace5() { // 重复字符有多处, 且不相邻
        assertEquals("abbddf", operationServiceImpl.replaceRepeatedChars("abcccdeeef"));
    }

    @Test
    void testReplace6() { // 需要递归处理(处理一次后返回的字符串仍可被再次处理)
        assertEquals("d", operationServiceImpl.replaceRepeatedChars("abcccbad"));
    }

    @Test
    void testReplace7() { // 重复字符大于3个
        assertEquals("abbeef", operationServiceImpl.replaceRepeatedChars("abcccceef"));
    }

    @Test
    void testReplace8() { // 无任何重复字符
        assertEquals("abbccddeef", operationServiceImpl.replaceRepeatedChars("abbccddeef"));
    }

    @Test
    void testReplace9() { // 重复字符为 'a', 'a' 需要被替换为 ""
        assertEquals("ccdd", operationServiceImpl.replaceRepeatedChars("abbbaccdd"));
    }


}