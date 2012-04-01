// Simple for
for(i <- 1 to 3){
  print(i)
}

// array
val a = Array(1, 2, 3, 4)
for(i <- 0 until a.size){
  print(a(i))
}

// multiple generators
for(i <- 1 to 3; j <- 1 to 3) print (i+j)

// guards
for(i <- 1 to 3; j<- 1 to 3 if i!=j) print(i+j)

// comprehension list: use yield:

for(i <- 1 to 10) yield i // Vector(1,2,3,4,5,6,7,8,9,10)
