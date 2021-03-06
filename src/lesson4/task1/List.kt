@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = Math.sqrt(v.map { it * it }.sum())

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = when (list.size) {
    0 -> 0.0
    else -> list.sum() / list.size
}


/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val q = mean(list)
    for (i in 0 until list.size)
        list[i] -= q
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    val q = a.size
    var sum = 0.0
    for (i in 0 until q)
        sum += a[i] * b[i]
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var sum = 0.0
    var w = 1.0
    val q = p.size
    for (i in 0 until q) {
        sum += p[i] * w
        w *= x
    }
    return sum
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var sum = 0.0
    val q = list.size
    for (i in 0 until q) {
        sum += list[i]
        list[i] = sum
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val q = mutableListOf<Int>()
    var w = n
    var e = 2
    while (w > 1) {
        if (w % e == 0) {
            q.add(e)
            w /= e
        } else e++
    }
    return q
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var t = n
    val q = mutableListOf<Int>()
    if (n == 0) q.add(0)
    while (t > 0) {
        val w = t % base
        q.add(0, w)
        t /= base
    }
    return q
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val q = convert(n, base)
    val w = mutableListOf<Char>()
    when {
        n == 0 -> return "0"
        else -> {
            for (i in q) {
                if (i <= 9) w.add('0' + i)
                else w.add('a' + i - 10)
            }
        }
    }
    return w.joinToString("")
}


/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var q = 0
    for (i in 0 until digits.size) {
        q = q * base + digits[i]
    }
    return q
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    val q = mutableListOf<Int>()
    for (element in str) {
        if (element in '0'..'9') q += element - '0'
        else q += element - 'a' + 10
    }
    var k = q[0]
    for (t in 1 until q.size) k = k * base + q[t]
    return k
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val units = listOf<String>("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val FromTenToNineteen = listOf<String>("десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать", "одна", "две")
    val Dozens = listOf<String>("двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят",
            "восемьдесят", "девяносто")
    val HundredsAndThousands = listOf<String>("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот",
            "восемьсот", "девятьсот", "тысяча", "тысяч", "тысячи")
    var q = n
    val word = mutableListOf<String>()
    if (q % 100 in 10..19) {
        word.add(FromTenToNineteen[q % 10])
        q /= 100
    } else {
        if (q % 10 != 0) {
            word.add(units[q % 10 - 1])                // ЕДИНИЦЫ
            q /= 10
        } else q /= 10
        q /= if (q > 0 && q % 10 != 0) {
            word.add(0, Dozens[q % 10 - 2])               // ДЕСЯТКИ
            10
        } else 10
    }
    q /= if (q > 0 && q % 10 != 0) {
        word.add(0, HundredsAndThousands[q % 10 - 1])     // CОТНИ
        10
    } else 10
    if (q > 0) {
        if (q % 100 in 10..19) {
            word.add(0, FromTenToNineteen[q % 10])             // ЕДИНИЦЫ ТЫСЯЧ
            word.add(1, HundredsAndThousands[10])
            q /= 100
        } else {
            when {
                q % 10 == 1 -> {
                    word.add(0, FromTenToNineteen[10])
                    word.add(1, HundredsAndThousands[9])
                    q /= 10
                }
                q % 10 == 2 -> {
                    word.add(0, FromTenToNineteen[11])
                    word.add(1, HundredsAndThousands[11])
                    q /= 10
                }
                q % 10 in 3..4 -> {
                    word.add(0, units[q % 10 - 1])
                    word.add(1, HundredsAndThousands[11])
                    q /= 10
                }
                q % 10 in 5..9 -> {
                    word.add(0, units[q % 10 - 1])
                    word.add(1, HundredsAndThousands[10])
                    q /= 10
                }
                q % 10 == 0 -> q /= 10
            }
            if (q > 0 && q % 10 != 0) {
                word.add(0, Dozens[q % 10 - 2])                 // ДЕСЯТКИ ТЫСЯЧ
                if ("тысяч" in word || "тысяча" in word || "тысячи" in word) q /= 10
                else {
                    word.add(1, HundredsAndThousands[10])
                    q /= 10
                }
            } else q /= 10
        }
    }
    if (q > 0 && q % 10 != 0) {
        word.add(0, HundredsAndThousands[q % 10 - 1])             // CОТНИ ТЫСЯЧ
        if ("тысяч" in word || "тысяча" in word || "тысячи" in word)
            return word.joinToString(" ")
        else {
            word.add(1, HundredsAndThousands[10])
            q /= 10
        }
    }
    return word.joinToString(" ")
}


