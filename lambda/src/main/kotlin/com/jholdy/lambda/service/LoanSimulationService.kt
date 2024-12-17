package com.jholdy.lambda.service

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.time.LocalDate
import java.time.Period

@Service
class LoanSimulationService {
    fun simulateLoan(loanAmount: BigDecimal, birthDate: LocalDate, paymentTermInMonths: Int): LoanSimulationResult {
        val interestRate = calculateInterestRate(birthDate)
        val monthlyRate = interestRate.divide(BigDecimal(12), 8, RoundingMode.HALF_UP)

        val monthlyPayment = calculateMonthlyPayment(loanAmount, monthlyRate, paymentTermInMonths)
        val totalPayment = monthlyPayment.multiply(BigDecimal(paymentTermInMonths)).setScale(2, RoundingMode.HALF_UP)
        val totalInterestPaid = totalPayment.subtract(loanAmount).setScale(2, RoundingMode.HALF_UP)

        return LoanSimulationResult(
            totalPayment = totalPayment,
            monthlyPayment = monthlyPayment,
            totalInterestPaid = totalInterestPaid
        )
    }

    private fun calculateInterestRate(birthDate: LocalDate): BigDecimal {
        val age = Period.between(birthDate, LocalDate.now()).years
        return when {
            age <= 25 -> BigDecimal("0.05")
            age in 26..40 -> BigDecimal("0.03")
            age in 41..60 -> BigDecimal("0.02")
            else -> BigDecimal("0.04")
        }
    }

    private fun calculateMonthlyPayment(loanAmount: BigDecimal, monthlyRate: BigDecimal, totalPayments: Int): BigDecimal {
        if (monthlyRate.compareTo(BigDecimal.ZERO) == 0) {
            return loanAmount.divide(BigDecimal(totalPayments), 2, RoundingMode.HALF_UP)
        }

        val factor = (BigDecimal.ONE + monthlyRate).pow(totalPayments)
        return loanAmount.multiply(monthlyRate).multiply(factor)
            .divide(factor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP)
    }
}

data class LoanSimulationResult(
    val totalPayment: BigDecimal,
    val monthlyPayment: BigDecimal,
    val totalInterestPaid: BigDecimal
)
