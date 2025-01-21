package org.example.solver.service.handler.filter;

import org.example.lib.ContextFilter;
import org.example.solver.annotation.FilterOrder;
import org.example.solver.entity.QuadraticEquationContext;
import org.example.solver.enums.ContextFilterType;
import lombok.extern.slf4j.Slf4j;

/**
 * Discriminant less than zero checker for a quadratic equation.
 * This filter checks if the discriminant is negative and, if so, sets no real roots.
 * If the discriminant is less than zero, the equation has no real solutions.
 */
@FilterOrder(5) // Specifies the order in which this filter should be applied
@Slf4j
public class DiscriminantLessZeroChecker implements ContextFilter<QuadraticEquationContext> {

    /**
     * Checks if the discriminant is less than zero.
     *
     * @param entity The context (QuadraticEquationContext) to check.
     * @return true if the discriminant is less than zero, otherwise false.
     */
    @Override
    public boolean isAuthorized(QuadraticEquationContext entity) {
        boolean isDiscriminantNegative = entity.getDiscriminant() < 0;
        log.info("Checking if discriminant is less than zero: {}", isDiscriminantNegative);
        return isDiscriminantNegative;
    }

    /**
     * Applies the filter to the given context. If the discriminant is negative,
     * it sets no real roots (an empty array) in the context.
     *
     * @param context The context (QuadraticEquationContext) to update with no roots.
     */
    @Override
    public void apply(QuadraticEquationContext context) {
        // Log the action before applying the filter
        log.info("Discriminant is negative, setting no real roots.");

        // Set the roots to an empty array, indicating no real roots
        context.setRoots(new double[0]);

        // Log that no real roots were found
        log.info("No real roots available.");
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
