/**
  * Created by toff on 30/09/2016.
  */

trait Functor[F[_]]{
  def fmap[A,B](f: A=>B):F[A] => F[B]
}

object FunctorSeq extends Functor[Seq] {
  override def fmap[A, B](f: (A) => B): Seq[A] => Seq[B] = (a:Seq[A]) => a.map(f)
}

trait Pointed[F[_]] {
  def point[A](a: => A):F[A]
}

object PointedSeq extends Pointed[Seq[_]] {
  override def point[A](a: =>A):Seq[A] = Seq(a)
}

trait PointedFunctor[F[_]] extends Pointed[F] with Functor[F]

trait Applic[F[_]]{
  def apply[A, B](f: F[A=>B]):F[A] => F[B]
}

object OptionApplic  extends Applic[Option] {
  override def apply[A, B](f: Option[A => B]): Option[A] => Option[B] = (optionalA: Option[A]) => for {
    a <- optionalA
    func <- f
  } yield func.apply(a)
}

object FruitMarketApplic {
  case class Market(isOpen:Boolean)
  case class Fruit(name:String, price:Double)
  def pricer(market:Market):Option[Fruit => Double] =
    if (market.isOpen) Some{fruit:Fruit => fruit.price} else None
  def grow:Option[Fruit] = Some(Fruit("strawberry", 3.0))
  val price = OptionApplic.apply(pricer(Market(true))).apply(grow)
}

trait Applicative[F[_]] extends Pointed[F] with Functor[F] with Applic[F]

object ApplicativeSeq extends Applicative[Seq]{
  override def point[A](a: =>A):Seq[A] = Seq(a)
  override def fmap[A, B](f: (A) => B): Seq[A] => Seq[B] = (a:Seq[A]) => a.map(f)

  override def apply[A, B](f: Seq[(A) => B]): (Seq[A]) => Seq[B] = (seqA:Seq[A]) =>
    for {
      a <- seqA
      func <- f
    } yield func(a)
}


trait Traversable[T[_]]{
  def traverse[F[_],A,B](f: A => F[B])(implicit applicative:Applicative[F]): T[A] => F[T[B]]
}

object TraversableExample {
  sealed trait BinaryTree[A]
  case class Leaf[A](a:A) extends BinaryTree[A]
  case class Bin[A](left:BinaryTree[A], right:BinaryTree[A]) extends BinaryTree[A]

  def binaryTreeIsTraversable[A]:Traversable[BinaryTree] = new Traversable[BinaryTree] {
    def createLeaf[B]:B => BinaryTree[B] = (n:B) => (Leaf(n): BinaryTree[B])
    def createBin[B]:BinaryTree[B] => BinaryTree[B] => BinaryTree[B]  = (nl:BinaryTree[B]) => (nr:BinaryTree[B]) => (Bin(nl, nr):BinaryTree[B])
    override def traverse[F[_], A, B](f: (A) => F[B])(implicit applicative: Applicative[F]): (BinaryTree[A]) => F[BinaryTree[B]] =
    {(t:BinaryTree[A]) =>
      def monadicCreateLeaf[B]:F[B => BinaryTree[B]] = applicative.point(createLeaf)
      def appliedCreateLeaf:F[B] => F[BinaryTree[B]] = applicative.apply(monadicCreateLeaf)
      def monadicCreateBin[B]:F[BinaryTree[B] => BinaryTree[B] => BinaryTree[B]] = applicative.point(createBin)
      def appliedOnceCreateBin:F[BinaryTree[B]] => F[BinaryTree[B] => BinaryTree[B]] = applicative.apply(monadicCreateBin)
      t match {
        case Leaf(a) => appliedCreateLeaf(f(a))
        case Bin(l,r) =>
          val right:F[BinaryTree[B]] = traverse(f)(applicative).apply(r)
          val left:F[BinaryTree[B]] = traverse(f)(applicative).apply(l)
          applicative.apply(appliedOnceCreateBin(left)).apply(right)
          null
      }
    }
  }
}

trait GhApi[Context[_]]{
  def projects(user:User):Context[Seq[Project]]
  def pullRequests(project:Project):Context[Seq[PullRequest]]
  def watchers(project:Project):Context[Seq[User]]
}
  /*
class GenericStatisticsService(context:Monad[_])(api:GhApi[Monad]){
  def statistics(user:User):Monad[Statistics] = {
    val projectsF:Monad[Seq[Project]] = api.projects(user)
    projectsF.flatMap { projects =>
      null
    }
    null
  }
  def traversablePRs:Traverse[Seq[Monad[_]]] = new Traverse[Seq] {
    override def traverse[A, B](values: Seq[Applicative[_]], operation: (A) => Applicative[B])
                               (implicit applicative: Applicative[_]): Applicative[Seq[B]] = for (value <- values) yield value.applyNested(operation)
  }
  def getPRs(projects:Seq[Project]):Seq[Monad[Seq[PullRequest]]] =
    projects.map(project => api.pullRequests(project))
}

class MyGhApi extends GhApi[Seq]{
  override def projects(user:User):Seq[Seq[Project]] = Seq.empty[Seq[Project]]
  override def pullRequests(project:Project):Seq[Seq[PullRequest]] = Seq.empty[Seq[PullRequest]]
  override def watchers(project:Project):Seq[Seq[User]] = Seq.empty[Seq[User]]
}
val v = new GenericStatisticsService(new MyGhApi())
*/