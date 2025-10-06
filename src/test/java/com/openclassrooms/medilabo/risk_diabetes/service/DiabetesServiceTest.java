package com.openclassrooms.medilabo.risk_diabetes.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.openclassrooms.medilabo.risk_diabetes.enums.DiabetesRiskLevel;

public class DiabetesServiceTest {

    private final RiskCalculatorService calculatorService = new RiskCalculatorService();

    @Test
    void testMaleUnder30_EarlyOnset() {
        assertEquals(DiabetesRiskLevel.EARLYONSET, calculatorService.riskLevel(5, 29, "M"));
        assertEquals(DiabetesRiskLevel.EARLYONSET, calculatorService.riskLevel(10, 20, "M"));
    }

    @Test
    void testFemaleUnder30_EarlyOnset() {
        assertEquals(DiabetesRiskLevel.EARLYONSET, calculatorService.riskLevel(7, 25, "F"));
        assertEquals(DiabetesRiskLevel.EARLYONSET, calculatorService.riskLevel(10, 28, "F"));
    }

    @Test
    void testOver30_EarlyOnset() {
        assertEquals(DiabetesRiskLevel.EARLYONSET, calculatorService.riskLevel(8, 45, "M"));
        assertEquals(DiabetesRiskLevel.EARLYONSET, calculatorService.riskLevel(10, 60, "F"));
    }

    @Test
    void testMaleUnder30_InDanger() {
        assertEquals(DiabetesRiskLevel.INDANGER, calculatorService.riskLevel(3, 25, "M"));
    }

    @Test
    void testFemaleUnder30_InDanger() {
        assertEquals(DiabetesRiskLevel.INDANGER, calculatorService.riskLevel(4, 27, "F"));
    }

    @Test
    void testOver30_InDanger() {
        assertEquals(DiabetesRiskLevel.INDANGER, calculatorService.riskLevel(6, 40, "M"));
        assertEquals(DiabetesRiskLevel.INDANGER, calculatorService.riskLevel(7, 50, "F"));
    }

    @Test
    void testOver30_Borderline() {
        assertEquals(DiabetesRiskLevel.BORDERLINE, calculatorService.riskLevel(2, 35, "M"));
        assertEquals(DiabetesRiskLevel.BORDERLINE, calculatorService.riskLevel(5, 65, "F"));
    }

    @Test
    void testNoneCases() {
        assertEquals(DiabetesRiskLevel.NONE, calculatorService.riskLevel(0, 25, "M"));
        assertEquals(DiabetesRiskLevel.NONE, calculatorService.riskLevel(1, 40, "F"));
        assertEquals(DiabetesRiskLevel.NONE, calculatorService.riskLevel(2, 20, "M"));
    }
}
