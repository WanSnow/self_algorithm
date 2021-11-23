package algorithm

type Node struct {
	Value interface{}
	Count int64
	PerCount int64
	height int64
	Left *Node
	Right *Node
}

func NewRoot(value interface{}, perCount int64) *Node{
	node := &Node{
		Value: value,
		height: 1,
		Count: 0,
		PerCount: perCount,
	}
	return node
}

func (n *Node) Insert(value interface{}, perCount int64) (root *Node){
	node := Node{
		Value: value,
		height: 1,
		Count: n.getCount(),
		PerCount: perCount,
	}
	return n.insert(node)
}

func NextNode(old *Node) (root *Node, node Node) {
	defer func() {
		root = balance(old)
		root.height = max(getHeight(root.Left), getHeight(root.Right)) + 1
	}()

	temp := &Node{
		Left: old,
	}

	ptr := temp
	node = *old
	for node.Left != nil {
		ptr = ptr.Left
		node = *node.Left
	}
	ptr.Left = nil
	return
}

func PushBack(old *Node, node Node) (root *Node) {
	node.Count += node.PerCount
	return old.insert(node)
}
