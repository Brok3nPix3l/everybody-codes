package s3.quests;

import utils.StringUtils;

import java.util.*;
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
            for (int i = 0; i < 2 && cur != null; i++) {
                if (head.getChild(i) != null) {
                    if (policy.strongReplacesWeak() && head.getBondType(i) == BondType.WEAK &&
                            canFormStrongBond(head.getSocket(i), cur.plug)) {
                        cur = head.connect(cur, i, BondType.STRONG);
                    } else {
                        addCurNodeToTree(head.getChild(i), policy);
                    }
                } else if (canFormStrongBond(head.getSocket(i), cur.plug) ||
                        policy.allowWeak() && canFormWeakBond(head.getSocket(i), cur.plug)) {
                    cur = head.connect(cur, i,
                            canFormStrongBond(head.getSocket(i), cur.plug) ? BondType.STRONG : BondType.WEAK);
                    return;
                }
            }
        } while (head.parent == null && cur != null);
    }

    private boolean canFormStrongBond(String s1, String s2) {
        return Objects.equals(s1, s2);
    }

    private boolean canFormWeakBond(String s1, String s2) {
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
        if (head.getChild(0) != null) {
            inorder(head.getChild(0), values);
        }
        values.add(head.id);
        if (head.getChild(1) != null) {
            inorder(head.getChild(1), values);
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
        private final String[] sockets;
        private final Map<Integer,Node> children;
        private Node parent;
        private final Map<Integer,BondType> bondTypes;

        public Node(int id, String plug, String[] sockets) {
            this.id = id;
            this.plug = plug;
            this.sockets = sockets;
            this.children = new HashMap<>();
            this.bondTypes = new HashMap<>();
        }

        public static Node fromLine(String line) {
            String[] split = line.split(", ");
            int id = Integer.parseInt(split[0].split("=", 2)[1]);
            String plug = split[1].split("=", 2)[1];
            String leftSocket = split[2].split("=", 2)[1];
            String rightSocket = split[3].split("=", 2)[1];
            return new Node(id, plug, new String[]{leftSocket, rightSocket});
        }

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
