package com.jholdy.lambda.controller.response

import java.math.BigDecimal

data class LoanSimulationResponse(
    val totalPayment: BigDecimal,
    val monthlyPayment: BigDecimal,
    val totalInterestPaid: BigDecimal
)