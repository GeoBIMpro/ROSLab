/**
 *
 */
package roslab.model.general;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Gebhard
 */
public class Configuration {

    String name;
    Library lib;
    List<Node> nodes;
    List<Link> links;

    /**
     * @param lib
     */
    public Configuration(Library lib) {
        this(null, lib, null, null);
    }

    /**
     * @param name
     * @param lib
     */
    public Configuration(String name, Library lib) {
        this(name, lib, null, null);
    }

    /**
     * @param name
     * @param lib
     * @param nodes
     * @param links
     */
    public Configuration(String name, Library lib, List<Node> nodes, List<Link> links) {
        if (name == null) {
            this.name = "Default";
        }
        else {
            this.name = name;
        }
        if (lib == null) {
            throw new IllegalArgumentException("Bad Library input: Library input cannot be Null.");
        }
        else {
            this.lib = lib;
        }
        if (nodes == null) {
            this.nodes = new ArrayList<Node>();
        }
        else {
            this.nodes = nodes;
        }
        if (links == null) {
            this.links = new ArrayList<Link>();
        }
        else {
            this.links = links;
        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the library
     */
    public Library getLibrary() {
        return lib;
    }

    /**
     * @param lib
     *            the library to set
     */
    public void setLibrary(Library lib) {
        this.lib = lib;

        // Clear nodes and links now that there's a new library
        this.nodes.clear();
        this.links.clear();
    }

    public boolean addNode(Node n) {
        if (!nodes.contains(n)) {
            return nodes.add(n);
        }
        return false;
    }

    public boolean removeNode(Node n) {
        return nodes.remove(n);
    }

    /**
     * @return the nodes
     */
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * @param clazz
     *            The class type of the nodes to be returned
     * @return the nodes of input type
     */
    public List<? extends Node> getNodesOfType(Class<? extends Node> clazz) {
        List<Node> nodes = new ArrayList<Node>();

        for (Node n : this.nodes) {
            if (n.getClass().equals(clazz)) {
                nodes.add(n);
            }
        }

        return nodes;
    }

    /**
     * @param nodes
     *            the UI nodes to set
     */
    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public boolean addLink(Link l) {
        if (!links.contains(l)) {
            return links.add(l);
        }
        return false;
    }

    public boolean removeLink(Link link) {
        return links.remove(link);
    }

    /**
     * @return the links
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @param links
     *            the links to set
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }

    /**
     * @return the links that include Node n
     */
    public List<Link> getLinks(Node n) {
        List<Link> links = new ArrayList<Link>();

        for (Link l : this.links) {
            if (l.getSrc().getParent().equals(n) || l.getDest().getParent().equals(n)) {
                links.add(l);
            }
        }

        return links;
    }

    /**
     * @return the links with class type matching the input type
     */
    public List<Link> getLinksOfType(Class<? extends Endpoint> clazz) {
        List<Link> links = new ArrayList<Link>();

        for (Link l : this.links) {
            if (l.getSrc().getClass().equals(clazz) || l.getDest().getClass().equals(clazz)) {
                links.add(l);
            }
        }

        return links;
    }

    /**
     * @return the list of endpoints
     */
    public List<Endpoint> getEndpoints() {
        List<Endpoint> eps = new ArrayList<Endpoint>();

        for (Node n : this.getNodes()) {
            eps.addAll(n.getEndpoints());
        }

        return eps;
    }

    public boolean contains(Node n) {
        return this.nodes.contains(n);
    }

    public boolean contains(Link l) {
        return this.links.contains(l);
    }

    public Node getNodeByName(String name) {
        for (Node n : nodes) {
            if (name.equals(n.getName())) {
                return n;
            }
        }
        return null;
    }

}
