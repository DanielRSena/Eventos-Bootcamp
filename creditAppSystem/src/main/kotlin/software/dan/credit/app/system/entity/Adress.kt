package software.dan.credit.app.system.entity

import jakarta.annotation.Nullable
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Embeddable
data class Address (
    @Column(nullable = false) var zipCode: String = "",
    @Column(nullable = false) var street: String = ""
)
