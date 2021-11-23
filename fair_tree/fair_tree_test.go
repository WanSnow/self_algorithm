package algorithm

import (
	"fmt"
	"testing"
)

func TestTree(t *testing.T) {
	count1 := int64(1)
	count2 := int64(2)
	count3 := int64(4)
	node := NewRoot(func(){
		fmt.Println("perCount:", count1)
	}, count1)
	node = node.Insert(func() {
		fmt.Println("perCount:", count2)
	}, count2)
	node = node.Insert(func() {
		fmt.Println("perCount:", count3)
	}, count3)

	for i:=0 ; i<20 ; i++ {
		var temp Node
		node, temp = NextNode(node)
		temp.Value.(func())()
		node = PushBack(node, temp)
	}
}
