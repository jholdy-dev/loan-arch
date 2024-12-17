package com.jholdy.lambda.controller.request

import java.math.BigDecimal
import java.time.LocalDate

data class LoanSimulationRequest(
    val loanAmount: BigDecimal,
    val birthDate: LocalDate,
    val paymentTermInMonths: Int
)