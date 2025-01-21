package org.example.solver;

import org.example.solver.service.QuadraticEquationSolver;
import org.example.solver.service.QuadraticEquationSolverImpl;
import org.example.solver.service.handler.QuadraticEquationContextHandler;
import org.example.solver.service.handler.QuadraticEquationHandler;
import org.example.solver.service.handler.filter.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticEquationSolverTest {

    QuadraticEquationSolver solver = new QuadraticEquationSolverImpl(new QuadraticEquationHandler(new QuadraticEquationContextHandler(List.of(
            new AValueValidator(),
            new BValueValidator(),
            new CValueValidator(),
            new DiscriminantCalculator(),
            new DiscriminantChecker(),
            new DiscriminantLessZeroChecker(),
            new QuadraticRootsCalculator(),
            new SqrtDiscriminantCalculator()
    ))));

//    Написать тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
    @Test
    void testNoRoots() {
        // Arrange: Создаем уравнение x^2 + 1 = 0 с коэффициентами a = 1, b = 0, c = 1.
        double a = 1;
        double b = 0;
        double c = 1;

        // Act: Вызываем метод solve с заданными коэффициентами.
        double[] roots = solver.solve(a, b, c);

        // Assert: Проверяем, что метод возвращает пустой массив, так как уравнение не имеет корней.
        assertArrayEquals(new double[0], roots, "Expected no roots for the equation x^2 + 1 = 0");
    }

//    Написать тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
    @Test
    void testTwoDistinctRoots() {
        // Arrange: Создаем уравнение x^2 = -1 с коэффициентами a = 1, b = 0, c = -1.
        double a = 1;
        double b = 0;
        double c = -1;

        // Act: Вызываем метод solve с заданными коэффициентами.
        double[] roots = solver.solve(a, b, c);

        // Assert: Проверяем, что метод возвращает массив с двумя корнями: 1 и -1.
        assertArrayEquals(new double[]{1, -1}, roots, "Expected roots are 1 and -1");
    }

//    Написать тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1).
    @Test
    void testSingleRootMultiplicityTwo() {
        // Arrange: Создаем уравнение x^2 + 2x + 1 = 0 с коэффициентами a = 1, b = 2, c = 1.
        double a = 1;
        double b = 2;
        double c = 1;

        // Act: Вызываем метод solve с заданными коэффициентами.
        double[] roots = solver.solve(a, b, c);

        // Assert: Проверяем, что метод возвращает массив с одним корнем кратности 2: -1.
        assertArrayEquals(new double[]{-1}, roots, "Expected a single root of multiplicity 2: -1");
    }
//Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
//Примечание. Учесть, что a имеет тип double и сравнивать с 0 через == нельзя.
    @Test
    void testCoefficientACannotBeZero() {
        // Arrange: Создаем уравнение 0x^2 + 2x + 1 = 0 с коэффициентом a = 0, b = 2, c = 1.
        double a = 0;
        double b = 2;
        double c = 1;

        // Act & Assert: Проверяем, что метод solve выбрасывает исключение IllegalArgumentException,
        // когда коэффициент a равен 0.
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(a, b, c),
                "Expected IllegalArgumentException for a = 0");
    }
//    С учетом того, что дискриминант тоже нельзя сравнивать с 0 через знак равенства,
//    подобрать такие коэффициенты квадратного уравнения для случая одного корня кратности два,
//    чтобы дискриминант был отличный от нуля, но меньше заданного эпсилон.
//    Эти коэффициенты должны заменить коэффициенты в тесте из п. 7.
    @Test
    void testSingleRootWithSmallNonZeroDiscriminant() {
        // Arrange: Создаем уравнение x^2 + 2.0000000001x + 1 = 0 с коэффициентами a = 1, b = 2.0000000001, c = 1.
        double a = 1;
        double b = 2.0000000001;
        double c = 1;

        // Act: Вызываем метод solve с заданными коэффициентами.
        double[] roots = solver.solve(a, b, c);

        // Assert: Проверяем, что метод возвращает массив с одним корнем: -1.00000000005 с точностью до 1e-6.
        assertArrayEquals(new double[]{-1.00000000005}, roots, 1e-6, "Expected a single root: -1.00000000005");
    }

//    Посмотреть какие еще значения могут принимать числа типа double,
//    кроме числовых и написать тест с их использованием на все коэффициенты.
//    solve должен выбрасывать исключение.

    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY})
    void testInvalidCoefficients(double invalidValue) {
        // Act & Assert: Проверяем, что метод solve выбрасывает исключение IllegalArgumentException,
        // когда коэффициенты a, b или c равны NaN, положительной или отрицательной бесконечности.
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(invalidValue, 2, 1),
                "Expected IllegalArgumentException for invalid coefficient a");
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(1, invalidValue, 1),
                "Expected IllegalArgumentException for invalid coefficient b");
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(1, 2, invalidValue),
                "Expected IllegalArgumentException for invalid coefficient c");
    }

    @Test
    void testInvalidCoefficientsA() {
        // Act & Assert: Проверяем, что метод solve выбрасывает исключение IllegalArgumentException,
        // когда коэффициент a равен NaN, положительной или отрицательной бесконечности.
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(Double.NaN, 2, 1),
                "Expected IllegalArgumentException for invalid coefficient a");
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(Double.POSITIVE_INFINITY, 2, 1),
                "Expected IllegalArgumentException for invalid coefficient a");
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(Double.NEGATIVE_INFINITY, 2, 1),
                "Expected IllegalArgumentException for invalid coefficient a");
    }

    @Test
    void testInvalidCoefficientsB() {
        // Act & Assert: Проверяем, что метод solve выбрасывает исключение IllegalArgumentException,
        // когда коэффициент b равен NaN, положительной или отрицательной бесконечности.
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(1, Double.NaN, 1),
                "Expected IllegalArgumentException for invalid coefficient b");
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(1, Double.POSITIVE_INFINITY, 1),
                "Expected IllegalArgumentException for invalid coefficient b");
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(1, Double.NEGATIVE_INFINITY, 1),
                "Expected IllegalArgumentException for invalid coefficient b");
    }

    @Test
    void testInvalidCoefficientsC() {
        // Act & Assert: Проверяем, что метод solve выбрасывает исключение IllegalArgumentException,
        // когда коэффициент c равен NaN, положительной или отрицательной бесконечности.
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(1, 2, Double.NaN),
                "Expected IllegalArgumentException for invalid coefficient c");
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(1, 2, Double.POSITIVE_INFINITY),
                "Expected IllegalArgumentException for invalid coefficient c");
        assertThrows(IllegalArgumentException.class,
                () -> solver.solve(1, 2, Double.NEGATIVE_INFINITY),
                "Expected IllegalArgumentException for invalid coefficient c");
    }
}