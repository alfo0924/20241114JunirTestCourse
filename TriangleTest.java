package demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Triangle類別的測試類別
 * 測試三角形類型判斷的各種情況
 */
class TriangleTest {

    /**
     * 測試無效三角形的情況
     * - 負數邊長
     * - 零邊長
     * - 兩邊之和小於等於第三邊
     */
    @Test
    void not_valid_triangle() {
        assertAll("not validate",
                // 測試負數邊長
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(-1, 2, 3)),
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(2, -2, 3)),
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(2, 2, -3)),

                // 測試零邊長
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(0, 2, 3)),

                // 測試兩邊之和小於等於第三邊的情況
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(1, 2, 3)),
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(1, 3, 2)),
                ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(3, 1, 2))
        );
    }

    /**
     * 測試等邊三角形
     * 所有邊長相等
     */
    @Test
    void testEquilateral() {
        assertAll("Equilateral",
                ()-> assertEquals("Equilateral", Triangle.getTriangleType(3, 3, 3)),
                ()-> assertEquals("Equilateral", Triangle.getTriangleType(2, 2, 2))
        );
    }

    /**
     * 測試等腰三角形
     * 任意兩邊相等
     */
    @Test
    void testIsosceles() {
        assertAll("Isosceles",
                // 測試第一和第二邊相等
                ()-> assertEquals("Isosceles", Triangle.getTriangleType(3, 3, 2)),
                // 測試第一和第三邊相等
                ()-> assertEquals("Isosceles", Triangle.getTriangleType(2, 3, 2)),
                // 測試第二和第三邊相等
                ()-> assertEquals("Isosceles", Triangle.getTriangleType(2, 3, 3))
        );
    }

    /**
     * 測試不等邊三角形
     * 三邊長度都不相等
     */
    @Test
    void testScalene() {
        assertAll("Scalene",
                ()-> assertEquals("Scalene", Triangle.getTriangleType(3, 4, 5)),
                ()-> assertEquals("Scalene", Triangle.getTriangleType(5, 7, 9))
        );
    }
}
