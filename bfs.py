''' Breadth first traversal for binary tree '''

# First, create a Queue class which will hold nodes to be visited
class Queue:
	def __init__(self):
		self.queue = []

	def enqueue(self, item):
		self.queue.append(item)

	def dequeue(self):
		return self.queue.pop(0)

	def isEmpty(self):
		return len(self.queue) == 0

# Define a Node class that holds references to L/R children, value, and 'visited' boolean
class Node:
	def __init__(self, value = None):
		self.value = value
		self.left = None
		self.right = None
		self.visited = False

# Define a Binary Tree class full of nodes
class BinTree:
	def __init__(self):
		self.root = None

# Now the BFS algorithm
def BFS(root):
	if root is None: # Edge case where we're passed a null ref
		return

	queue = Queue() # Initialize queue
	root.visited = True
	queue.enqueue(root)
	while queue.isEmpty() == False:
		v = queue.dequeue() # Some node containing a value
		print(v.value)
		# for all vertices adjacent to v...
		if v.left is not None and v.left.visited == False:
			v.left.visited = True
			queue.enqueue(v.left)
		if v.right is not None and v.right.visited == False:
			v.right.visited = True
			queue.enqueue(v.right)

# The main function where everything comes together
def main():
	tree = BinTree()
	# Populate the tree with nodes
	tree.root = Node(50)
	tree.root.left = Node(20)
	tree.root.right = Node(30)
	tree.root.left.left = Node(10)
	tree.root.left.right = Node(5)
	tree.root.right.left = Node(3)
	# Perform BFS to visit the nodes of this tree
	BFS(tree.root)

main()