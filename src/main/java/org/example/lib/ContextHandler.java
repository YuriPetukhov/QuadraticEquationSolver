package org.example.lib;

import lombok.extern.slf4j.Slf4j;
import org.example.solver.annotation.FilterOrder;
import org.example.solver.enums.ContextFilterType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
public abstract class ContextHandler<F> {

    private final List<ContextFilter<F>> contextFilters;

    protected ContextHandler(List<ContextFilter<F>> contextFilters) {
        this.contextFilters = contextFilters.stream()
                .sorted(Comparator.comparingInt(f -> f.getClass().getAnnotation(FilterOrder.class).value()))
                .collect(Collectors.toList());
    }

    public void handle(F context, ContextFilterType filterType) {
        for (ContextFilter<F> contextFilter : contextFilters) {

            if (contextFilter.getFilterType() == filterType && contextFilter.isAuthorized(context)) {
                log.info("Executing filter: {}", contextFilter.getClass().getSimpleName());
                contextFilter.apply(context);
            }
        }
    }
}
