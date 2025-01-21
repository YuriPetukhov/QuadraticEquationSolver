package org.example.solver.service.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.solver.entity.QuadraticEquationContext;
import org.example.solver.enums.ContextFilterType;

/**
 * Handler for processing quadratic equation contexts.
 * Delegates filtering and validation to a QuadraticEquationContextHandler
 * and returns the computed roots.
 */
@Slf4j
@RequiredArgsConstructor
public class QuadraticEquationHandler {

    private final QuadraticEquationContextHandler contextHandler;

    /**
     * Handles the given quadratic equation context by applying the necessary filters and validations.
     *
     * @param context the quadratic equation context containing coefficients and placeholders for roots
     * @return an array of computed roots of the quadratic equation
     */
    public double[] handleContext(QuadraticEquationContext context) {
        log.info("Handling quadratic equation context: {}", context);

        // Delegate to the context handler to process the filters and validations
        contextHandler.handle(context, ContextFilterType.QUADRATIC_EQUATION);

        // Retrieve and return the computed roots
        double[] roots = context.getRoots();
        log.info("Computed roots for context: {}", (Object) roots); // Cast to Object to handle arrays in logging
        return roots;
    }
}
