package Parse_Tree.Statements;

import Parse_Tree.Expressions.Node_Boolean_Expression;
import Parse_Tree.Node_Block;

public class Node_If_Statement {

    // Parent
    private String if_statement;

    // Children
    private Node_Boolean_Expression child_node_boolean_expression;
    private Node_Block child_node_block1, child_node_block2;

    // Variables
    private String child_if;
    private String child_boolean_expression;
    private String child_block1;
    private String child_else;
    private String child_block2;
    private String child_end;
    private boolean syntax_error;

    // Constructors
    public Node_If_Statement(){}
    public Node_If_Statement(String if_statement){
        syntax_error = false;
        // Store block string
        this.if_statement = if_statement.trim();

        // identify children
        if(this.if_statement.contains("if") && this.if_statement.contains("else")) {
            child_if = this.if_statement.substring(0, this.if_statement.indexOf(" ")).trim();
            child_boolean_expression = this.if_statement.substring(this.if_statement.indexOf(" "), this.if_statement.indexOf("\n")).trim();
            child_block1 = this.if_statement.substring(this.if_statement.indexOf("\n")+1).trim();
            child_else = "else";
            child_block2 = "";
            child_end = this.if_statement.substring(Math.max(this.if_statement.lastIndexOf(" "), this.if_statement.lastIndexOf("\n") )+1).trim();
            syntax_error();
        }
        else
            syntax_error=true;
        if(!syntax_error)
            expand();
    }

    private boolean syntax_error(){
        syntax_error = if_error() || boolean_expression_error() || block_error() || else_error() || end_error();
        return syntax_error;
    }
    private boolean if_error(){
        if(child_if.equals("if"))
            return true;
        return false;
    }
    private boolean boolean_expression_error(){
        return false;
    }
    private boolean block_error(){ return false; }
    private boolean else_error(){
        if(!child_else.equals("else"))
            return true;
        return false;
    }
    private boolean end_error(){
        if(!child_end.equals("end"))
            return true;
        return false; }

    // Create children
    private void expand(){
        child_node_boolean_expression = new Node_Boolean_Expression(child_boolean_expression);
        child_node_block1 = new Node_Block(child_block1);
        child_node_block2 = new Node_Block(child_block2);
    }

    // Output
    public void display_node(int level){
        level++;
        for(int i=0; i<level; i++)
            System.out.print("- ");
        // Pre-order traversal
        // Parent
        System.out.printf("Level %d If Statement node, children:\n", level);
        System.out.printf("\tfunction: \'%s\'\n", child_if);
        System.out.printf("\tboolean expression: \'%s\'\n", child_boolean_expression);
        System.out.printf("\tblock1: \'%s\'\n", child_block1);
        System.out.printf("\tblock2: \'%s\'\n", child_block2);
        System.out.printf("\tterminal: \'%s\'\n", child_end);

        // Children
        if(!syntax_error) {
            child_node_boolean_expression.display_node(level);
            child_node_block1.display_node(level);
            child_node_block2.display_node(level);
        }
    }
    public String get_node(int level){
        StringBuilder stringBuilder = new StringBuilder();
        level++;
        for(int i=0; i<level; i++)
            stringBuilder.append("- ");
        // Pre-order traversal
        // Parent
        stringBuilder.append("Level "+level+" If Statement node, children:\n");
        stringBuilder.append("\tfunction: \'"+child_if+"\'\n");
        stringBuilder.append("\tboolean expression: \'"+child_boolean_expression+"\'\n");
        stringBuilder.append("\tblock1: \'"+child_block1+"\'\n");
        stringBuilder.append("\tblock2: \'"+child_block2+"\'\n");
        stringBuilder.append("\tterminal: \'"+child_end+"\'\n");

        // Children
        if(!syntax_error) {
            stringBuilder.append(child_node_boolean_expression.get_node(level));
            stringBuilder.append(child_node_block1.get_node(level));
            stringBuilder.append(child_node_block2.get_node(level));
        }
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error){
            System.out.println("Error in If Statement");
            if(if_error()) {
                System.out.printf("\tif function: \'%s\' where it\'s supposed to be \'if'\n", child_if);
                return true;
            }
            if(else_error()){
                System.out.printf("\telse: \'%s\' where it\'s supposed to be \'else'\n", child_else);
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
            if(child_node_block1.display_error())
                return true;
            if(child_node_block2.display_error())
                return true;
        }
        return false;
    }
}
