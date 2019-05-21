package Parse_Tree.Statements;

import Parse_Tree.Expressions.Node_Boolean_Expression;
import Parse_Tree.Node_Block;

public class Node_While_Statement {

    // Parent
    private String while_statement;

    // Children
    private Node_Boolean_Expression child_node_boolean_expression;
    private Node_Block child_node_block;

    // Variables
    private String child_while;
    private String child_boolean_expression;
    private String child_block;
    private String child_end;
    private boolean syntax_error;

    // Constructors
    public Node_While_Statement(){}
    public Node_While_Statement(String while_statement){
        syntax_error=false;
        this.while_statement = while_statement.trim();

        // identify children
        if(this.while_statement.contains("while")) {
            child_while = this.while_statement.substring(0, this.while_statement.indexOf(" ")).trim();
            child_boolean_expression = this.while_statement.substring(this.while_statement.indexOf(" "), this.while_statement.indexOf("\n")).trim();
            child_block = this.while_statement.substring(this.while_statement.indexOf("\n")+1, Math.max(this.while_statement.lastIndexOf(" "), this.while_statement.lastIndexOf("\n"))).trim();
            child_end = this.while_statement.substring(Math.max(this.while_statement.lastIndexOf(" "), this.while_statement.lastIndexOf("\n"))).trim();
            syntax_error();
        }
        else
            syntax_error=true;

        if(!syntax_error)
            expand();
    }

    private boolean syntax_error(){
        syntax_error = while_error() || boolean_expression_error() || block_error() || end_error();
        return syntax_error;
    }
    private boolean while_error(){
        if(!child_while.equals("while"))
            return true;
        return false;
    }
    private boolean boolean_expression_error(){
        return false;
    }
    private boolean block_error(){ return false; }
    private boolean end_error(){
        if(!child_end.equals("end"))
            return true;
        return false;
    }

    // Create children
    private void expand(){
        child_node_boolean_expression = new Node_Boolean_Expression(child_boolean_expression);
        child_node_block = new Node_Block(child_block);
    }

    // Output
    public void display_node(int level){
        level++;
        for(int i=0; i<level; i++)
            System.out.print("- ");
        // Pre-order traversal
        // Parent
        System.out.printf("Level %d While Statement node, children:\n", level);
        System.out.printf("\tfunction: \'%s\'\n", child_while);
        System.out.printf("\tboolean expression: \'%s\'\n", child_boolean_expression);
        System.out.printf("\tblock: \'%s\'\n", child_block);
        System.out.printf("\tterminal: \'%s\'\n", child_end);

        // Children
        if(!syntax_error) {
            child_node_boolean_expression.display_node(level);
            child_node_block.display_node(level);
        }
    }
    public String get_node(int level){
        StringBuilder stringBuilder = new StringBuilder();
        level++;
        for(int i=0; i<level; i++)
            stringBuilder.append("- ");
        // Pre-order traversal
        // Parent
        stringBuilder.append("Level "+level+" While Statement node, children:\n");
        stringBuilder.append("\tfunction: \'"+child_while+"\'\n");
        stringBuilder.append("\tboolean expression: \'+child_boolean_expression+\'\n");
        stringBuilder.append("\tblock: \'"+child_block+"\'\n");
        stringBuilder.append("\tterminal: \'"+child_end+"\'\n");

        // Children
        if(!syntax_error) {
            stringBuilder.append(child_node_boolean_expression.get_node(level));
            stringBuilder.append(child_node_block.get_node(level));
        }
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error){
            System.out.println("Error in While Statement");
            if(while_error()) {
                System.out.printf("\twhile function: \'%s\' where it\'s supposed to be \'while'\n", child_while);
                return true;
            }
            if(end_error()){
                System.out.printf("\tterminal: \'%s\' where it\'s supposed to be \'end'\n", child_end);
                return true;
            }
            return true;
        }
        else {
            if(child_node_boolean_expression.display_error())
                return true;
            if(child_node_block.display_error())
                return true;
        }
        return false;
    }

}
