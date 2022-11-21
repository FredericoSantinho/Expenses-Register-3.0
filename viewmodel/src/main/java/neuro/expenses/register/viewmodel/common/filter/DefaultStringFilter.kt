package neuro.expenses.register.viewmodel.common.filter

class DefaultStringFilter() : StringFilter {
  override fun filter(query: String, s: String): Boolean {
    val querySplits = query.lowercase().split(' ')
    val sSplits = s.lowercase().split(' ')

    return querySplits.all { querySplit -> sSplits.any { it.contains(querySplit) } }
  }
}