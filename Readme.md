# BinaraySearch
二分搜尋測試案例分析與實作：

### 程式碼分析

```java
package demo;

/**
 * 二分搜尋演算法實作
 * 包含搜尋方法和結果類別
 */
public class BinarySearch {
    public static class Result {
        public boolean Found;
        public int index;

        public Result(boolean found, int index) {
            this.Found = found;
            this.index = index;
        }
    }

    public Result search(int key, int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == key) {
                return new Result(true, mid);
            }

            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return new Result(false, -1);
    }
}
```

## 2. 測試案例設計方法

### 2.1 等價分割法分析

將測試案例分為以下等價類別：

1. **輸入陣列大小**
   - 空陣列（無效等價類）
   - 單一元素陣列（有效等價類）
   - 多個元素陣列（有效等價類）

2. **搜尋目標值位置**
   - 目標值在陣列開頭（有效等價類）
   - 目標值在陣列中間（有效等價類）
   - 目標值在陣列結尾（有效等價類）
   - 目標值不在陣列中（無效等價類）

3. **目標值特性**
   - 正數（有效等價類）
   - 負數（有效等價類）
   - 零（有效等價類）
   - 重複值（有效等價類）

## 3. 完整測試程式碼

```java
package demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BinarySearch 類別的測試類別
 * 使用等價分割法設計測試案例
 */
class BinarySearchTest {
    private final BinarySearch binarySearch = new BinarySearch();

    /**
     * 等價類別1：輸入陣列的大小
     * - 空陣列 (無效等價類)
     * - 單一元素陣列 (有效等價類)
     * - 多個元素陣列 (有效等價類)
     */
    @Test
    void testArraySize() {
        // 1.1 空陣列
        int[] emptyArray = {};
        BinarySearch.Result emptyResult = binarySearch.search(1, emptyArray);
        assertFalse(emptyResult.Found);
        assertEquals(-1, emptyResult.index);

        // 1.2 單一元素陣列
        int[] singleArray = {5};
        BinarySearch.Result singleResult = binarySearch.search(5, singleArray);
        assertTrue(singleResult.Found);
        assertEquals(0, singleResult.index);

        // 1.3 多個元素陣列
        int[] multiArray = {1, 2, 3, 4, 5};
        BinarySearch.Result multiResult = binarySearch.search(3, multiArray);
        assertTrue(multiResult.Found);
        assertEquals(2, multiResult.index);
    }

    /**
     * 等價類別2：搜尋目標值的位置
     * - 目標值在陣列開頭 (有效等價類)
     * - 目標值在陣列中間 (有效等價類)
     * - 目標值在陣列結尾 (有效等價類)
     * - 目標值不在陣列中 (無效等價類)
     */
    @Test
    void testTargetPosition() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // 2.1 目標值在開頭
        BinarySearch.Result startResult = binarySearch.search(1, array);
        assertTrue(startResult.Found);
        assertEquals(0, startResult.index);

        // 2.2 目標值在中間
        BinarySearch.Result middleResult = binarySearch.search(5, array);
        assertTrue(middleResult.Found);
        assertEquals(4, middleResult.index);

        // 2.3 目標值在結尾
        BinarySearch.Result endResult = binarySearch.search(9, array);
        assertTrue(endResult.Found);
        assertEquals(8, endResult.index);

        // 2.4 目標值不在陣列中
        BinarySearch.Result notFoundResult = binarySearch.search(10, array);
        assertFalse(notFoundResult.Found);
        assertEquals(-1, notFoundResult.index);
    }

    /**
     * 等價類別3：目標值的特性
     * - 正數 (有效等價類)
     * - 負數 (有效等價類)
     * - 零 (有效等價類)
     * - 重複值 (有效等價類)
     */
    @Test
    void testTargetCharacteristics() {
        // 3.1 正數搜尋
        int[] positiveArray = {1, 2, 3, 4, 5};
        BinarySearch.Result positiveResult = binarySearch.search(3, positiveArray);
        assertTrue(positiveResult.Found);
        assertEquals(2, positiveResult.index);

        // 3.2 負數搜尋
        int[] negativeArray = {-5, -4, -3, -2, -1};
        BinarySearch.Result negativeResult = binarySearch.search(-3, negativeArray);
        assertTrue(negativeResult.Found);
        assertEquals(2, negativeResult.index);

        // 3.3 零值搜尋
        int[] zeroArray = {-2, -1, 0, 1, 2};
        BinarySearch.Result zeroResult = binarySearch.search(0, zeroArray);
        assertTrue(zeroResult.Found);
        assertEquals(2, zeroResult.index);

        // 3.4 重複值搜尋
        int[] duplicateArray = {1, 2, 2, 2, 3};
        BinarySearch.Result duplicateResult = binarySearch.search(2, duplicateArray);
        assertTrue(duplicateResult.Found);
        assertTrue(duplicateResult.index >= 1 && duplicateResult.index <= 3);
    }
}
```

## 4. 測試案例說明

每個測試方法都針對特定的等價類別進行測試：

1. `testArraySize()`：測試不同大小的輸入陣列
2. `testTargetPosition()`：測試目標值在陣列中的不同位置
3. `testTargetCharacteristics()`：測試不同特性的目標值

每個測試案例都使用斷言（assertions）來驗證：
- `Found` 布林值是否正確
- `index` 值是否符合預期

這種測試設計確保了：
- 完整的測試覆蓋率
- 邊界條件的處理
- 特殊情況的處理
- 各種輸入值的正確性



# Triangle

## 整體結構
這是一個使用 JUnit 5 框架編寫的測試類別，用於測試 Triangle 類別中的 `getTriangleType` 方法。測試類別採用了分類測試的方式，將不同類型的三角形測試案例分開處理。

## 測試方法分析

### 1. 無效三角形測試 (not_valid_triangle)
```java
@Test
void not_valid_triangle() {
    assertAll("not validate",
            // 測試負數邊長
            ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(-1, 2, 3)),
            ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(2, -2, 3)),
            ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(2, 2, -3)),
            
            // 測試零邊長
            ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(0, 2, 3)),
            
            // 測試兩邊之和小於等於第三邊
            ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(1, 2, 3)),
            ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(1, 3, 2)),
            ()-> assertEquals("Not a valid triangle", Triangle.getTriangleType(3, 1, 2))
    );
}
```
- 測試無效三角形的三種情況：
  - 負數邊長
  - 零邊長
  - 不符合三角形邊長規則（兩邊之和小於等於第三邊）

### 2. 等邊三角形測試 (testEquilateral)
```java
@Test
void testEquilateral() {
    assertAll("Equilateral",
            ()-> assertEquals("Equilateral", Triangle.getTriangleType(3, 3, 3)),
            ()-> assertEquals("Equilateral", Triangle.getTriangleType(2, 2, 2))
    );
}
```
- 測試三邊相等的情況
- 使用不同的邊長值進行測試

### 3. 等腰三角形測試 (testIsosceles)
```java
@Test
void testIsosceles() {
    assertAll("Isosceles",
            // 測試三種不同的等腰情況
            ()-> assertEquals("Isosceles", Triangle.getTriangleType(3, 3, 2)),
            ()-> assertEquals("Isosceles", Triangle.getTriangleType(2, 3, 2)),
            ()-> assertEquals("Isosceles", Triangle.getTriangleType(2, 3, 3))
    );
}
```
- 測試三種等腰三角形的情況：
  - 第一邊等於第二邊
  - 第一邊等於第三邊
  - 第二邊等於第三邊

### 4. 不等邊三角形測試 (testScalene)
```java
@Test
void testScalene() {
    assertAll("Scalene",
            ()-> assertEquals("Scalene", Triangle.getTriangleType(3, 4, 5)),
            ()-> assertEquals("Scalene", Triangle.getTriangleType(5, 7, 9))
    );
}
```
- 測試三邊皆不相等的情況
- 使用兩組不同的測試數據

## 測試技術特點
1. **使用 @Test 註解**：標示測試方法
2. **使用 assertAll**：將相關的測試案例組合在一起，即使其中一個失敗，其他的也會繼續執行
3. **Lambda 表達式**：使用 `()->` 語法來編寫測試案例
4. **詳細的註解**：每個測試方法都有清楚的註解說明測試目的
5. **測試案例分類**：依據三角形類型將測試分為不同方法
6. **邊界值測試**：包含了零值、負數等邊界情況的測試

## 測試覆蓋率
根據圖片顯示，這些測試案例達到：
- 類別覆蓋率：100%
- 方法覆蓋率：100%
- 行覆蓋率：85%
- 分支覆蓋率：75%


