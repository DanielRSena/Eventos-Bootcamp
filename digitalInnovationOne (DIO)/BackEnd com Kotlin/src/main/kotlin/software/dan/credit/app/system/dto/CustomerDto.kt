package software.dan.credit.app.system.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import software.dan.credit.app.system.entity.Address
import software.dan.credit.app.system.entity.Customer
import java.math.BigDecimal

data class CustomerDto(

    @field:NotEmpty(message = "first Name value is empty") val firstName: String,
    @field:NotEmpty(message = "last Name value is empty") val lastName: String,
    @field:NotEmpty(message = "CPF value is empty")
    @field:CPF(message = "invalid cpf") val cpf: String,
    @field:NotNull(message = "The income value is null") val income: BigDecimal,
    @field:NotEmpty(message = "email value is empty")
    @field:Email(message = "Invalid email") val email: String,
    @field:NotEmpty(message = "password value is empty") val password: String,
    @field:NotEmpty(message = "zipCode value is empty") val zipCode: String,
    @field:NotEmpty(message = "street value is empty") val street: String
) {

    fun toEntity(): Customer = Customer(

        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}