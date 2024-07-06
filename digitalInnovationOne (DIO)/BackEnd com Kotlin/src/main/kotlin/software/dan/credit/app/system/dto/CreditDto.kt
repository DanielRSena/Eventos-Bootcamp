package software.dan.credit.app.system.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import software.dan.credit.app.system.entity.Credit
import software.dan.credit.app.system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "The credit value is null") val creditValue: BigDecimal,
    @field:Future(message = "The day first Installment must be in the future") val dayFirstOfInstallment: LocalDate,
    @field:NotNull(message = "The income value is null") val numberOfInstallments: Int,
    @field:NotNull(message = "The customer Id value is null") val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallment = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}