import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryProvider
import org.komapper.dialect.postgresql.r2dbc.PostgreSqlR2dbcDialect
import org.komapper.r2dbc.R2dbcDatabase
import java.util.*

fun main() {
    connect()
}

fun connect() {
    val options = ConnectionFactoryOptions.builder()
        .option(ConnectionFactoryOptions.DRIVER, "pool")
        .option(ConnectionFactoryOptions.PROTOCOL, "postgresql")
        .option(ConnectionFactoryOptions.HOST, "localhost")
        .option(ConnectionFactoryOptions.DATABASE, "mydb")
        .option(ConnectionFactoryOptions.USER, "root")
        .option(ConnectionFactoryOptions.PASSWORD, "root")
        .build()
    println(getAvailableDrivers())
    R2dbcDatabase(options, dialect = PostgreSqlR2dbcDialect())
}

// from io.r2dbc.spi.ConnectionFactories
private fun getAvailableDrivers(): String {
    val availableDrivers = StringBuilder()
    for (provider in loadProviders()) {
        if (availableDrivers.isNotEmpty()) {
            availableDrivers.append(", ")
        }
        availableDrivers.append(provider.driver)
    }
    if (availableDrivers.isEmpty()) {
        availableDrivers.append("None")
    }
    return availableDrivers.toString()
}

private fun loadProviders(): ServiceLoader<ConnectionFactoryProvider> {
    return ServiceLoader.load(ConnectionFactoryProvider::class.java, ConnectionFactoryProvider::class.java.classLoader)
}