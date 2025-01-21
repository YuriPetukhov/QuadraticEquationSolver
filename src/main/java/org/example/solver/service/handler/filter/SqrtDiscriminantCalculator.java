package org.example.solver.service.handler.filter;

import org.example.lib.ContextFilter;
import org.example.solver.annotation.FilterOrder;
import org.example.solver.entity.QuadraticEquationContext;
import org.example.solver.enums.ContextFilterType;
import lombok.extern.slf4j.Slf4j;

/**
 * This filter calculates the square root of the discriminant of a quadratic equation.
 * The square root is stored in the context if the discriminant is positive.
 */
@FilterOrder(7) // Specifies the order in which this filter should be applied
@Slf4j
public class SqrtDiscriminantCalculator implements ContextFilter<QuadraticEquationContext> {

    /**
     * Checks if the filter can be applied.
     * The filter is applied when the discriminant is positive.
     *
     * @param entity The context (QuadraticEquationContext) to check.
     * @return true if the discriminant is positive, otherwise false.
     */
    @Override
    public boolean isAuthorized(QuadraticEquationContext entity) {
        boolean isDiscriminantPositive = entity.getDiscriminant() > 0;
        log.info("Checking if the discriminant is positive: {}", isDiscriminantPositive);
        return isDiscriminantPositive;
    }

    /**
     * Calculates the square root of the discriminant and stores it in the context.
     * The square root of the discriminant is used in further calculations for the roots.
     *
     * @param context The context (QuadraticEquationContext) in which to store the square root of the discriminant.
     */
    @Override
    public void apply(QuadraticEquationContext context) {
        // Log the action before calculating the square root
        log.info("Discriminant is positive, calculating square root.");

        // Calculate the square root of the discriminant
        double sqrtDiscriminant = Math.sqrt(context.getDiscriminant());

        // Store the square root of the discriminant in the context
        context.setSqrtDiscriminant(sqrtDiscriminant);

        // Log the calculated square root
        log.info("Calculated square root of discriminant: {}", sqrtDiscriminant);
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
