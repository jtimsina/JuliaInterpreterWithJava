package Parse_Tree.Expressions;

import Parse_Tree.Tokens.Lexemes;

public class Node_Binary_Expression {

    // Parent
    private String binary_expression;

    // Children
    private Node_Arithmetic_Expression child_node_arithmetic_expression1, child_node_arithmetic_expression2;

    // Variables
    private String child_arithmetic_expression1;
    private String child_arithmetic_op;
    private String child_arithmetic_expression2;
    private boolean syntax_error;

    public Node_Binary_Expression(){}
    public Node_Binary_Expression(String binary_expression){
        syntax_error=false;
        this.binary_expression = binary_expression;
        // identify children
        if(this.binary_expression.contains(Lexemes.add_operator)) {
            child_arithmetic_expression1 = this.binary_expression.substring(0, this.binary_expression.indexOf(Lexemes.add_operator)).trim();
            child_arithmetic_op = "add_operator";
            child_arithmetic_expression2 = this.binary_expression.substring(this.binary_expression.indexOf(Lexemes.add_operator) + 1).trim();
        } else if (this.binary_expression.contains(Lexemes.sub_operator)) {
            child_arithmetic_expression1 = this.binary_expression.substring(0, this.binary_expression.indexOf(Lexemes.sub_operator)).trim();
            child_arithmetic_op = "sub_operator";
            child_arithmetic_expression2 = this.binary_expression.substring(this.binary_expression.indexOf(Lexemes.sub_operator) + 1).trim();
        } else if (this.binary_expression.contains(Lexemes.mul_operator)) {
            child_arithmetic_expression1 = this.binary_expression.substring(0, this.binary_expression.indexOf(Lexemes.mul_operator)).trim();
            child_arithmetic_op = "mul_operator";
            child_arithmetic_expression2 = this.binary_expression.substring(this.binary_expression.indexOf(Lexemes.mul_operator) + 1).trim();
        } else if (this.binary_expression.contains(Lexemes.div_operator)) {
            child_arithmetic_expression1 = this.binary_expression.substring(0, this.binary_expression.indexOf(Lexemes.div_operator)).trim();
            child_arithmetic_op = "div_operator";
            child_arithmetic_expression2 = this.binary_expression.substring(this.binary_expression.indexOf(Lexemes.sub_operator) + 1).trim();
        } else if (this.binary_expression.contains(Lexemes.mod_operator)) {
            child_arithmetic_expression1 = this.binary_expression.substring(0, this.binary_expression.indexOf(Lexemes.mod_operator)).trim();
            child_arithmetic_op = "mod_operator";
            child_arithmetic_expression2 = this.binary_expression.substring(this.binary_expression.indexOf(Lexemes.mod_operator) + 1).trim();
        } else if (this.binary_expression.contains(Lexemes.rev_div_operator)) {
            child_arithmetic_expression1 = this.binary_expression.substring(0, this.binary_expression.indexOf(Lexemes.rev_div_operator)).trim();
            child_arithmetic_op = "rev_div_operator";
            child_arithmetic_expression2 = this.binary_expression.substring(this.binary_expression.indexOf(Lexemes.rev_div_operator) + 1).trim();
        } else if (this.binary_expression.contains(Lexemes.exp_operator)) {
            child_arithmetic_expression1 = this.binary_expression.substring(0, this.binary_expression.indexOf(Lexemes.exp_operator)).trim();
            child_arithmetic_op = "exp_operator";
            child_arithmetic_expression2 = this.binary_expression.substring(this.binary_expression.indexOf(Lexemes.exp_operator) + 1).trim();
        }
        else
            syntax_error=true;

        if (!syntax_error)
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
        System.out.printf("Level %d Binary Expression node, child:\n", level);
        System.out.printf("\tassignment operator: \'%s\'\n", child_arithmetic_op);
        System.out.printf("\tarithmetic expression1: \'%s\'\n", child_arithmetic_expression1);
        System.out.printf("\tarithmetic expression2: \'%s\'\n", child_arithmetic_expression2);

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
        stringBuilder.append("Level "+level+" Binary Expression node, child:\n");
        stringBuilder.append("\tassignment operator: \'"+child_arithmetic_op+"\'\n");
        stringBuilder.append("\tarithmetic expression1: \'"+child_arithmetic_expression1+"\'\n");
        stringBuilder.append("\tarithmetic expression2: \'"+child_arithmetic_expression2+"\'\n");

        // Children
        if(!syntax_error) {
            stringBuilder.append(child_node_arithmetic_expression1.get_node(level));
            stringBuilder.append(child_node_arithmetic_expression2.get_node(level));
        }
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error){
            System.out.printf("Error Binary Expression: \'%s\'\n", binary_expression);
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
