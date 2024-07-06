package software.dan.credit.app.system.exception

data class BusinessException(override val message: String?) : RuntimeException(message)