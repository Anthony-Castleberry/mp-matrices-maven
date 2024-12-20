package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Anthony Castleberry
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * default value; starts as null.
   */
  T basic = null;

  /**
   * 2d array that holds matrix.
   */
  T[][] contents;

  /**
   * height of 2d array.
   */
  int height;

  /**
   * width of 2d array.
   */
  int width;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    this.contents = (T[][]) new Object[height][];
    this.height = height;
    this.width = width;
    this.basic = def;

    this.initMatrix(this.contents);
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    return contents[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if (this.checkRowValidity(row) && this.checkColValidity(col)) {
      contents[row][col] = val;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if (this.checkRowValidity(row) || row == this.height) {
      this.expandRow(row);
      for (int i = 0; i < this.width; i++) {
        this.contents[row][i] = basic;
      } // for
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {

    if (vals.length != this.width) {
      throw new ArraySizeException();
    } // if

    if (this.checkRowValidity(row) || row == this.height) {
      this.expandRow(row);
      for (int i = 0; i < this.width; i++) {
        this.contents[row][i] = vals[i];
      } // for
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if (checkColValidity(col) || col == this.width) {
      this.expandCol(col);
      for (int i = 0; i < this.height; i++) {
        this.contents[i][col] = basic;
      } // for
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {

    if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if

    if (checkColValidity(col) || col == this.width) {
      this.expandCol(col);
      for (int i = 0; i < this.height; i++) {
        this.contents[i][col] = vals[i];
      } // for
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if (this.checkRowValidity(row)) {
      for (int i = row; i < this.height - 1; i++) {
        this.contents[i] = this.contents[i + 1];
      } // for
      this.height--;
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if (checkColValidity(col)) {
      width--;
      for (int i = 0; i < this.height; i++) {
        this.contents[i] = this.shiftRow(i, col);
      } // for
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {

    checkBounds(startRow, startCol, endRow, endCol);

    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        this.contents[i][j] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throws IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {

    checkBounds(startRow, startCol, endRow, endCol);

    int currentRow = startRow;
    int currentCol = startCol;

    while (currentRow < endRow && currentCol < endCol) {
      this.contents[currentRow][currentCol] = val;
      currentRow += deltaRow;
      currentCol += deltaCol;
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    return this;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      return this.equals((MatrixV0) other);
    } else {
      return false;
    } // if
  } // equals(Object)

  /**
   * Determine if two matrix's are equal.
   *
   * @param other
   *    Matrix to compare.
   *
   * @return true if the contents, width, and height are the same, false otherwise.
   */
  public boolean equals(MatrixV0 other) {
    if (this.width == other.width() && this.height == other.height()) {
      for (int i = 0; i < this.height; i++) {
        for (int j = 0; j < this.width; j++) {
          if (this.contents[i][j] != other.get(i, j)) {
            return false;
          } // if
        } // for
      } // for
      return true;
    } // if

    return this.hashCode() == other.hashCode();
  } // equals(MatrixV0)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()

  /**
   * Checks if a given row is in valid bounds of 0 inclusive to height exclusive.
   *
   * @param row
   *   row to be checked
   *
   * @return true if row is in bounds, false otherwise
   */
  public boolean checkRowValidity(int row) {
    return row < this.height && row >= 0;
  } // checkRowValidity(int)

  /**
   * Checks if a given column is in bounds of 0 inclusive width exclusive.
   *
   * @param col
   * @return true if column is in bounds, false otherwise
   */
  public boolean checkColValidity(int col) {
    return col < this.width && col >= 0;
  } // checkColValidity(int)

  /**
   * expands the matrix by making it one row taller leaving,
   * the row specified empty.
   *
   * @param row row to be inserted later
   */
  private void expandRow(int row) {
    this.height++;
    int offset = 0;
    T[][] newContents = (T[][]) new Object[height][];
    this.initMatrix(newContents);
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        if (i == row) {
          newContents[i][j] = basic;
          offset = 1;
        } // if

        if (offset == 1 && i == 0) {
          // nothing
        } else {
          newContents[i][j] = this.contents[i - offset][j];
        } // if
      } // for
    } // for
    this.contents = newContents;
  } // expandRow(int)

    /**
   * expands the matrix by making it one col wider leaving,
   * the col specified empty.
   *
   * @param col Col to be inserted later
   */
  private void expandCol(int col) {
    this.width++;
    int offset = 0;
    for (int i = 0; i < this.height; i++) {
      T[] newRow = (T[]) new Object[width];
      for (int j = 0; j < this.width; j++) {
        if (j == col) {
          newRow[j] = null;
          offset = 1;
        } else {
          newRow[j] = this.contents[i][j - offset];
        } // if
      } // for
      offset = 0;
      this.contents[i] = newRow;
    } // for
  } // expandcol(int)

  /**
   * fills the given 2d array with all default values.
   *
   * @param matrix
   *    the 2d array to be filled.
   */
  private void initMatrix(T[][] matrix) {
    for (int i = 0; i < this.height; i++) {
      matrix[i] = (T[]) new Object[width];
      for (int j = 0; j < this.width; j++) {
        matrix[i][j] = this.basic;
      } // for
    } // for
  } // initMatrix(T[][])

  /**
   *
   * Takes a row of the matrix and returns it with one less element.
   *
   * @param row row to have element shifted one left from col onwards
   * @param col the index in row to leave out
   * @return
   *    a new array without the value in col
   */
  private T[] shiftRow(int row, int col) {
    T[] array = (T[]) new Object[width];
    int currentIndex = 0;
    for (int i = 0; i < this.width; i++, currentIndex++) {
      if (currentIndex == col) {
        currentIndex++;
      } // if
      array[i] = this.contents[row][currentIndex];
    } // for
    return array;
  } // shiftRow(int, int)

  /**
   * checks if a region is in bounds of the 2d matrix.
   *
   * @param startRow top bound
   * @param startCol left bound
   * @param endRow bottom bound
   * @param endCol right bound
   */
  private void checkBounds (int startRow, int startCol, int endRow, int endCol) throws IndexOutOfBoundsException{
    if (this.checkColValidity(startCol) && this.checkColValidity(endCol)) {
      // nothing
    } else if (endCol == this.width) {
      // nothing
    } else if (this.checkRowValidity(startRow) && this.checkRowValidity(endRow)) {
      // nothing
    } else if (endRow == this.height) {
      // nothing
    } else {
      throw new IndexOutOfBoundsException();
    } // if
  } // checkBounds(int, int, int, int)
} // class MatrixV0
