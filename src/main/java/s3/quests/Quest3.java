package s3.quests;

import utils.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Quest3 extends QuestLong {
    private Node curNode;

    @Override
    public long part1(String input) {
        List<String> lines = StringUtils.splitInput(input);
        List<Node> nodes = lines.stream().map(Node::fromLine).collect(Collectors.toList());
        Node head = nodes.removeFirst();
        for (Node node : nodes) {
            curNode = node;
            addCurNodeToTree(head, BondPolicy.STRONG_ONLY);
        }
        return generateChecksum(head);
    }

    @Override
    public long part2(String input) {
        List<String> lines = StringUtils.splitInput(input);
        List<Node> nodes = lines.stream().map(Node::fromLine).collect(Collectors.toList());
        Node head = nodes.removeFirst();
        for (Node node : nodes) {
            curNode = node;
            addCurNodeToTree(head, BondPolicy.STRONG_AND_WEAK);
        }
        return generateChecksum(head);
    }

    @Override
    public long part3(String input) {
        List<String> lines = StringUtils.splitInput(input);
        List<Node> nodes = lines.stream().map(Node::fromLine).collect(Collectors.toList());
        Node head = nodes.removeFirst();
        for (Node node : nodes) {
            curNode = node;
            addCurNodeToTree(head, BondPolicy.STRONG_REPLACES_WEAK);
        }
        return generateChecksum(head);
    }

    /**
     * Adds {@link #curNode} to the provided tree.
     *
     * @param head   entry point of the tree, to which {@code curNode} is added
     * @param policy determines which rules should be applied
     */
    private void addCurNodeToTree(Node head, BondPolicy policy) {
        do {
            for (int i = 0; i < 2 && curNode != null; i++) {
                Node curChild = head.getChild(i);
                String curSocket = head.getSocket(i);
                boolean strong = canFormStrongBond(curSocket, curNode.plug);
                boolean weak = policy.allowWeak() && canFormWeakBond(curSocket, curNode.plug);

                if (curChild != null) {
                    if (policy.strongReplacesWeak() && head.getBondType(i) == BondType.WEAK && strong) {
                        curNode = head.connect(curNode, i, BondType.STRONG);
                    } else {
                        addCurNodeToTree(curChild, policy);
                    }
                } else if (strong || weak) {
                    curNode = head.connect(curNode, i, strong ? BondType.STRONG : BondType.WEAK);
                    return;
                }
            }
        } while (head.parent == null && curNode != null);
    }

    /**
     * A strong bond is one where both the color and the shape match.
     */
    private boolean canFormStrongBond(String s1, String s2) {
        return Objects.equals(s1, s2);
    }

    /**
     * A weak bond is created when only the color matches or only the shape matches between the plug and the
     * socket.
     */
    private boolean canFormWeakBond(String s1, String s2) {
        String[] s1Split = s1.split(" ", 2);
        String[] s2Split = s2.split(" ", 2);
        return Objects.equals(s1Split[0], s2Split[0]) || Objects.equals(s1Split[1], s2Split[1]);
    }

    /**
     * Performs an inorder traversal of the tree and multiplies each
     * node's {@link Node#id} by the order in which it was encountered (starting from 1)
     *
     * @param head entry point of the tree, to which {@code curNode} is added
     */
    private long generateChecksum(Node head) {
        List<Integer> values = new ArrayList<>();
        inorder(head, values);
        long checksum = 0;
        for (int i = 0; i < values.size(); i++) {
            checksum += (long) values.get(i) * (i + 1);
        }
        return checksum;
    }

    /**
     * Performs an inorder traversal of the tree
     *
     * @param head   entry point of the tree
     * @param values {@link Node#id} values are appended to this list in the order in which they are encountered
     */
    private void inorder(Node head, List<Integer> values) {
        if (head.getChild(0) != null) {
            inorder(head.getChild(0), values);
        }
        values.add(head.id);
        if (head.getChild(1) != null) {
            inorder(head.getChild(1), values);
        }
    }


    private enum BondPolicy {
        /**
         * Only strong bonds are allowed to be formed.
         */
        STRONG_ONLY {
            @Override
            public boolean allowWeak() {
                return false;
            }

            @Override
            public boolean strongReplacesWeak() {
                return false;
            }
        },
        /**
         * Weak and strong bonds are allowed to be formed.
         */
        STRONG_AND_WEAK {
            @Override
            public boolean allowWeak() {
                return true;
            }

            @Override
            public boolean strongReplacesWeak() {
                return false;
            }
        },
        /**
         * Weak and strong bonds are allowed to be formed, and strong bonds can replace weak bonds.
         */
        STRONG_REPLACES_WEAK {
            @Override
            public boolean allowWeak() {
                return true;
            }

            @Override
            public boolean strongReplacesWeak() {
                return true;
            }
        };

        /**
         * @return {@code True} if weak bonds are allowed to be formed, {@code false} if only strong bonds are
         * allowed
         */
        public abstract boolean allowWeak();

        /**
         * @return {@code True} if strong bonds are allowed to replace weak bonds and {@code false} if not
         */
        public abstract boolean strongReplacesWeak();
    }

    public enum BondType {
        WEAK, STRONG
    }


    private static class Node {
        private final int id;
        private final String plug;
        private final String[] sockets;
        private final Map<Integer, Node> children;
        private final Map<Integer, BondType> bondTypes;
        private Node parent;

        public Node(int id, String plug, String[] sockets) {
            this.id = id;
            this.plug = plug;
            this.sockets = sockets;
            this.children = new HashMap<>();
            this.bondTypes = new HashMap<>();
        }

        /**
         * @param line expected format is e.g. <pre>"id=1, plug=BLUE HEXAGON, leftSocket=GREEN CIRCLE, rightSocket=BLUE PENTAGON, data=?"</pre>
         */
        public static Node fromLine(String line) {
            String[] split = line.split(", ");
            int id = Integer.parseInt(split[0].split("=", 2)[1]);
            String plug = split[1].split("=", 2)[1];
            String leftSocket = split[2].split("=", 2)[1];
            String rightSocket = split[3].split("=", 2)[1];
            return new Node(id, plug, new String[]{leftSocket, rightSocket});
        }

        /**
         * Connects {@code newNode} to this {@link Node} as its {@code index}-th child.
         *
         * @param newNode  the new child node
         * @param index    the index at which the child node is added
         * @param bondType the type of bond formed between this {@link Node} and {@code newNode}
         * @return the {@link Node} that used to be this {@link Node}'s {@code index}-th child, or {@code null} if
         * there wasn't one
         */
        public Node connect(Node newNode, int index, BondType bondType) {
            Node temp = children.getOrDefault(index, null);
            children.put(index, newNode);
            bondTypes.put(index, bondType);
            newNode.parent = this;
            return temp;
        }

        public Node getChild(int index) {
            return children.getOrDefault(index, null);
        }

        public BondType getBondType(int index) {
            return bondTypes.getOrDefault(index, null);
        }

        public String getSocket(int index) {
            return sockets[index];
        }

        @Override
        public String toString() {
            return "Node{" + "id=" + id + '}';
        }
    }
}
