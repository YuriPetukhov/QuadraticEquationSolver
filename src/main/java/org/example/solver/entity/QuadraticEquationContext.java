package org.example.solver.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuadraticEquationContext {
    private double a, b, c;
    private double discriminant;
    private double[] roots;
    private double sqrtDiscriminant;
}
