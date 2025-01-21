package org.example.solver.service.handler.filter;

import org.example.lib.ContextFilter;
import org.example.solver.annotation.FilterOrder;
import org.example.solver.entity.QuadraticEquationContext;
import org.example.solver.enums.ContextFilterType;
import lombok.extern.slf4j.Slf4j;

/**
 * This filter calculates the roots of the quadratic equation if the discriminant is positive.
 * If the discriminant is positive, it computes two real roots based on the quadratic formula.
 */
@FilterOrder(8) // Specifies the order in which this filter should be applied
@Slf4j
public class QuadraticRootsCalculator implements ContextFilter<QuadraticEquationContext> {

    private static final double EPSILON = 1e-8;

    /**
     * Checks if the filter can be applied.
     * The filter is applied when the discriminant is positive and significantly greater than zero.
     *
     * @param entity The context (QuadraticEquationContext) to check.
     * @return true if the discriminant is positive and sufficiently large, otherwise false.
     */
    @Override
    public boolean isAuthorized(QuadraticEquationContext entity) {
        boolean isDiscriminantPositive = entity.getDiscriminant() > 0 && Math.abs(entity.getDiscriminant()) > EPSILON;
        log.info("Checking if the discriminant is positive: {}", isDiscriminantPositive);
        return isDiscriminantPositive;
    }

    /**
     * Calculates the two real roots of the quadratic equation using the quadratic formula.
     * The roots are stored in the context.
     *
     * @param context The context (QuadraticEquationContext) in which to store the calculated roots.
     */
    @Override
    public void apply(QuadraticEquationContext context) {
        // Log the action before calculating roots
        log.info("Discriminant is positive, calculating two real roots.");

        // Calculate the square root of the discriminant
        double sqrtDiscriminant = Math.sqrt(context.getDiscriminant());

        // Calculate the two roots using the quadratic formula
        double root1 = (-context.getB() + sqrtDiscriminant) / (2 * context.getA());
        double root2 = (-context.getB() - sqrtDiscriminant) / (2 * context.getA());

        // Set the roots in the context
        context.setRoots(new double[]{root1, root2});

        // Log the calculated roots
        log.info("Calculated roots: root1 = {}, root2 = {}", root1, root2);
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
