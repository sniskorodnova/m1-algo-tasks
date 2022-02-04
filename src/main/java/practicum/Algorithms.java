package practicum;

import java.util.*;

public class Algorithms {

    /**
     * Отсортируйте список, НЕ используя методы стандартной библиотеки (напр. Collections.sort).
     */
    public static List<Integer> sort(List<Integer> list) {
        List<Integer> sortedList = new ArrayList<>(list);
        int temp;
        for (int i = 1; i < sortedList.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (sortedList.get(i) < sortedList.get(j)) {
                    temp = sortedList.get(j);
                    sortedList.set(j, sortedList.get(i));
                    sortedList.set(i, temp);
                }
            }
        }
        return sortedList;
    }

    /**
     * Удалите дубликаты из списка.
     *
     * Усложнение: не используйте дополнительные структуры данных
     *  для хранения промежуточных значений.
     *  (списки, массивы, хэш-таблицы, множества и т.п.).
     * К списку-результату это не относится.
     */
    public static List<Integer> removeDuplicates(List<Integer> list) {
        List<Integer> newList = new ArrayList<>();
        boolean isDuplicate = false;
        if (list.isEmpty()) {
            return list;
        } else {
            newList.add(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                for (int j = 0; j < newList.size(); j++) {
                    if (newList.get(j) == list.get(i)) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    newList.add(list.get(i));
                }
                isDuplicate = false;
            }

            return newList;
        }
    }

    /**
     * Проверьте, является ли список "палиндромом".
     * Палиндром -- это список, который в обе стороны читается одинаково.
     * Например:
     *  палиндромы: [1 2 1], [3 2 1 2 3], [2 2 2], []
     *  не палиндромы: [1 2 3], [2 2 3], [3 2 1 3 2]
     *
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n)
     */
    public static boolean isPalindrome(List<Integer> list) {
        boolean isPalindrom = true;
        if (list.isEmpty()) {
            return isPalindrom;
        } else {
            for (int i = 0; i < list.size() / 2 + 1; i++) {
                if (list.get(i) != list.get(list.size() - (i + 1))) {
                    isPalindrom = false;
                    break;
                }
            }
        }
        return isPalindrom;
    }

    /**
     * Объедините два отсортированных списка в один отсортированный список.
     * Например:
     * [1 3 5] + [2 4 6] = [1 2 3 4 5 6]
     * [1 2 3] + [1 3 5] = [1 1 2 3 3 5]
     * [] + [1] = [1]
     * [7] + [1 4] = [1 4 7]
     *
     * Доп. условие: у алгоритма должна быть линейная сложность, O(n).
     */
    public static List<Integer> mergeSortedLists(List<Integer> a, List<Integer> b) {
        List<Integer> sortedList = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < a.size() || j < b.size()) {
            if (i == a.size()) {
                for (int k = j; k < b.size(); k++) {
                    sortedList.add(b.get(k));
                }
                break;
            } else if (j == b.size()) {
                for (int k = i; k < a.size(); k++) {
                    sortedList.add(a.get(k));
                }
                break;
            } else {
                if (a.get(i) <= b.get(j)) {
                    sortedList.add(a.get(i));
                    i++;
                } else {
                    sortedList.add(b.get(j));
                    j++;
                }
            }
        }
        return sortedList;
    }

    /**
     * Проверьте, что в массиве нет дубликатов.
     * Верните true, если дубликатов нет, иначе false.
     *
     * Усложнение: не используйте дополнительные структуры данных
     *  (списки, массивы, хэш-таблицы, множества и т.п.).
     */
    public static boolean containsEveryElementOnce(int[] array) {
        boolean isNoDuplicate = true;

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    isNoDuplicate = false;
                    break;
                }
            }
        }
        return isNoDuplicate;
    }

    /**
     * Определите, является ли один массив перестановкой другого.
     * Т.е. в массивах хранится один и тот же набор элементов, но (возможно) в разном порядке.
     *
     * Для решения нжуно использовать одну хэш-таблицу.
     *
     * Например:
     * [1 2 3] и [3 2 1] = true
     * [1 1 2] и [1 2 1] = true
     * [1 2 3] и [1 2 3] = true
     * [] и [] = true
     *
     * [1 2] и [1 1 2] = false, разный набор элементов
     */
    public static boolean isPermutation(int[] a, int[] b) {
        Map<Integer, Integer> mapElem = new HashMap<>();
        boolean isPermutation = true;

        for (int i = 0; i < a.length; i++) {
            if (mapElem.containsKey(a[i])) {
                int temp = mapElem.get(a[i]);
                temp++;
                mapElem.put(a[i], temp);
            } else {
                mapElem.put(a[i], 1);
            }
        }

        for (int i = 0; i < b.length; i++) {
            int count = 0;
            for (int j = 0; j < b.length; j++) {
                if (b[j] == b[i]) {
                    count++;
                }
            }
            if (!mapElem.containsKey(b[i])) {
                isPermutation = false;
                break;
            } else {
                if (mapElem.get(b[i]) != count) {
                    isPermutation = false;
                    break;
                }
            }
        }
        return isPermutation;
    }

    /**
     * Сложная задача.
     *
     * В памяти компьютера изображения (часто) хранятся в виде двумерного массива.
     * Напишите метод, который повернёт "изображение" на 90 градусов вправо.
     * "Изображение" в данном примере -- двумерный массив целых чисел.
     *
     * Например:
     * на входе:
     * [ [1 2]
     *   [3 4]
     *   [5 6] ]
     *
     * на выходе:
     * [ [5 3 1]
     *   [6 4 2] ]
     */
    public static int[][] rotateRight(int[][] image) {
        int[][] rotatedImage = new int[image[0].length][image.length];

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                rotatedImage[j][(image.length - 1) - i] = image[i][j];
            }
        }
        return rotatedImage;
    }
}
