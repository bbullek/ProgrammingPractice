
''' Depth first (a.k.a. preorder) traversal for binary tree  '''

# First, create a Stack class which will hold nodes to be visited
class Stack:
	def __init__(self):
		self.stack = []

	def push(self, item):
		self.stack.append(item)

	def pop(self):
		return self.stack.pop()

	def isEmpty(self):
		return len(self.stack) == 0

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

# Now the DFS algorithm
def DFS(root):
	if root is None: # Edge case where we're passed a null ref
		return

	stack = Stack() # Initialize queue
	root.visited = True
	stack.push(root)
	while stack.isEmpty() == False:
		v = stack.pop() # Some node containing a value
		print(v.value)
		# Push the right child first, so that the left child gets processed first
		if v.right is not None and v.right.visited == False:
			v.right.visited = True
			stack.push(v.right)
		if v.left is not None and v.left.visited == False:
			v.left.visited = True
			stack.push(v.left)

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
	# Perform DFS to visit the nodes of this tree
	DFS(tree.root)

main()