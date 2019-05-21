package Parse_Tree;

import Parse_Tree.Expressions.Node_Arithmetic_Expression;
import Parse_Tree.Tokens.Lexemes;

public class Node_Iter {

    // Parent
    private String iter;

    // Children
    private Node_Arithmetic_Expression child_node_arithmetic_expression1, child_node_arithmetic_expression2;

    // Variables
    private String child_arithmetic_expression1;
    private String child_arithmetic_expression2;
    private boolean syntax_error;

    public Node_Iter(){}
    public Node_Iter(String iter){
        syntax_error = false;

        this.iter = iter;
        // identify children
        if(this.iter.contains(":")){
            child_arithmetic_expression1 = this.iter.substring(0, this.iter.indexOf(":")).trim();
            child_arithmetic_expression2 = this.iter.substring(this.iter.indexOf(":") + 1).trim();
            syntax_error();
        }
        else
            syntax_error = true;

        if(!syntax_error)
            expand();
    }

    private boolean syntax_error(){
        return syntax_error;
    }


    // Create children
    private void expand(){
        child_node_arithmetic_expression1 = new Node_Arithmetic_Expression(child_arithmetic_expression1);
        child_node_arithmetic_expression2 = new Node_Arithmetic_Expression(child_arithmetic_expression2);
    }

    // Output
    public void display_node(int level){
        level++;
        for(int i=0; i<level; i++)
            System.out.print("- ");
        // Pre-order traversal
        // Parent
        System.out.printf("Level %d For Statement node, children:\n", level);
        System.out.printf("\tarithmetic expression: \'%s\'\n", child_arithmetic_expression1);
        System.out.printf("\titer operator: \'%s\'\n", ":");
        System.out.printf("\tarithmetic expression: \'%s\'\n", child_arithmetic_expression2);

        // Children
        if(!syntax_error) {
            child_node_arithmetic_expression1.display_node(level);
            child_node_arithmetic_expression2.display_node(level);
        }

    }
    public String get_node(int level){
        StringBuilder stringBuilder = new StringBuilder();
        level++;
        for(int i=0; i<level; i++)
            stringBuilder.append("- ");
        // Pre-order traversal
        // Parent
        stringBuilder.append("Level "+level+" For Statement node, children:\n");
        stringBuilder.append("\tarithmetic expression: \'"+child_arithmetic_expression1+"\'\n");
        stringBuilder.append("\titer operator: \'"+":"+"\'\n");
        stringBuilder.append("\tarithmetic expression: \'"+child_arithmetic_expression2+"\'\n");

        // Children
        if(!syntax_error) {
            stringBuilder.append(child_node_arithmetic_expression1.get_node(level));
            stringBuilder.append(child_node_arithmetic_expression2.get_node(level));
        }
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error){
            System.out.printf("Error in Iter: \'%s\'\n", iter);
            return true;
        }
        else {
            if(child_node_arithmetic_expression1.display_error())
                return true;
            if(child_node_arithmetic_expression2.display_error())
                return true;
        }
        return false;
    }

}
