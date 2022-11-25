package neuro.expenses.register.common.koin

import org.koin.core.context.GlobalContext
import org.koin.dsl.KoinAppDeclaration

fun startKoinIfNeeded(appDeclaration: KoinAppDeclaration) {
  GlobalContext.getKoinApplicationOrNull() ?: GlobalContext.startKoin(appDeclaration)
}