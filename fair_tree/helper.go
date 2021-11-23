package algorithm

func (n *Node)getCount() int64 {
	node := n
	for node.Left != nil {
		node = node.Left
	}
	return node.Count
}

func max(a ,b int64) int64 {
	if a > b {
		return a
	}else {
		return b
	}
}

func (n *Node)compare(node *Node) int{
	if n.Count <= node.Count {
		return 1
	} else {
		return -1
	}
}

func getHeight(n *Node) int64 {
	if n == nil {
		return 0
	}
	return n.height
}

func (n *Node)insert(node Node) (root *Node) {
	defer func() {
		root = balance(n)
		root.height = max(getHeight(root.Left), getHeight(root.Right)) + 1

	}()
	cmpResult := n.compare(&node)
	switch {
	case cmpResult < 0:
		if n.Left == nil {
			n.Left = &node
			return
		}
		n.Left = n.Left.insert(node)
		return
	case cmpResult > 0:
		if n.Right == nil {
			n.Right = &node
			return
		}
		n.Right = n.Right.insert(node)
		return
	default:
		return
	}
}

// 左旋
func leftRotate(node *Node) *Node{
	if node == nil {
		return nil
	}
	newNode := node.Right
	node.Right = newNode.Left
	newNode.Left = node

	newNode.height = max(getHeight(newNode.Left), getHeight(newNode.Right)) + 1
	node.height = max(getHeight(node.Left), getHeight(node.Right)) + 1
	return newNode
}

// 右旋
func rightRotate(node *Node) *Node{
	if node == nil {
		return nil
	}
	newNode := node.Left
	node.Left = newNode.Right
	newNode.Right = node

	newNode.height = max(getHeight(newNode.Left), getHeight(newNode.Right)) + 1
	node.height = max(getHeight(node.Left), getHeight(node.Right)) + 1
	return newNode
}

//先将左孩子左旋转，自己再右旋转
func (n *Node) leftRightRotate() *Node {
	n.Left = leftRotate(n.Left)
	return rightRotate(n)
}

//先将右孩子右旋转，然后自己右旋转
func (n *Node) rightLeftRotate() *Node {
	n.Right = rightRotate(n.Right)
	return leftRotate(n)
}

//调整树为二叉平衡树
func balance(node *Node) *Node {
	if node == nil {
		return nil
	}
	if getHeight(node.Right)-getHeight(node.Left) == 2 {
		if getHeight(node.Right.Right) > getHeight(node.Right.Left) {
			node = leftRotate(node)
		} else {
			node = node.rightLeftRotate()
		}
	} else if getHeight(node.Left)-getHeight(node.Right) == 2 {
		if getHeight(node.Left.Left) > getHeight(node.Left.Right) {
			node = rightRotate(node)
		} else {
			node = node.leftRightRotate()
		}
	}
	return node
}
