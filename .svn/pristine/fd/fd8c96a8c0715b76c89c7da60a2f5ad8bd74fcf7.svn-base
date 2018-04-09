package cn.cnyirui.framework.model.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EasyUITreeNode implements Serializable {
    private static final long serialVersionUID = -2948844195239890950L;

    private String id;
    private String text;
    private String iconCls;
    private String state = "open";
    private Boolean checked = false;
    private String attributes;
    private List<EasyUITreeNode> children;

    public EasyUITreeNode(String id, String text, String iconCls, String attributes) {
        super();
        this.id = id;
        this.text = text;
        this.iconCls = iconCls;
        this.attributes = attributes;
    }

    public EasyUITreeNode() {
        super();
    }

    /**
     * id
     * 
     * @return id id
     */
    public String getId() {
        return id;
    }

    /**
     * id
     * 
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * text
     * 
     * @return text text
     */
    public String getText() {
        return text;
    }

    /**
     * text
     * 
     * @param text text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * iconCls
     * 
     * @return iconCls iconCls
     */
    public String getIconCls() {
        return iconCls;
    }

    /**
     * iconCls
     * 
     * @param iconCls iconCls
     */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    /**
     * state
     * 
     * @return state state
     */
    public String getState() {
        return state;
    }

    /**
     * state
     * 
     * @param state state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * checked
     * 
     * @return checked checked
     */
    public Boolean getChecked() {
        return checked;
    }

    /**
     * checked
     * 
     * @param checked checked
     */
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    /**
     * attributes
     * 
     * @return attributes attributes
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * attributes
     * 
     * @param attributes attributes
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    /**
     * children
     * 
     * @return children children
     */
    public List<EasyUITreeNode> getChildren() {
        if (children == null) {
            children = new ArrayList<EasyUITreeNode>();
        }
        return children;
    }

    /**
     * children
     * 
     * @param children children
     */
    public void setChildren(List<EasyUITreeNode> children) {
        this.children = children;
    }

    public boolean hasChildren() {
        return !getChildren().isEmpty();
    }

    public boolean childExists(String id) {
        return EasyUITreeNode.findById(getChildren(), id) != null;
    }

    public static EasyUITreeNode findById(List<EasyUITreeNode> easyUITreeNodes, String id) {
        if (easyUITreeNodes == null || easyUITreeNodes.isEmpty()) {
            return null;
        }
        for (EasyUITreeNode easyUITreeNode : easyUITreeNodes) {
            if (easyUITreeNode.id.equals(id)) {
                return easyUITreeNode;
            }
            EasyUITreeNode result = findById(easyUITreeNode.getChildren(), id);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
