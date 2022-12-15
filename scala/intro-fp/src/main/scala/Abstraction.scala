/**
  * Created by toff on 02/10/2016.
  */

package net.francesbagual.sandbox.scala

// Point let you add context to a type
trait Point[F[_]] {
  def point[A](a:A):F[A]
}

// Functor let you modify the content inside a Context
trait Functor[F[_]] {
  def fmap[A,B](f: A => B):F[B]
}

// Function that let you apply a function inside a context
trait Applicative[F[_]] {
  def apply[A,B](f:F[A => B]):F[A] => F[B]
}

// Traversable let you transform a collection of monad into a monad of collection
trait Traversable[T[_]] {
  def traverse[F[_],A,B](f: A => F[B])(implicit applicative:Applicative[F]): T[A] => T[F[B]]
}

// Const is a container for values of type M with a "phantom" type A
case class Const[M, +A] (value:M)

