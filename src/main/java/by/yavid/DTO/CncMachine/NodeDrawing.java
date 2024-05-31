package by.yavid.DTO.CncMachine;

import java.util.ArrayList;

public class NodeDrawing implements Comparable<NodeDrawing> {
    private String text;
    private ArrayList<NodeDrawing> nodes;

    public NodeDrawing(String text, ArrayList<NodeDrawing> nodes) {
        this.text = text;
        this.nodes = nodes;
    }

    public NodeDrawing(String text) {
        this.text = text;
    }

    public NodeDrawing() {
        this.nodes= new ArrayList<NodeDrawing>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<NodeDrawing> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<NodeDrawing> nodes) {
        this.nodes = nodes;
    }

    public void AddNodes(NodeDrawing node) {
        this.nodes.add(node);
    }

    @Override
    public int compareTo(NodeDrawing o) {
        return this.text.compareTo(o.getText());
    }
}
