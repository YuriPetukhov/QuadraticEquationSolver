package org.example.solver.service.handler.filter;

import org.example.lib.ContextFilter;
import org.example.solver.annotation.FilterOrder;
import org.example.solver.enums.ContextFilterType;
import org.example.solver.entity.QuadraticEquationContext;
import org.example.lib.ValueValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * A value validator filter for validating coefficient 'a' in a quadratic equation.
 * This filter checks if the value of 'a' is not zero and is numeric.
 */
@FilterOrder(1) // Specifies the order in which this filter should be applied
@Slf4j
public class AValueValidator implements ContextFilter<QuadraticEquationContext> {

    private final ValueValidator valueValidator = new ValueValidator();

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
     * Applies the filter to the given context. This checks the value of coefficient 'a'
     * to ensure it is not zero and is a valid numeric value.
     *
     * @param context The context (QuadraticEquationContext) to validate.
     */
    @Override
    public void apply(QuadraticEquationContext context) {
        // Log the action for debugging purposes
        log.info("Applying AValueValidator to validate coefficient 'a'");

        // Validate 'a' - it should not be zero
        valueValidator.validateNotZero(context.getA(), "a");

        // Validate 'a' - it should be a numeric value
        valueValidator.validateNumeric(context.getA(), "a");
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
