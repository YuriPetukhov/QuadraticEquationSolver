package org.example.solver.service.handler.filter;

import org.example.lib.ContextFilter;
import org.example.solver.annotation.FilterOrder;
import org.example.solver.entity.QuadraticEquationContext;
import org.example.solver.enums.ContextFilterType;
import lombok.extern.slf4j.Slf4j;

/**
 * Discriminant checker for a quadratic equation.
 * This filter checks if the discriminant is zero and, if so, calculates the root.
 * In such cases, the equation has exactly one real root.
 */
@FilterOrder(6) // Specifies the order in which this filter should be applied
@Slf4j
public class DiscriminantChecker implements ContextFilter<QuadraticEquationContext> {

    // Small epsilon value for floating-point comparison
    private static final double EPSILON = 1e-8;

    /**
     * Checks if the discriminant is approximately zero.
     *
     * @param entity The context (QuadraticEquationContext) to check.
     * @return true if the discriminant is close to zero, otherwise false.
     */
    @Override
    public boolean isAuthorized(QuadraticEquationContext entity) {
        // Check if the discriminant is approximately zero (within EPSILON tolerance)
        boolean isDiscriminantZero = Math.abs(entity.getDiscriminant()) < EPSILON;
        log.info("Checking discriminant for zero: {}", isDiscriminantZero);
        return isDiscriminantZero;
    }

    /**
     * Applies the filter to the given context. If the discriminant is zero,
     * it calculates the single root of the quadratic equation and sets it in the context.
     *
     * @param context The context (QuadraticEquationContext) to update with the root.
     */
    @Override
    public void apply(QuadraticEquationContext context) {
        // Log the action before calculating the root
        log.info("Discriminant is zero, calculating the root for the quadratic equation.");

        // Calculate the single root of the equation: x = -b / (2a)
        double root = -context.getB() / (2 * context.getA());

        // Set the calculated root in the context
        context.setRoots(new double[]{root});

        // Log the calculated root
        log.info("Root calculated: {}", root);
    }

    /**
     * Returns the type of the filter.
     *
     * @return The ContextFilterType associated with this filter.
     */
    @Override
    public ContextFilterType getFilterType() {
        return ContextFilterType.QUADRATIC_EQUATION;
    }
}
