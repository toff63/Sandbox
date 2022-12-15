trait Serializable[A] {
  def toJson(a: A): String
}
case class Person(name: String, age: Int)
case class Job(employer: String, position: String)

implicit val personSerializer: Serializable[Person] =
  (p: Person) => s"{\"name\": \"${p.name}\", \"age\": ${p.age}}"
implicit val jobSerializer: Serializable[Job] =
  (j: Job) => s"{\"employer\": \"${j.employer}\", \"position\": \"${j.position}\"}"

def serialize[S: Serializable](s: S): String = {
  val serializer = implicitly[Serializable[S]]
  serializer.toJson(s)
}

val john = Person("john", 23)
println(serialize(john))
val job = Job("Zego", "SWE")
println(serialize(job))

sealed trait Animal
final case class Dog(name: String) extends Animal
final case class Cat(name: String) extends Animal
final case class Bird(name: String) extends Animal

trait BehavesLikeHuman[A] {
  def speak(a: A): Unit
}

implicit class BehavesLikeHumanOp[A: BehavesLikeHuman](value: A) {
  def speak(): Unit = {
    val behavesLikeHuman = implicitly[BehavesLikeHuman[A]]
    behavesLikeHuman.speak(value)
  }
}
implicit val behavesLikeHumanDog: BehavesLikeHuman[Dog] = (d: Dog) => println(s"I'm a Dog, my name is ${d.name}")

Dog("Café").speak()