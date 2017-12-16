@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 || width <= 0) throw IllegalArgumentException()
    else return MatrixImpl<E>(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, val element: E) : Matrix<E> {
    val base = MutableList(height * width) { element }

    override fun get(row: Int, column: Int): E {
        if (height <= 0 || width <= 0) throw IllegalArgumentException()
        return base[width * row + column]
    }

    override fun get(cell: Cell): E {
        if (height <= 0 || width <= 0) throw IllegalArgumentException()
        return get(cell.row, cell.column)
    }

    override fun set(row: Int, column: Int, value: E) {
        if (height <= 0 || width <= 0) throw IllegalArgumentException()
        base[width * row + column] = value
    }

    override fun set(cell: Cell, value: E) {
        if (height <= 0 || width <= 0) throw IllegalArgumentException()
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatrixImpl<*>

        if (height != other.height) return false
        if (width != other.width) return false
        if (element != other.element) return false
        return true
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + (element?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String = "MatrixImpl(height=$height, width=$width, element=$element, base=$base)"

}

