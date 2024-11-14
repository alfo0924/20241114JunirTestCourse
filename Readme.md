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
