package org.example.solver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.solver.entity.QuadraticEquationContext;
import org.example.solver.service.handler.QuadraticEquationHandler;

/**
 * Implementation of the QuadraticEquationSolver interface.
 * Responsible for solving quadratic equations by delegating the processing
 * to a QuadraticEquationHandler.
 */
@Slf4j
@RequiredArgsConstructor
public class QuadraticEquationSolverImpl implements QuadraticEquationSolver {

    private final QuadraticEquationHandler handler;

    /**
     * Solves a quadratic equation of the form ax^2 + bx + c = 0.
     *
     * @param a coefficient of x^2
     * @param b coefficient of x
     * @param c constant term
     * @return an array containing the roots of the equation
     */
    @Override
    public double[] solve(double a, double b, double c) {
        log.info("Received coefficients: a={}, b={}, c={}", a, b, c);

        // Build the context object to encapsulate the coefficients
        QuadraticEquationContext context = QuadraticEquationContext.builder()
                .a(a)
                .b(b)
                .c(c)
                .build();
        log.debug("Constructed QuadraticEquationContext: {}", context);

        // Delegate the handling of the context to the handler
        double[] roots = handler.handleContext(context);
        log.info("Computed roots: {}", (Object) roots); // Cast to Object to avoid varargs confusion in logging

        return roots;
    }
}
