package ac.obl.cc

/*
 * Just a bunch of source annotations.
 * You don't need to have them in your codebase. Still, it may be helpful for
 * static code analysis.
 */

/**
 * 🟦
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class DATA

/**
 * 🟪
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class BUILDER

/**
 * 🟥
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class STATE

/**
 * 🟨
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class FUNCTION

/**
 * 🟧
 */
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class ACTION

/**
 * ⬛️
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class CONTEXT
