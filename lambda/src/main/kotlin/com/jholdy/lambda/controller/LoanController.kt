package com.jholdy.lambda.controller
import com.jholdy.lambda.controller.request.LoanSimulationRequest
import com.jholdy.lambda.controller.response.LoanSimulationResponse
import com.jholdy.lambda.service.LoanSimulationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/loan")
class LoanSimulationController(private val loanSimulationService: LoanSimulationService) {

    @PostMapping("/simulate")
    fun simulateLoan(@RequestBody request: LoanSimulationRequest): ResponseEntity<LoanSimulationResponse> {
        val simulationResult = loanSimulationService.simulateLoan(
            loanAmount = request.loanAmount,
            birthDate = request.birthDate,
            paymentTermInMonths = request.paymentTermInMonths
        )

        val response = LoanSimulationResponse(
            totalPayment = simulationResult.totalPayment,
            monthlyPayment = simulationResult.monthlyPayment,
            totalInterestPaid = simulationResult.totalInterestPaid
        )

        return ResponseEntity.ok(response)
    }
}