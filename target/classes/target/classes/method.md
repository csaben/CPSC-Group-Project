tragically, selenium is best documented with using an IDE like pycharm 
from intellij idea so im going to switch over for that for the rest of production

---

im going with number two;

given the url of the homepage of a novel,

scrape the urls associated with each chapter,

if >1 go to next page of listed chapters and scrape, go until no more urls,

store urls in a temporary variable,

begin a loop to scrape and collect an ArrayList<String> that includes all of the markdown 
from within the <div class="chapter-inner chapter-content">,

make a folder with the name of the novel

save each ele (chapter) in the arraylist to a file (.txt) within the novel folder

serve the files within the folder as an ArrayList<String> object with a class of 
its own that handles batching of chapters to gui (i.e. continouous, load 1, load 5, load 10, ...)

---

Ways to do this;

1) use driver to navigate each chapter and collect the inner chapter div to preserve formatting and just display markdown/html inside of the gui


2) .. loop through each paragraph element inside a div to create a wall of text.

advantage of two is that it would be trivial to implement continuous scrolling... although markdown/html should work with continuous too if it worked at all


