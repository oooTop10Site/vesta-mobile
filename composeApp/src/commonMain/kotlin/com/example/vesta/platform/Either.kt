package com.example.vesta.platform
import com.example.vesta.platform.Either.Left
import com.example.vesta.platform.Either.Right

sealed class Either<out L, out R> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Left<out L>(val a: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Left(a)
    fun <R> right(b: R) = Right(b)

    inline fun fold(fnL: (L) -> Unit = {}, fnR: (R) -> Any = {}): Any =
        when (this) {
            is Left -> fnL(a)
            is Right -> fnR(b)
        }
}



fun <A, B, C> Either<A, B>.zip(fb: Either<A, C>): Either<A, Pair<B, C>> =
    flatMap { a ->
        fb.map { b -> Pair(a, b) }
    }

inline fun <L, R> Either<L, R>.onSuccess(action: (R) -> Unit): Either<L, R> {
    if (this is Right) action(b)
    return this
}

inline fun <L, R> Either<L, R>.doOnSuccess(action: (R) -> Unit): Either<L, R> =
    when (this) {
        is Either.Left -> {
            Left(a)
        }
        is Right -> {
            action(b)
            Right(b)
        }
    }

inline fun <L, R> Either<L, R>.doOnFailure(failure: (L) -> Unit): Either<L, R> =
    when (this) {
        is Left -> {
            failure.invoke(a)
            Left(a)
        }
        is Right -> Right(b)
    }

inline fun <L, R> Either<L, R>.onFailure(action: (L) -> Unit) {
    if (this is Left) action(a)
}

inline fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> =
    when (this) {
        is Left -> Left(a)
        is Right -> right(fn(b))
    }

inline fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
    when (this) {
        is Left -> Left(a)
        is Right -> fn(b)
    }

inline fun <L, R> Either<L, R>.log(fn: (R) -> String): Either<L, R> =
    when (this) {
        is Left -> Left(a)
        is Right -> {
            Right(b)
        }
    }

inline fun <L, R, Z> Either<L, R>.zipWith(fn: (R) -> Either<L, Z>): Either<L, Pair<R, Z>> =
    when (this) {
        is Left -> Left(a)
        is Right -> {
            fn(b).flatMap {
                Right(Pair(b, it))
            }
        }
    }

inline fun <L, R, Z, X> Either<L, Pair<R, Z>>.zipWithUp(fn: (Pair<R, Z>) -> Either<L, X>): Either<L, Triple<R, Z, X>> =
    when (this) {
        is Left -> Left(a)
        is Right -> {
            fn(Pair(b.first, b.second)).flatMap {
                Right(Triple(b.first, b.second, it))
            }
        }
    }


inline fun <T, L, R> Either<L, R>.onFailureResumeNext(failure: (L) -> Either<T, R>): Either<T, R> =
    when (this) {
        is Left -> failure(a)
        is Right -> Right(b)
    }

inline fun <L, R> Either<L, R>.onFailureReturn(failure: (L) -> Either<L, R>): Either<L, R> =
    when (this) {
        is Left -> failure(a)
        is Right -> Right(b)
    }


fun <L, R> Either<L, R>.getRight(): R {
    return (this as Right).b
}

fun <L, R> Either<L, R>.getLeft(): L {
    return (this as Left).a
}


fun <T> T.toRight(): Either<Failure, T> {
    return Either.Right(this)
}


fun toRightUnit(): Either<Failure, Unit> {
    return Either.Right(Unit)
}
