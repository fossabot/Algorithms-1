package bst

import (
	"github.com/stretchr/testify/assert"
	"testing"
)

func TestBsTree_Insert(t *testing.T) {
	bstree := NewBSTree()
	err := bstree.Insert(10)
	assert.Nil(t, err)
	err = bstree.Insert(20)
	assert.Nil(t, err)
	assert.True(t, bstree.root.value == 10)
	assert.True(t, bstree.root.right.value == 20)
	err = bstree.Insert(5)
	assert.Nil(t, err)
	assert.True(t, bstree.root.value == 10)
	assert.True(t, bstree.root.right.value == 20)
	assert.True(t, bstree.root.left.value == 5)
	//test
}

func TestBSTNode_Insert(t *testing.T) {

	var n *BSTNode = nil
	assert.NotNil(t, n.Insert(10))
	n = &BSTNode{value: 10}
	err := n.Insert(5)
	assert.Nil(t, err)
	assert.True(t, n.value == 10)
	assert.True(t, n.left.value == 5)

}
