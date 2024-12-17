package com.jholdy.lambda

import com.jholdy.lambda.service.LoanSimulationService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class LoanSimulationServiceTest {

    private val loanSimulationService = LoanSimulationService()

    @Test
    fun `should calculate loan simulation correctly for age under 25`() {
        val loanAmount = BigDecimal(10000)
        val birthDate = LocalDate.now().minusYears(20)
        val paymentTermInMonths = 12

        val result = loanSimulationService.simulateLoan(
            loanAmount = loanAmount,
            birthDate = birthDate,
            paymentTermInMonths = paymentTermInMonths
        )

        val expectedTotalPayment = BigDecimal("10272.84")
        val expectedMonthlyPayment = BigDecimal("856.07")
        val expectedTotalInterestPaid = BigDecimal("272.84")

        assertEquals(expectedTotalPayment, result.totalPayment)
        assertEquals(expectedMonthlyPayment, result.monthlyPayment)
        assertEquals(expectedTotalInterestPaid, result.totalInterestPaid)
    }

    @Test
    fun `should calculate loan simulation correctly for age between 26 and 40`() {
        val loanAmount = BigDecimal(10000)
        val birthDate = LocalDate.now().minusYears(30)
        val paymentTermInMonths = 24

        val result = loanSimulationService.simulateLoan(
            loanAmount = loanAmount,
            birthDate = birthDate,
            paymentTermInMonths = paymentTermInMonths
        )

        val expectedTotalPayment = BigDecimal("10315.44")
        val expectedMonthlyPayment = BigDecimal("429.81")
        val expectedTotalInterestPaid = BigDecimal("315.44")

        assertEquals(expectedTotalPayment, result.totalPayment)
        assertEquals(expectedMonthlyPayment, result.monthlyPayment)
        assertEquals(expectedTotalInterestPaid, result.totalInterestPaid)
    }

    @Test
    fun `should calculate loan simulation correctly for age between 41 and 60`() {
        val loanAmount = BigDecimal(10000)
        val birthDate = LocalDate.now().minusYears(50)
        val paymentTermInMonths = 36

        val result = loanSimulationService.simulateLoan(
            loanAmount = loanAmount,
            birthDate = birthDate,
            paymentTermInMonths = paymentTermInMonths
        )

        val expectedTotalPayment = BigDecimal("10311.48")
        val expectedMonthlyPayment = BigDecimal("286.43")
        val expectedTotalInterestPaid = BigDecimal("311.48")

        assertEquals(expectedTotalPayment, result.totalPayment)
        assertEquals(expectedMonthlyPayment, result.monthlyPayment)
        assertEquals(expectedTotalInterestPaid, result.totalInterestPaid)
    }

    @Test
    fun `should calculate loan simulation correctly for age above 60`() {
        val loanAmount = BigDecimal(10000)
        val birthDate = LocalDate.now().minusYears(70)
        val paymentTermInMonths = 48

        val result = loanSimulationService.simulateLoan(
            loanAmount = loanAmount,
            birthDate = birthDate,
            paymentTermInMonths = paymentTermInMonths
        )

        val expectedTotalPayment = BigDecimal("10837.92")
        val expectedMonthlyPayment = BigDecimal("225.79")
        val expectedTotalInterestPaid = BigDecimal("837.92")

        assertEquals(expectedTotalPayment, result.totalPayment)
        assertEquals(expectedMonthlyPayment, result.monthlyPayment)
        assertEquals(expectedTotalInterestPaid, result.totalInterestPaid)
    }
}
