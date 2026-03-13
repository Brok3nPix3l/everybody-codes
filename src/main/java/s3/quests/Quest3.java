package s3.quests;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Quest3 extends QuestLong {
    private Node cur;

    @Override
    public long part1(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        List<Node> nodes = lines.stream().map(Node::fromLine).collect(Collectors.toList());
        Node head = nodes.removeFirst();
        for (Node node : nodes) {
            cur = node;
            addCurNodeToTree(head, BondPolicy.STRONG_ONLY);
        }
        ans = generateChecksum(head);
        return ans;
    }

    private void addCurNodeToTree(Node head, BondPolicy policy) {
        do {
            for (Side side : Side.values()) {
                if (head.getFromSide(side) != null) {
                    if (policy.strongReplacesWeak() && head.getBondTypeFromSide(side) == BondType.WEAK &&
                            isStrongBond(head.getSocketFromSide(side), cur.plug)) {
                        cur = head.connectToSide(cur, side, BondType.STRONG);
                    } else {
                        addCurNodeToTree(head.getFromSide(side), policy);
                    }
                } else if (isStrongBond(head.getSocketFromSide(side), cur.plug) ||
                        policy.allowWeak() && isWeakBond(head.getSocketFromSide(side), cur.plug)) {
                    cur = head.connectToSide(cur, side,
                            isStrongBond(head.getSocketFromSide(side), cur.plug) ? BondType.STRONG : BondType.WEAK);
                    return;
                }
                if (cur == null) {
                    return;
                }
            }
        } while (head.parent == null && cur != null);
    }

    private boolean isStrongBond(String s1, String s2) {
        return Objects.equals(s1, s2);
    }

    private boolean isWeakBond(String s1, String s2) {
        String[] s1Split = s1.split(" ", 2);
        String[] s2Split = s2.split(" ", 2);
        return Objects.equals(s1Split[0], s2Split[0]) || Objects.equals(s1Split[1], s2Split[1]);
    }

    private long generateChecksum(Node head) {
        List<Integer> values = new ArrayList<>();
        inorder(head, values);
        long checksum = 0;
        for (int i = 0; i < values.size(); i++) {
            checksum += (long) values.get(i) * (i + 1);
        }
        return checksum;
    }

    private void inorder(Node head, List<Integer> values) {
        if (head.left != null) {
            inorder(head.left, values);
        }
        values.add(head.id);
        if (head.right != null) {
            inorder(head.right, values);
        }
    }

    @Override
    public long part2(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        List<Node> nodes = lines.stream().map(Node::fromLine).collect(Collectors.toList());
        Node head = nodes.removeFirst();
        for (Node node : nodes) {
            cur = node;
            addCurNodeToTree(head, BondPolicy.STRONG_AND_WEAK);
        }
        ans = generateChecksum(head);
        return ans;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        List<Node> nodes = lines.stream().map(Node::fromLine).collect(Collectors.toList());
        Node head = nodes.removeFirst();
        for (Node node : nodes) {
            cur = node;
            addCurNodeToTree(head, BondPolicy.STRONG_REPLACES_WEAK);
        }
        ans = generateChecksum(head);
        return ans;
    }

    private enum BondPolicy {
        STRONG_ONLY {
            @Override
            public boolean allowWeak() {
                return false;
            }

            @Override
            public boolean strongReplacesWeak() {
                return false;
            }
        }, STRONG_AND_WEAK {
            @Override
            public boolean allowWeak() {
                return true;
            }

            @Override
            public boolean strongReplacesWeak() {
                return false;
            }
        }, STRONG_REPLACES_WEAK {
            @Override
            public boolean allowWeak() {
                return true;
            }

            @Override
            public boolean strongReplacesWeak() {
                return true;
            }
        };

        public abstract boolean allowWeak();

        public abstract boolean strongReplacesWeak();
    }

    public enum BondType {
        WEAK, STRONG
    }

    public enum Side {
        LEFT, RIGHT
    }


    private static class Node {
        private final int id;
        private final String plug;
        private final String leftSocket;
        private final String rightSocket;
        private Node left;
        private Node right;
        private Node parent;
        private BondType leftBondType;
        private BondType rightBondType;

        public Node(int id, String plug, String leftSocket, String rightSocket) {
            this.id = id;
            this.plug = plug;
            this.leftSocket = leftSocket;
            this.rightSocket = rightSocket;
        }

        public static Node fromLine(String line) {
            String[] split = line.split(", ");
            int id = Integer.parseInt(split[0].split("=", 2)[1]);
            String plug = split[1].split("=", 2)[1];
            String leftSocket = split[2].split("=", 2)[1];
            String rightSocket = split[3].split("=", 2)[1];
            return new Node(id, plug, leftSocket, rightSocket);
        }

        public Node connectToSide(Node newNode, Side side, BondType bondType) {
            if (side == Side.LEFT) {
                Node temp = left;
                left = newNode;
                leftBondType = bondType;
                newNode.parent = this;
                return temp;
            } else if (side == Side.RIGHT) {
                Node temp = right;
                right = newNode;
                newNode.parent = this;
                rightBondType = bondType;
                return temp;
            }
            throw new RuntimeException("Unexpected side: " + side.toString());
        }

        public Node getFromSide(Side side) {
            if (side == Side.LEFT) {
                return left;
            } else if (side == Side.RIGHT) {
                return right;
            } else {
                throw new RuntimeException("Unexpected side: " + side.toString());
            }
        }

        public BondType getBondTypeFromSide(Side side) {
            if (side == Side.LEFT) {
                return leftBondType;
            } else if (side == Side.RIGHT) {
                return rightBondType;
            } else {
                throw new RuntimeException("Unexpected side: " + side.toString());
            }
        }

        public String getSocketFromSide(Side side) {
            if (side == Side.LEFT) {
                return leftSocket;
            } else if (side == Side.RIGHT) {
                return rightSocket;
            } else {
                throw new RuntimeException("Unexpected side: " + side.toString());
            }
        }

        @Override
        public String toString() {
            return "Node{" + "id=" + id + '}';
        }
    }
}
