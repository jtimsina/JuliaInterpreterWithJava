package Parse_Tree.Statements;

import Parse_Tree.Expressions.Node_Arithmetic_Expression;
import Parse_Tree.Tokens.Lexemes;
import Parse_Tree.Tokens.Lexical_Analyzer;

public class Node_Assignment_Statement {

    // Parent
    private String assign_statement;

    // Children
    private Node_Arithmetic_Expression child_node_arithmetic_expression;

    // Variables
    private String child_id;
    private String child_assignment_operator;
    private String child_arithmetic_expression;
    private boolean syntax_error;

    // Constructors
    public Node_Assignment_Statement(){}
    public Node_Assignment_Statement(String assign_statement){
        syntax_error=false;
        // Store block string
        this.assign_statement = assign_statement.trim();

        // identify children
        if(this.assign_statement.contains(Lexemes.assignment_operator)) {
            child_id = this.assign_statement.substring(0, this.assign_statement.indexOf("=")).trim();
            child_assignment_operator = "=";
            child_arithmetic_expression = this.assign_statement.substring(this.assign_statement.indexOf("=")+1).trim();
        }
        else
            syntax_error = true;

        syntax_error();

        if(!syntax_error)
            expand();
    }

    // Methods

    // Syntax Check
    private boolean syntax_error(){
        syntax_error = id_error() || arithmetic_expression_error() || assignment_operator_error();
        return syntax_error;
    }
    private boolean id_error(){
        Lexical_Analyzer lexical_analyzer = new Lexical_Analyzer(child_id);
        if(lexical_analyzer.get_type().equals("id"))
            return false;
        return true;
    }
    private boolean arithmetic_expression_error(){
        return false;
    }
    private boolean assignment_operator_error(){
        return false;
    }

    // Create children
    private void expand(){
        child_node_arithmetic_expression = new Node_Arithmetic_Expression(child_arithmetic_expression);
    }

    // Output
    public void display_node(int level){
        level++;
        for(int i=0; i<level; i++)
            System.out.print("- ");
        // Pre-order traversal
        // Parent
        System.out.printf("Level %d Assign Statement node, children:\n", level);
        System.out.printf("\tid: \'%s\'\n", child_id);
        System.out.printf("\tassignment_operator: \'%s\'\n", child_assignment_operator);
        System.out.printf("\tarithmetic expression: \'%s\'\n", child_arithmetic_expression);

        // Children
        if(!syntax_error) {
            child_node_arithmetic_expression.display_node(level);
        }
    }
    public String get_node(int level){
        StringBuilder stringBuilder = new StringBuilder();
        level++;
        for(int i=0; i<level; i++)
            stringBuilder.append("- ");
        // Pre-order traversal
        // Parent
        stringBuilder.append("Level "+level+" Assign Statement node, children:\n");
        stringBuilder.append("\tid: \'"+child_id+"\'\n");
        stringBuilder.append("\tassignment_operator: \'"+child_assignment_operator+"\'\n");
        stringBuilder.append("\tarithmetic expression: \'"+child_arithmetic_expression+"\'\n");

        // Children
        if(!syntax_error) {
            stringBuilder.append(child_node_arithmetic_expression.get_node(level));
        }
        return stringBuilder.toString();
    }
    public boolean display_error(){
        if(syntax_error){
            System.out.println("Error in Assignment Statement");
            if(id_error()){
                System.out.printf("\terror on id: \'%s\'\n", child_id);
                return true;
            }
            if(assignment_operator_error()) {
                System.out.printf("\terror on assignment operator: ", child_assignment_operator);
                return true;
            }
            return true;
        }
        else {
            if(child_node_arithmetic_expression.display_error())
                return true;
        }
        return false;
    }

}
