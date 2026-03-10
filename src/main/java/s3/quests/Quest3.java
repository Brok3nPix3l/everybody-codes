package s3.quests;

import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Quest3 extends QuestLong {
    private Node cur;
    private List<Node> nodes;

    @Override
    public long part1(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        nodes = lines.stream().map(Node::fromLine).collect(Collectors.toList());
        Node head = nodes.removeFirst();
        cur = nodes.removeFirst();
        buildTree(head);
        while (head.parent != null) {
            head = head.parent;
        }
        ans = generateChecksum(head);
        return ans;
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
        if (head == null) {
            return;
        }
        if (head.left != null) {
            inorder(head.left, values);
        }
        values.add(head.id);
        if (head.right != null) {
            inorder(head.right, values);
        }
    }

    private void buildTree(Node head) {
        while (cur != null) {
            if (head.left != null) {
                buildTree(head.left);
            } else if (Objects.equals(cur.plug, head.leftSocket)) {
                head.left = cur;
                cur.parent = head;
                if (nodes.isEmpty()) {
                    cur = null;
                    break;
                }
                cur = nodes.removeFirst();
            }
            if (head.right != null) {
                buildTree(head.right);
            } else if (Objects.equals(cur.plug, head.rightSocket)) {
                head.right = cur;
                cur.parent = head;
                if (nodes.isEmpty()) {
                    cur = null;
                    break;
                }
                cur = nodes.removeFirst();
            }
            if (head.parent != null) {
                break;
            }
        }
    }

    @Override
    public long part2(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }

    @Override
    public long part3(String input) {
        long ans = 0L;
        List<String> lines = StringUtils.splitInput(input);
        return ans;
    }

    private static class Node {
        private final int id;
        private final String plug;
        private final String leftSocket;
        private final String rightSocket;
        private Node left;
        private Node right;
        private Node parent;

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

        @Override
        public String toString() {
            return "Node{" + "id=" + id + '}';
        }
    }
}
