# Creating data
books = data.frame(
  title = c("A game of throne", "war and peace", "Lord of the ring"),
  author = c("George R. R. Martin", "Tolstoy", "Tolkien"),
  num_pages = c("500", "875", "500")
)

books$title == c("A game of throne", "war and peace", "Lord of the ring")
books$title[1] == "A game of throne"