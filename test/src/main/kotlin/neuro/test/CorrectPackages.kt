package neuro.test

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import neuro.test.clazz.ClassUtils
import neuro.test.rx.ObserveSubscriptionTest
import java.io.IOException
import java.util.*

class CorrectPackages(
  aClass: Class<*>,
  exclusions: List<String> = listOf(ObserveSubscriptionTest::class.qualifiedName!!),
  private val classUtils: ClassUtils = ClassUtils()
) {
  private val exclusions: List<String>

  init {
    this.exclusions = buildExclusions(exclusions, aClass)
  }

  fun checkPackages() {
    getWrongPackages(javaClass).forEach { System.err.println(it) }
  }

  @Throws(IOException::class)
  fun getWrongPackages(aClass: Class<*>): List<Throwable> {
    val classNames: List<String> = classUtils.findClassNames(getRootPackage(aClass))
      .filter { className: String ->
        className.endsWith(
          "Test"
        )
      }
      .toList()
    val list: MutableList<Throwable> = LinkedList()
    Observable.fromIterable(classNames)
      .flatMapCompletable { s: String ->
        Completable.fromAction { checkPackage(s) }
          .doOnError { e: Throwable ->
            list.add(
              e
            )
          }
          .onErrorComplete()
      }
      .blockingAwait()
    return list
  }

  private fun buildExclusions(exclusions: List<String>, aClass: Class<*>): LinkedList<String> {
    val list = LinkedList(exclusions)
    list.add(aClass.name)
    return list
  }

  private fun getRootPackage(aClass: Class<*>): String {
    return aClass.name
      .substring(
        0, aClass.name
          .indexOf('.')
      )
  }

  @Throws(IOException::class)
  private fun checkPackage(className: String) {
    val classNames = classUtils.findClassNames(getPackage(className))
    require(
      !(!className.endsWith("CorrectPackagesTest") && !exclusions.contains(className) && !isParentPresent(
        classNames,
        className
      ))
    ) { "Package not right! $className" }
  }

  private fun getPackage(className: String): String {
    // We'll assume that all classes contain dots.
    return className.substring(0, className.lastIndexOf('.'))
  }

  private fun isParentPresent(classes: List<String>, testClassName: String): Boolean {
    val expecteClassName = testClassName.substring(0, testClassName.length - 4)
      .intern()
    return classes.any { s: String -> expecteClassName === s }
  }
}