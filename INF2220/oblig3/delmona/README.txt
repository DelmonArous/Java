Mandatory assignment 3

- How to compile and run the program:
	javac *.java
	java Oblig3 "c_g" "cogwrgaccag"
	java Oblig3 "" "cogwrgaccag"
	java Oblig3 "_ac" "cogwrgaccag"
	java Oblig3 "ga_" "cogwrgaccag"
	java Oblig3 "_a_" "cogwrgaccag"
	java Oblig3 "___" "cogwrgaccag"
	java Oblig3 "c_g" ""
	!!OR!!
	java Oblig3 <pattern> <filename>

- The main method is included in Oblig3.java.

- Assumptions:
	The text (the haystack) does not contain the "_"-wildcard-symbol.
	1-byte characters (2^8).
	
- There are no peculiarities about the implementation and everything works:
	We take in the pattern and text, and construct bad character shift array of the pattern. Initially we let all 
	array values $x \in [0,255]$ take the value (length of needle - 1 - the index of the next to last wildcards in the needle), 
	since each wildcard is a positive match. This is done to align the next wildcard in the needle to the haystack. The remaining 
	array values of the bad character shift are standard. Berfore searching for pattern matches, we check if the needle length is larger 
	than the haystack or if the needle is empty. If so, we return an empty array of index matches. If not, we start searching for the needle 
	in the haystack. Here we follow the implementation of Boyer Moore Horspool given in lecture 9 slide 17. The only change is that we add
	the index of the match to the index match array (rather than only returning the index of the first positive match).
	We then break from the for-loop and continue searching for more positive matches to add to the array. A positive match
	is defined if needle[i]=haystack[j] or needle[i]=wildcard.

- All the test cases gave expected results:
	For instance, if the haystack is "cogwrgaccag" and needle is "_ac". The only pattern-match starts out at index 5, i.e.
	haystack[5] = 'g', because of the wildcard. This is shown in the output.

I have cooperated with Saurav Sharma (sauravsh) in this assignment.