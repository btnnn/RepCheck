package com.brandtnewtonsoftware.rep_check.models.calculations.formulas;

/**
 * Wathen
 * Created by Brandt on 8/2/2015.
 */
public class WathenFormula extends OneRepMaxFormula {

    @Override
    protected double calculateMax(int reps, double weight) {
        return 100 * weight / (48.8 + 53.8 * Math.pow(Math.E, -0.075 * reps));
    }

    @Override
    protected double calculateWeightForRep(int reps) {
        return max * (48.8 + 53.8 * Math.pow(Math.E, -0.075 * reps)) / 100;
    }

    @Override
    public FormulaType toEnum() {
        return FormulaType.Wathen;
    }
}
