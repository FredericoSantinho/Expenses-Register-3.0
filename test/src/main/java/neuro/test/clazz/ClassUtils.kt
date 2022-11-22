package neuro.test.clazz

import org.reflections.Reflections
import org.reflections.scanners.Scanners
import java.io.File
import java.io.IOException
import java.util.*

class ClassUtils {
  private val INITIAL_CAPACITY = 32

  /**
   * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   * @throws ClassNotFoundException
   * @throws IOException
   */
  @Throws(IOException::class, ClassNotFoundException::class)
  fun findClasses(packageName: String): MutableSet<Class<*>> {
    val reflections = Reflections(packageName, Scanners.SubTypes.filterResultsBy { c -> true })
    return reflections.getSubTypesOf(Any::class.java)
  }

  @Throws(IOException::class)
  fun findClassNames(packageName: String): List<String> {
    return findClasses(packageName).map { it.name.intern() }
  }

  /**
   * Recursive method used to find all class names in a given directory and subdirs.
   *
   * @param directory   The base directory
   * @param packageName The package name for classes found inside the base directory
   * @return The class names
   * @throws ClassNotFoundException
   */
  fun findClassNames(directory: File, packageName: String): Collection<String> {
    val classNames: MutableCollection<String> = ArrayList(INITIAL_CAPACITY)
    if (!directory.exists()) {
      return classNames
    }
    val files = directory.listFiles()
    if (files != null) {
      for (file in files) {
        if (file.isDirectory) {
          classNames.addAll(findClassNames(file, packageName + '.' + file.name))
        } else if (file.name.endsWith(".class")) {
          classNames.add(
            "$packageName." + file.name.substring(
              0, file.name.length - 6
            )
          )
        }
      }
    }
    return classNames
  }
}