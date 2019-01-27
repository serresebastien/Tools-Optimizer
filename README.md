[🇫🇷](/README-fr.md "French")

# 🛠 Tools Optimizer 🛠

## Problem description

Numerically controlled machines have a tool magazine that stores all the tools needed to run a range. A tool changer is used to interchange the tool in place on the machine with a tool from the store. There are different types of stores and tool changers. For this study, we will consider two-way circular stores with a tool changer that performs tool permutations.

![Diagram](/img/diagram.png?raw=true "Diagram")

This operation implies that the change of tools can be done partially in hidden time. Indeed, during the period during which the tool in place is used, the store can turn to bring the next tool. It should be noted that a tool does not necessarily remain in the place it initially had.

The arrival of high-speed machining machines and the appearance of long machining ranges (in terms of the number of operations) and involving brief operations no longer make it possible to perform tool changes in hidden time. Therefore, the time taken by the machine to change the tools becomes a non-productive and non-negligible time compared to the total machining time.

The position of the tools in the store influences the changeover time of the tools. One can therefore wonder what is the "good" position of the tools in the store that reduces tool change times.

## Objective of the Project

This project consists in creating a tool allowing, for a given range, to optimize the position of the tools in the store of a machine. This tool will be based on Simulated Annealing (SA) method.

