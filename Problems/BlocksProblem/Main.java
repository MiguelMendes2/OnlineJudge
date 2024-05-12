package Problems.BlocksProblem;


import java.util.Scanner;

/**
 * Blocks Problem
 * The problem is to implement a simulation of a block world. The world is a plane surface with a number of
 * blocks piled on it. The blocks are rectangular solids with a height of 1 and of varying area, in such a way
 * that only one block can be placed at any location on the plane. The world is divided into a number of
 * locations, each of which is identified by a number (starting from 0).
 */
public class Main {

    public static class StackNode{
        private int nBlock;
        private StackNode next;

        public StackNode(int nBlock) {
            this.nBlock = nBlock;
            next = null;
        }
    }

    private static StackNode[] blocks;

    public static void Runner() {
        Scanner scn = new Scanner(System.in);
        int nBlocks = scn.nextInt();
        initBlocks(nBlocks);
        while(true) {
            String[] command = scn.nextLine().split(" ");
            if(command[0].equals("quit")) {
                break;
            }
            if(command[0].equals("move")) {
                int a = Integer.parseInt(command[1]);
                int b = Integer.parseInt(command[3]);
                if(ilegalCommand(a, b)) {
                    continue;
                }
                if(command[2].equals("onto")) {
                    moveOnto(a, b);
                } else if(command[2].equals("over")) {
                    moveOver(a, b);
                }
            } else if(command[0].equals("pile")) {
                int a = Integer.parseInt(command[1]);
                int b = Integer.parseInt(command[3]);
                if(ilegalCommand(a, b)) {
                    continue;
                }
                if(command[2].equals("onto")) {
                    pileOnto(a, b);
                } else if(command[2].equals("over")) {
                    pileOver(a, b);
                }
            }
        }
        scn.close();
        printBlocks();
    }

    private static boolean ilegalCommand(int a, int b) {
        return a == b || findBlockStack(a) == findBlockStack(b);
    }

    private static void initBlocks(int nBlocks) {
        blocks = new StackNode[nBlocks];
        for(int i = 0; i < nBlocks; i++) {
            blocks[i] = new StackNode(i);
        }
    }

    private static void moveOnto(int a, int b) {
        removeBlock(a);
        removeBlock(b, true, true);
        stackBlockNumber(new StackNode(a),b);
    }

    private static void moveOver(int a, int b) {
        removeBlock(a);
        stackBlock(new StackNode(a), findBlockStack(b));
    }

    private static void pileOnto(int a, int b) {
        StackNode stackA = getBlock(a);
        removeBlock(b,true, true);
        removeBlock(a, false, false);
        stackBlockNumber(stackA,b);
    }

    private static void pileOver(int a, int b) {
        StackNode stackA = getBlock(a);
        removeBlock(a, false, false);
        stackBlock(stackA, findBlockStack(b));
    }

    private static void stackBlockNumber(StackNode block, int blockNumber) {
        int stackIndex = findBlockStack(blockNumber);
        StackNode stack = blocks[stackIndex];
        if (stack == null) {
            blocks[stackIndex] = block;
            return;
        }
        if(stack.nBlock == blockNumber) {
            stack.next = block;
            return;
        }
        while(stack.nBlock != blockNumber) {
            stack = stack.next;
        }
        stack.next = block;
    }

    private static void stackBlock(StackNode block, int stackIndex) {
        StackNode stack = blocks[stackIndex];
        if (stack == null) {
            blocks[stackIndex] = block;
            return;
        }
        while(stack.next != null) {
            stack = stack.next;
        }
        stack.next = block;
    }

    private static void removeBlock(int blockNum) {
        removeBlock(blockNum, false, true);
    }

    private static void removeBlock(int blockNum, boolean removeAbove, boolean initPositions) {
        int stackIndex = findBlockStack(blockNum);
        StackNode stack = blocks[stackIndex];
        StackNode temp = null;
        if (stack.nBlock == blockNum) {
            temp = stack.next;
            if(!removeAbove) {
                blocks[stackIndex] = null;
            } else {
                stack.next = null;
            }
        } else {
            while(stack.next != null) {
                if(stack.next.nBlock == blockNum) {
                    temp = stack.next.next;
                    if (removeAbove) {
                        stack.next.next = null;
                    } else {
                        stack.next = null;
                    }
                    break;
                }
                stack = stack.next;
            }
        }
        if(initPositions) {
            while(temp != null) {
                stackBlock(new StackNode(temp.nBlock), temp.nBlock);
                temp = temp.next;
            }
        }
    }

    private static int findBlockStack(int blockNum) {
        for(int i = 0; i < blocks.length; i++) {
            StackNode stack = blocks[i];
            while(stack != null) {
                if(stack.nBlock == blockNum) {
                    return i;
                }
                stack = stack.next;
            }
        }
        return -1;
    }

    private static StackNode getBlock(int blockNum) {
        int stackIndex = findBlockStack(blockNum);
        StackNode stack = blocks[stackIndex];
        while(stack != null) {
            if(stack.nBlock == blockNum) {
                return stack;
            }
            stack = stack.next;
        }
        throw new IllegalArgumentException("Block not found in any stack");
    }

    private static void printBlocks() {
        StringBuilder strB = new StringBuilder();
        for(int i = 0; i < blocks.length; i++) {
            StackNode stack = blocks[i];
            strB.append(i + ":");
            while(stack != null) {
                strB.append(" ").append(stack.nBlock);
                stack = stack.next;
            }
            if (i + 1 < blocks.length) {
                strB.append("\n");
            }
        }
        System.out.println(strB.toString());
    }
    public static void main(String[] args) {
        Runner();
    }
}
