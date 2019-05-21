package Parse_Tree;

import Parse_Tree.Statements.Node_Statement;

public class Node_Block {

    // Parent
    private String parent_block;

    // Children
    private Node_Statement child_node_statement;
    private Node_Block child_node_block;

    // Variables
    private String child_statement;
    private String child_block;
    private boolean syntax_error;

    public Node_Block(){}
    public Node_Block(String parent_block){
        syntax_error = false;

        // Store block string
        this.parent_block = parent_block.trim();

        // identify children
        if(this.parent_block.contains("\n")) {
            child_statement = this.parent_block.substring(0, this.parent_block.indexOf("\n")).trim();
            child_block = this.parent_block.substring(this.parent_block.indexOf("\n")+1).trim();
            syntax_error();
        }
        else {
            child_statement = this.parent_block;
        }

        if(child_statement!=null && !syntax_error)
            expand();
    }

    // Methods

    // Syntax check
    private boolean syntax_error(){
        syntax_error = statement_error() || block_error();
        return syntax_error;
    }
    private boolean statement_error(){
        return false;
    }
    private boolean block_error(){ return false; }

    // Create children
    private void expand(){
        if(child_block != null)
            child_node_block = new Node_Block(child_block);
        child_node_statement = new Node_Statement(child_statement);
    }
    
    // Output
    public void display_node(int level){
        level++;
        for(int i=0; i<level; i++)
            System.out.print("- ");
        // Pre-order traversal
        // Parent
        System.out.printf("Level %d Block node, children:\n", level);
        System.out.printf("\tstatement: \'%s\'\n", child_statement);
        if(child_block != null)
            System.out.printf("\tblock: \'%s\'\n", child_block);

        // Children
        child_node_statement.display_node(level);
        if(child_node_block != null && !syntax_error)
            child_node_block.display_node(level);
    }
    public String get_node(int level){
        StringBuilder stringBuilder = new StringBuilder();
        level++;
        for(int i=0; i<level; i++)
            stringBuilder.append("- ");
        // Pre-order traversal
        // Parent
        stringBuilder.append("Level "+level+" Block node, children:\n");
        stringBuilder.append("\tstatement: \'"+child_statement+"\'\n");
        if(child_block != null)
            stringBuilder.append("\tblock: \'"+child_block+"\'\n");

        // Children
        stringBuilder.append(child_node_statement.get_node(level));
        if(child_node_block != null && !syntax_error)
            stringBuilder.append(child_node_block.get_node(level));
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error) {
            if (statement_error()) {
                System.out.println("Error in statement");
                System.out.printf("\tstatement: \'%s\' where it\'s supposed to be \'function'\n", child_statement);
                return true;
            }
            System.out.println("Error in Block");
            return true;
        }
        else{
            if(child_node_statement.display_error())
                return true;
            if(child_node_block!=null)
                if(child_node_block.display_error())
                    return true;
        }
        return false;
    }
}
