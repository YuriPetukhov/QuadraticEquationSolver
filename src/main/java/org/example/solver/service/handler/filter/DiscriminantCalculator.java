package org.example.solver.service.handler.filter;

import org.example.lib.ContextFilter;
import org.example.solver.annotation.FilterOrder;
import org.example.solver.enums.ContextFilterType;
import org.example.solver.entity.QuadraticEquationContext;
import lombok.extern.slf4j.Slf4j;

/**
 * Discriminant calculator for a quadratic equation.
 * This filter calculates the discriminant (Δ) of the quadratic equation.
 * The discriminant is used to determine the number and type of the equation's roots.
 */
@FilterOrder(4) // Specifies the order in which this filter should be applied
@Slf4j
public class DiscriminantCalculator implements ContextFilter<QuadraticEquationContext> {

    /**
     * Checks if the filter is authorized to act on the given entity.
     *
     * @param entity The context (QuadraticEquationContext) to check.
     * @return true if authorized, otherwise false.
     */
    @Override
    public boolean isAuthorized(QuadraticEquationContext entity) {
        // In this case, the filter is always authorized to apply.
        return true;
    }

    /**
     * Applies the filter to the given context. This calculates the discriminant (Δ)
     * of the quadratic equation and sets it in the context.
     *
     * The discriminant is calculated as: Δ = b^2 - 4ac
     *
     * @param context The context (QuadraticEquationContext) to update with the discriminant.
     */
    @Override
    public void apply(QuadraticEquationContext context) {
        // Log the action for debugging purposes
        log.info("Calculating discriminant for the quadratic equation: Δ = b^2 - 4ac");

        // Calculate the discriminant (Δ)
        double discriminant = context.getB() * context.getB() - 4 * context.getA() * context.getC();

        // Set the calculated discriminant in the context
        context.setDiscriminant(discriminant);

        // Log the calculated discriminant
        log.info("Discriminant calculated: Δ = {}", discriminant);
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
