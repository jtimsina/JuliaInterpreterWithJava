package Parse_Tree.Expressions;

import Parse_Tree.Tokens.Lexemes;

public class Node_Boolean_Expression {

    // Parent
    private String boolean_expression;

    // Children
    private Node_Arithmetic_Expression child_node_arithmetic_expression1, child_node_arithmetic_expression2;

    // Variables
    private String child_arithmetic_expression1;
    private String child_arithmetic_expression2;
    private String child_relative_op;
    private boolean syntax_error;

    // Constructors
    public Node_Boolean_Expression(){}
    public Node_Boolean_Expression(String boolean_expression){
        syntax_error=false;
        // Store block string
        this.boolean_expression = boolean_expression.trim();

        // identify children
        if(this.boolean_expression.contains(Lexemes.le_operator)) {
            child_arithmetic_expression1 = this.boolean_expression.substring(0, this.boolean_expression.indexOf(Lexemes.le_operator)).trim();
            child_relative_op = "le_operator";
            child_arithmetic_expression1 = this.boolean_expression.substring(this.boolean_expression.indexOf(Lexemes.le_operator) + Lexemes.le_operator.length() + 1).trim();
        } else if (this.boolean_expression.contains(Lexemes.lt_operator)) {
            child_arithmetic_expression1 = this.boolean_expression.substring(0, this.boolean_expression.indexOf(Lexemes.lt_operator)).trim();
            child_relative_op = "lt_operator";
            child_arithmetic_expression1 = this.boolean_expression.substring(this.boolean_expression.indexOf(Lexemes.lt_operator)+Lexemes.lt_operator.length()+1).trim();
        } else if (this.boolean_expression.contains(Lexemes.ge_operator)){
            child_arithmetic_expression1 = this.boolean_expression.substring(0, this.boolean_expression.indexOf(Lexemes.ge_operator)).trim();
            child_relative_op = "ge_operator";
            child_arithmetic_expression1 = this.boolean_expression.substring(this.boolean_expression.indexOf(Lexemes.ge_operator)+Lexemes.ge_operator.length()+1).trim();
        } else if (this.boolean_expression.contains(Lexemes.gt_operator)){
            child_arithmetic_expression1 = this.boolean_expression.substring(0, this.boolean_expression.indexOf(Lexemes.gt_operator)).trim();
            child_relative_op = "gt_operator";
            child_arithmetic_expression1 = this.boolean_expression.substring(this.boolean_expression.indexOf(Lexemes.gt_operator)+Lexemes.gt_operator.length()+1).trim();
        } else if (this.boolean_expression.contains(Lexemes.ne_operator)){
            child_arithmetic_expression1 = this.boolean_expression.substring(0, this.boolean_expression.indexOf(Lexemes.ne_operator)).trim();
            child_relative_op = "ne_operator";
            child_arithmetic_expression1 = this.boolean_expression.substring(this.boolean_expression.indexOf(Lexemes.ne_operator)+Lexemes.ne_operator.length()+1).trim();
        } else if (this.boolean_expression.contains(Lexemes.eq_operator)){
            child_arithmetic_expression1 = this.boolean_expression.substring(0, this.boolean_expression.indexOf(Lexemes.eq_operator)).trim();
            child_relative_op = "eq_operator";
            child_arithmetic_expression1 = this.boolean_expression.substring(this.boolean_expression.indexOf(Lexemes.eq_operator)+Lexemes.eq_operator.length()+1).trim();
        }else
            syntax_error=true;
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
        System.out.printf("Level %d Boolean Expression node, child:\n", level);
        System.out.printf("\trelative operator: \'%s\'\n", child_relative_op);
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
        stringBuilder.append("Level "+level+" Boolean Expression node, child:\n");
        stringBuilder.append("\trelative operator: \'"+child_relative_op+"\'\n");
        stringBuilder.append("\tarithmetic expression1: \'"+child_arithmetic_expression1+"\'\n");
        stringBuilder.append("\tarithmetic expression2: \'"+child_arithmetic_expression2+"\'\n");

        // Children
        if(!syntax_error) {
            stringBuilder.append(child_node_arithmetic_expression1.get_node(level));
            stringBuilder.append(child_node_arithmetic_expression2.get_node(level));
        }
        return stringBuilder.toString();
    }
    public boolean display_error() {
        if (syntax_error) {
            System.out.printf("Error Boolean Expression: \'%s\'\n", boolean_expression);
            return true;
        } else {
            if (child_node_arithmetic_expression1.display_error())
                return true;
            if (child_node_arithmetic_expression2.display_error())
                return true;
        }
        return false;
    }

}
