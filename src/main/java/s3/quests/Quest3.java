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
            addCurToTree(head);
        }
        ans = generateChecksum(head);
        return ans;
    }

    private void addCurToTree(Node head) {
        if (head.left != null) {
            addCurToTree(head.left);
        } else if (Objects.equals(head.leftSocket, cur.plug)) {
            head.left = cur;
            cur.parent = head;
            cur = null;
            return;
        }
        if (cur == null) {
            return;
        }
        if (head.right != null) {
            addCurToTree(head.right);
        } else if (Objects.equals(head.rightSocket, cur.plug)) {
            head.right = cur;
            cur.parent = head;
            cur = null;
            return;
        }
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
