# CS4310.01_Memory-Allocation-Comparison

Project objective:

Compare the performance of the memory allocation strategies first-fit, next-fit, best-fit, worst-fit.

Description:

The simulation mimics a series of request and release operations.

  •Physical memory is implemented as an integer arraylist of size n.

  •Each allocated block starts with a positive integer, which indicates the length of the block.

  •Each hole starts with a negative integer, -k, where the absolute value k indicates the length of the hole.

  •Each request specifies a new block of size s to be allocated, where s is generated at random from a normal (Gaussian) distribution.Values less than 1 and greater than n -1 are discarded.

  •Each release specifies one of the currently allocated blocks, chosen at random.

  •Memory utilization is the ratio of space occupied by blocks divided by the total memory size n, and can vary from 0 to 1.

Memory is assumed to be in a steady state and an unbounded stream of requests is always available. Consequently, a change to memory occurs only after a release operation:

  •The released block is first coalesced with any neighboring holes.

  •Next, zero or more requests may be satisfied, depending on the size of the new hole and the sizes of the requested blocks.

Task:

  •Implement the above simulation for the allocation strategies first-fit, next-fit, best-fit, worst-fit.

  •Choose an average request size, d, and a standard deviation, v. Use the chosen values to first initialize memory and then during the simulation whenever generating the next request size.

  •Initialize memory to contain a set of blocks of normally distributed sizes (using d and v) and placed randomly throughout the memory.

  •Run the simulation using different allocation strategies and record the average memory utilizations and the average search times (numbers of holes examined to satisfy a request).

  •Repeat the simulation for different values of d and v.

  •Plot families of curves showing how average memory utilization and average search times vary with average request size d. Then choose different values of v and repeat the experiments.
  
  

